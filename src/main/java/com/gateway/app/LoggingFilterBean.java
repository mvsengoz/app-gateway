package com.gateway.app;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;

import static java.util.Collections.list;

@Component
public class LoggingFilterBean extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilterBean.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = requestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = responseWrapper(response);

        MDC.put("id", UUID.randomUUID().toString());
        logRequest(requestWrapper);
        chain.doFilter(requestWrapper, responseWrapper);
        logResponse(responseWrapper);
        MDC.remove("id");
        ThreadContext.clearMap();
    }


    private void logRequest(ContentCachingRequestWrapper request) throws IOException
    {

        if (logger.isDebugEnabled())
        {
            logger.debug("Remote Addr   : {} ", request.getRemoteAddr());
            logger.debug("URI           : {} ", request.getRequestURI());
            logger.debug("Method        : {} ", request.getMethod());
            logger.debug("Headers       : {} ", headersToString(list(request.getHeaderNames()), request::getHeader));
            logger.debug("Request body  : {} ", new String(request.getContentAsByteArray()));

        }


    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Status code   : {} ", response.getStatus());
            logger.debug("Headers       : {} ", headersToString(response.getHeaderNames(), response::getHeader));
            logger.debug("Response body : {} ", new String(response.getContentAsByteArray()));

        }


        response.copyBodyToResponse();

    }

    private String headersToString(Collection<String> headerNames, Function<String, String> headerValueResolver) {
        StringBuilder builder = new StringBuilder();
        for (String headerName : headerNames) {
            String header = headerValueResolver.apply(headerName);
            builder.append("%s=%s".formatted(headerName, header)).append(" ");
        }
        return builder.toString();
    }

    private ContentCachingRequestWrapper requestWrapper(ServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper requestWrapper) {
            return requestWrapper;
        }
        return new ContentCachingRequestWrapper((HttpServletRequest) request);
    }

    private ContentCachingResponseWrapper responseWrapper(ServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper responseWrapper) {
            return responseWrapper;
        }
        return new ContentCachingResponseWrapper((HttpServletResponse) response);
    }
}