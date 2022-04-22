package io.github.mountainest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket defaultApi() {
        Docket docket=new Docket(DocumentationType.OAS_30)
//            .enable(true)
            .apiInfo(new ApiInfoBuilder()
                //.title("swagger-bootstrap-ui-demo RESTful APIs")
                .description("# swagger-bootstrap-ui-demo RESTful APIs")
                .termsOfServiceUrl("http://www.xx.com/")
//                .contact("xx@qq.com")
                .version("1.0")
                .build())
            //分组名称
            .groupName("2.X版本")
            .select()
            //这里指定Controller扫描包路径
            .apis(RequestHandlerSelectors.basePackage("io.github"))
            .paths(PathSelectors.any())
            .build();
        return docket;
    }
}
