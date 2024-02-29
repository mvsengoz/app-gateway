package com.gateway.app.provider.horoscopexi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gateway.app.provider.CommunicationClient;
import com.gateway.app.provider.IProviderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component("horoscopexi")
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
                  @Value(value = "${horoscopexi.url}") String url,
                  @Value(value = "${horoscopexi.user}") String user,
                  @Value(value = "${horoscopexi.password}") String password,
                  @Value(value = "${horoscopexi.enabled:false}") boolean enabled

    ) {

        this.url = url;
        this.communicationClient = communicationClient;
        this.headers = new HttpHeaders();
        this.enabled = enabled;
        headers.add("x-rapidapi-host", user);
        headers.add("x-rapidapi-key", password);

    }
    @Override
    public String daily(String sign) throws JsonProcessingException {
        return null;
    }

    @Override
    public String weekly(String sign) throws JsonProcessingException {
        return null;
    }

    @Override
    public String monthly(String sign) throws JsonProcessingException {
        return null;
    }

    @Override
    public boolean isActive()  {
        return enabled;
    }
}
