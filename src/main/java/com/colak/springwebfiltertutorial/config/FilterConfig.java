package com.colak.springwebfiltertutorial.config;

import com.colak.springwebfiltertutorial.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());

        // Apply filter to /api/* paths
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
