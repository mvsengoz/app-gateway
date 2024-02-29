package com.gateway.app.provider;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IProviderClient {

    String daily(String sign) throws JsonProcessingException ;

    String weekly(String sign) throws JsonProcessingException ;

    String monthly(String sign) throws JsonProcessingException ;

    boolean isActive() ;
}
