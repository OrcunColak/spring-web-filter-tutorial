package com.colak.springwebfiltertutorial.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// OncePerRequestFilter is an abstract class and Spring guarantees that it will be executed exactly once for each request.
// Sometimes when a request is received, it may be passed on to some other servlet and so those exact same filters will be
// re-executed before reaching this servlet.
@Component
@Slf4j
public class OncePerRequestImpl extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("Once Per Request Filter start");
        filterChain.doFilter(request,response);
        log.info("Once Per Request Filter end");
    }
}
