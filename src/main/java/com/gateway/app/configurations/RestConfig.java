package com.gateway.app.configurations;

import com.gateway.app.interceptor.RestClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestConfig {

    @Autowired
    RestClientInterceptor restClientInterceptor;
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template =  new RestTemplate(new BufferingClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory()
        ));
        template.setInterceptors(List.of(restClientInterceptor));
        return template;
    }

}