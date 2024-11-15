package com.colak.springtutorial.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// See https://medium.com/@vaibhavpithadiya09/how-to-read-the-http-request-body-multiple-times-in-java-f3fa91fd65c0
// HttpServletRequest only allows you to read the body once.
// Spring’s ContentCachingRequestWrapper seemed like a good solution but
// 1. The body is only cached after the filter chain, so you can’t access it in filters before doFilter is called.
// 2. Even with caching, ServletInputStream still only supports a single read—meaning that once you reach the end, it’s done. Any further reads just return -1.
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] cachedRequest;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        // Read and cache the request body
        this.cachedRequest = request.getInputStream().readAllBytes();
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedServletInputStream(this.cachedRequest);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.cachedRequest)));
    }

    private static class CachedServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream byteArrayInputStream;

        public CachedServletInputStream(byte[] cachedRequest) {
            this.byteArrayInputStream = new ByteArrayInputStream(cachedRequest);
        }

        @Override
        public boolean isFinished() {
            return byteArrayInputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            // Not required for this implementation
        }

        @Override
        public int read() {
            return byteArrayInputStream.read();
        }
    }
}
