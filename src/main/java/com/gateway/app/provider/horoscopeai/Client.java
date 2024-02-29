package com.gateway.app.provider.horoscopeai;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gateway.app.provider.CommunicationClient;
import com.gateway.app.provider.IProviderClient;
import com.gateway.app.provider.horoscopeai.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component("horoscopeai")
public class Client implements IProviderClient {

    private final CommunicationClient communicationClient;
    private final String url;

    private final static String CONTEXT_DAILY = "/%s/today/general/en";
    private final static String CONTEXT_WEEKLY = "/%s/weekly/general/en";
    private final static String CONTEXT_MONTHLY = "/%s/monthly/general/en";

    public final static String PROVIDER_NAME = "horoscopeai";
    public final boolean enabled ;
    private final HttpHeaders headers ;


    public Client(CommunicationClient communicationClient,
                  @Value(value = "${horoscopeai.url}") String url,
                  @Value(value = "${horoscopeai.user}") String user,
                  @Value(value = "${horoscopeai.password}") String password,
                  @Value(value = "${horoscopeai.enabled:false}") boolean enabled


    ) {

        this.url = url;
        this.communicationClient = communicationClient;
        this.headers = new HttpHeaders();
        this.enabled = enabled;
        headers.add("x-rapidapi-host", user);
        headers.add("x-rapidapi-key", password);


    }

    public String daily(String sign) throws JsonProcessingException {
        var result = communicationClient.apiGet(url.concat(String.format(CONTEXT_DAILY, sign)),
                new HttpEntity(headers), Result.class,"volkan","test");
        return result.general().get(0);
    }

    public String weekly(String sign) throws JsonProcessingException {
        var result = communicationClient.apiGet(url.concat(String.format(CONTEXT_WEEKLY, sign)),
                new HttpEntity(headers), Result.class,"volkan","test");
        return result.general().get(0);
    }

    public String monthly(String sign) throws JsonProcessingException {
        var result = communicationClient.apiGet(url.concat(String.format(CONTEXT_MONTHLY, sign)),
                new HttpEntity(headers), Result.class,"volkan","test");
        return result.general().get(0);
    }
    @Override
    public boolean isActive()  {
        return enabled;
    }

}
