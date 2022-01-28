package com.mountain.framework.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationBeanConfig {
    @Bean
    public FilterRegistrationBean config() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new AccessFilter());
        bean.setName("access.filter");

        return bean;
    }
}
