package io.github.mountainest.config;

import io.github.mountainest.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationBeanConfig {
    @Bean
    public FilterRegistrationBean config() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CommonFilter());
        bean.setName("common.filter");
        return bean;
    }
}
