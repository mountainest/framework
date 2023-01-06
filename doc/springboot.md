```java
public class SpringApplication {
    public ConfigurableApplicationContext run(String... args) {
        long startTime = System.nanoTime();
        // 处理 bootstrapRegistryInitializers，加到AbstractApplicationEventMulticaster.defaultRetriever.applicationListeners
        DefaultBootstrapContext bootstrapContext = createBootstrapContext();
        ConfigurableApplicationContext context = null;
        configureHeadlessProperty();
        // 加载SpringApplicationRunListener，并通过SpringApplicationRunListeners分为多个阶段
        SpringApplicationRunListeners listeners = getRunListeners(args);
        // 早期初始化
        listeners.starting(bootstrapContext, this.mainApplicationClass);
        try {
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            // 会再次创建WebApplicationType.NONE类型的SpringApplication（即AnnotationConfigApplicationContext），调用run方法，目的是？
            ConfigurableEnvironment environment = prepareEnvironment(listeners, bootstrapContext, applicationArguments);
            configureIgnoreBeanInfo(environment);
            // 打印banner
            Banner printedBanner = printBanner(environment);
            // ConfigurationClassPostProcessor等后置处理器注入，会默认初始化一个AnnotationConfigServletWebServerApplicationContext
            context = createApplicationContext();
            context.setApplicationStartup(this.applicationStartup);
            // 被我们标记了@SpringBootApplication的启动主类在运行过程中会被包装成一个bean定义，放入容器中
            prepareContext(bootstrapContext, context, environment, listeners, applicationArguments, printedBanner);
            // 解析包扫描信息并完成剩余bean注册，比如MybatisPlus包等
            refreshContext(context);
            afterRefresh(context, applicationArguments);
            Duration timeTakenToStartup = Duration.ofNanos(System.nanoTime() - startTime);
            if (this.logStartupInfo) {
                new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), timeTakenToStartup);
            }
            listeners.started(context, timeTakenToStartup);
            callRunners(context, applicationArguments);
        } catch (Throwable ex) {
            handleRunFailure(context, ex, listeners);
            throw new IllegalStateException(ex);
        }
        try {
            Duration timeTakenToReady = Duration.ofNanos(System.nanoTime() - startTime);
            listeners.ready(context, timeTakenToReady);
        } catch (Throwable ex) {
            handleRunFailure(context, ex, null);
            throw new IllegalStateException(ex);
        }
        return context;
    }

    private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
                                                       DefaultBootstrapContext bootstrapContext, ApplicationArguments applicationArguments) {
        // Create and configure the environment，初始化ApplicationServletEnvironment
        ConfigurableEnvironment environment = getOrCreateEnvironment();
        configureEnvironment(environment, applicationArguments.getSourceArgs());
        ConfigurationPropertySources.attach(environment);
        // 会再次调用run方法，以WebApplicationType.NONE类型的SpringApplication
        listeners.environmentPrepared(bootstrapContext, environment);
        DefaultPropertiesPropertySource.moveToEnd(environment);
        Assert.state(!environment.containsProperty("spring.main.environment-prefix"),
            "Environment prefix cannot be set via properties.");
        bindToSpringApplication(environment);
        if (!this.isCustomEnvironment) {
            environment = convertEnvironment(environment);
        }
        ConfigurationPropertySources.attach(environment);
        return environment;
    }

    // 加载配置
    private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
                                                       DefaultBootstrapContext bootstrapContext, ApplicationArguments applicationArguments) {
        // Create and configure the environment，初始化ApplicationServletEnvironment
        ConfigurableEnvironment environment = getOrCreateEnvironment();
        configureEnvironment(environment, applicationArguments.getSourceArgs());
        ConfigurationPropertySources.attach(environment);
        // 会再次调用run方法，以WebApplicationType.NONE类型的SpringApplication
        listeners.environmentPrepared(bootstrapContext, environment);
        DefaultPropertiesPropertySource.moveToEnd(environment);
        Assert.state(!environment.containsProperty("spring.main.environment-prefix"),
            "Environment prefix cannot be set via properties.");
        bindToSpringApplication(environment);
        if (!this.isCustomEnvironment) {
            environment = convertEnvironment(environment);
        }
        ConfigurationPropertySources.attach(environment);
        return environment;
    }
}
```
ConfigurationClassPostProcessor处理：
(1) @Configuration注解的作用是什么，Spring是如何解析加了@Configuration注解的类？
(2) Spring在什么时候对@ComponentScan、@ComponentScans注解进行了解析？
(3) Spring什么时候解析了@Import注解，如何解析的？
(4) Spring什么时候解析了@Bean注解？
都是该类处理

