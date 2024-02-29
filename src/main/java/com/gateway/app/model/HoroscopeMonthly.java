package com.gateway.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;


@Document
public class HoroscopeMonthly {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    private String sign;
    private String content;
    private String providerName;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime createdAt;

    public HoroscopeMonthly(String sign, String content, String providerName, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime createdAt) {
        this.sign = sign;
        this.content = content;
        this.providerName = providerName;
        this.startedAt=startedAt;
        this.endedAt=endedAt;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getSign() {
        return sign;
    }

    public String getContent() {
        return content;
    }

    public String getProviderName() {
        return providerName;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
