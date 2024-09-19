package com.colak.springtutorial.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Order(1)
@WebFilter(filterName = "myFilter", urlPatterns = {"*"})
// @WebFilter(filterName = "myFilter", urlPatterns = {"/a/*", "/b/*"}, description = "Custom filter")
@Slf4j
public class MyCustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyCustomFilter::init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("MyCustomFilter::doFilter");
        // Continue the request chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyCustomFilter::destroy");
    }
}