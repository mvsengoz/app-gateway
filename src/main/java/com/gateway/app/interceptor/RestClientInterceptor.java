package com.gateway.app.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

@Component
public class RestClientInterceptor implements ClientHttpRequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException
    {
        //put a unique id to the map

        MDC.put("id", UUID.randomUUID().toString());
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        MDC.remove("id");

        //Add optional additional headers
        //response.getHeaders().add("headerName", "VALUE");

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException
    {
        if (logger.isDebugEnabled())
        {

            logger.debug("URI       : {} ", request.getURI());
            logger.debug("Method    : {} ", request.getMethod());
            logger.debug("Headers   : {} ", request.getHeaders());
            logger.debug("Request body  : {} ", new String(body, "UTF-8"));

        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Status code   : {} ", response.getStatusCode());
            logger.debug("Status text   : {} ", response.getStatusText());
            logger.debug("Headers       : {} ", response.getHeaders());
            logger.debug("Response body : {} ", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));

        }
    }
}
