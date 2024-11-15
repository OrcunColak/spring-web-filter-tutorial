package com.colak.springtutorial.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        // Wrap the original request
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper(httpRequest);

        // Log the request body (for demonstration purposes)
        String requestBody = new String(wrappedRequest.getInputStream().readAllBytes());
        System.out.println("Request body before chain: " + requestBody);

        log.info("Logging Request  {} : {}", httpRequest.getMethod(), httpRequest.getRequestURI());

        // Continue the request chain
        chain.doFilter(request, response);

        log.info("Logging Response :{}", response.getContentType());
    }
}
