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
            .apiInfo(new ApiInfoBuilder()
                .title("接口文档")
                .build())
            .groupName("0.0.1版本");
        return docket;
    }
}
