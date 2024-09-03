package com.colak.springtutorial.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

// OncePerRequestFilter is an abstract class and Spring guarantees that it will be executed exactly once for each request.
// Sometimes when a request is received, it may be passed on to some other servlet and so those exact same filters will be
// re-executed before reaching this servlet.
@Component
public class OncePerRequestImpl extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            logger.info("Once Per Request Filter start");
            filterChain.doFilter(request, response);
            logger.info("Once Per Request Filter end");
        } catch (Exception exception) {
            // See https://medium.com/@diluckshan/mastering-exception-handling-in-spring-boot-filters-70be7bb940c7
            // for exception handling in Spring Filters
            logger.error("Error during filtering:", exception);

            // Set appropriate status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            PrintWriter printWriter = response.getWriter();
            printWriter.write("An error occurred while processing your request.");
        }
    }
}
