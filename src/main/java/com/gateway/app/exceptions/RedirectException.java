package com.gateway.app.exceptions;

public class RedirectException extends RuntimeException{
    public RedirectException(String msg){
        super(msg);
    }
}
