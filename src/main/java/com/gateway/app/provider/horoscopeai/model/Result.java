package com.gateway.app.provider.horoscopeai.model;


import java.util.ArrayList;

public record Result (
         String sign,
         String period,
         String language,
         ArrayList<String> general
) {
}