```java
public class BootstrapApplicationListener {
    private ConfigurableApplicationContext bootstrapServiceContext(ConfigurableEnvironment environment,
                                                                   final SpringApplication application, String configName) {
        ConfigurableEnvironment bootstrapEnvironment = new AbstractEnvironment() {
        };
        MutablePropertySources bootstrapProperties = bootstrapEnvironment.getPropertySources();
        String configLocation = environment.resolvePlaceholders("${spring.cloud.bootstrap.location:}");
        String configAdditionalLocation = environment
            .resolvePlaceholders("${spring.cloud.bootstrap.additional-location:}");
        Map<String, Object> bootstrapMap = new HashMap<>();
        bootstrapMap.put("spring.config.name", configName);
        // if an app (or test) uses spring.main.web-application-type=reactive, bootstrap
        // will fail
        // force the environment to use none, because if though it is set below in the
        // builder
        // the environment overrides it
        bootstrapMap.put("spring.main.web-application-type", "none");
        if (StringUtils.hasText(configLocation)) {
            bootstrapMap.put("spring.config.location", configLocation);
        }
        if (StringUtils.hasText(configAdditionalLocation)) {
            bootstrapMap.put("spring.config.additional-location", configAdditionalLocation);
        }
        // 打个标签，只有第一次进入BootstrapApplicationListener，防止再次进来调用SpringApplication的run方法
        bootstrapProperties.addFirst(new MapPropertySource(BOOTSTRAP_PROPERTY_SOURCE_NAME, bootstrapMap));
        for (PropertySource<?> source : environment.getPropertySources()) {
            if (source instanceof StubPropertySource) {
                continue;
            }
            bootstrapProperties.addLast(source);
        }
        // TODO: is it possible or sensible to share a ResourceLoader?
        SpringApplicationBuilder builder = new SpringApplicationBuilder().profiles(environment.getActiveProfiles())
            .bannerMode(Mode.OFF).environment(bootstrapEnvironment)
            // Don't use the default properties in this builder
            .registerShutdownHook(false).logStartupInfo(false).web(WebApplicationType.NONE);
        final SpringApplication builderApplication = builder.application();
        if (builderApplication.getMainApplicationClass() == null) {
            // gh_425:
            // SpringApplication cannot deduce the MainApplicationClass here
            // if it is booted from SpringBootServletInitializer due to the
            // absense of the "main" method in stackTraces.
            // But luckily this method's second parameter "application" here
            // carries the real MainApplicationClass which has been explicitly
            // set by SpringBootServletInitializer itself already.
            builder.main(application.getMainApplicationClass());
        }
        if (environment.getPropertySources().contains("refreshArgs")) {
            // If we are doing a context refresh, really we only want to refresh the
            // Environment, and there are some toxic listeners (like the
            // LoggingApplicationListener) that affect global static state, so we need a
            // way to switch those off.
            builderApplication.setListeners(filterListeners(builderApplication.getListeners()));
        }
        builder.sources(BootstrapImportSelectorConfiguration.class);
        // 调用SpringApplication的run方法
        final ConfigurableApplicationContext context = builder.run();
        // gh-214 using spring.application.name=bootstrap to set the context id via
        // `ContextIdApplicationContextInitializer` prevents apps from getting the actual
        // spring.application.name
        // during the bootstrap phase.
        context.setId("bootstrap");
        // Make the bootstrap context a parent of the app context
        addAncestorInitializer(application, context);
        // It only has properties in it now that we don't want in the parent so remove
        // it (and it will be added back later)
        // 移除标签
        bootstrapProperties.remove(BOOTSTRAP_PROPERTY_SOURCE_NAME);
        mergeDefaultProperties(environment.getPropertySources(), bootstrapProperties);
        return context;
    }
}
```

```java
public abstract class AbstractApplicationContext {
	@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");

			// Prepare this context for refreshing.
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
				// Invoke factory processors registered as beans in the context.
                // 从spring.factories文件加载自动配置类到上下文
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);
				beanPostProcess.end();

				// Initialize message source for this context.
				initMessageSource();

				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// Check for listener beans and register them.
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
                // 实例化加载的自动配置类
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
				contextRefresh.end();
			}
		}
	}
}
```

AutoConfigurationImportSelector实现了EnableAutoConfiguration注解。会在Spring容器生命周期的 invokeBeanFactoryPostProcessors 阶段（bean工厂后置处理器）调用。
```java
public class AutoConfigurationImportSelector {
    protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
        // 是否开启EnableAutoConfiguration
        if (!isEnabled(annotationMetadata)) {
            return EMPTY_ENTRY;
        }
        // 获得 @EnableAutoConfiguration注解标签上所有的属性值
        AnnotationAttributes attributes = getAttributes(annotationMetadata);
        // 从spring.factories文件里获得 EnableAutoConfiguration key对应的所有自动装配引导类，
        // 并去掉一些重复的（因为有可能用户自定义引入了一些重复的类）排除需要排除的类，
        // 具体操作是通过@SpringBootApplication 注解中的 exclude、excludeName、环境属性中的 spring.autoconfigure.exclude配置
        List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
        configurations = removeDuplicates(configurations);
        Set<String> exclusions = getExclusions(annotationMetadata, attributes);
        checkExcludedClasses(configurations, exclusions);
        configurations.removeAll(exclusions);
        // 根据  spring-autoconfigure-metadata.properties 中配置的规则过虑掉一部分引导类
        configurations = getConfigurationClassFilter().filter(configurations);
        fireAutoConfigurationImportEvents(configurations, exclusions);
        return new AutoConfigurationEntry(configurations, exclusions);
    }
}
```
https://blog.csdn.net/Aqu415/article/details/107676073

application.yml文件点击对应的配置项为什么能够跳转到源码，是通过spring-configuration-metadata.json文件实现的。
https://blog.csdn.net/Aqu415/article/details/113719362