package com.gateway.app.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.app.interceptor.RestClientInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Service
public class CommunicationClient {

    private ObjectMapper mapper = new ObjectMapper();

    private RestClientInterceptor restClientInterceptor;
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommunicationClient(RestClientInterceptor restClientInterceptor,
                               RestTemplate restTemplate) {

        this.restClientInterceptor = restClientInterceptor;
        this.restTemplate = restTemplate;
    }


    public <responseModel, requestModel> void post(String url, requestModel request, Class<responseModel> response) {

        RestClient restClient = RestClient.create();

        ResponseEntity<Void> result = restClient.post()
                .uri(url)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();


    }

    @Retryable(retryFor = {RuntimeException.class},  maxAttempts = 2, backoff = @Backoff(delay = 1000))
    public  <T> T apiGet(String url, HttpEntity httpEntity,
                            Class<T> responseType, Object... urlVariables) {
        try {
            int count = RetrySynchronizationManager.getContext().getRetryCount();
            logger.debug("trying : "+count);
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
                    responseType, urlVariables) ;

            return responseEntity.getBody();

        } catch (Exception e) {
            throw new  RuntimeException(e);
        }

    }



    /*
    @Retryable(retryFor = {RuntimeException.class}, maxAttempts = 10, backoff = @Backoff(delay = 1000))
    public <T> T getBackendResponse(String param1, String param2) {

        int count = RetrySynchronizationManager.getContext().getRetryCount();
        System.out.println("Hello from remote backend!!! retry : "+count);



        throw new RuntimeException("Remote API failed");




    }


    public String getBackendResponseFallback(RuntimeException e, String param1, String param2) {
        System.out.println("Closing App");
        return "";
    }

     */



}
