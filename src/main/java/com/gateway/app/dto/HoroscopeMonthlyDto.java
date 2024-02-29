package com.gateway.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gateway.app.mapper.Default;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class HoroscopeMonthlyDto {
    private String id;
    private String sign;
    private String signStart;
    private String signEnd;
    private String content;
    private String providerName;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime createdAt;

    @Default
    public HoroscopeMonthlyDto(String id, String sign, String content, String providerName, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime createdAt) {
        this.id = id ;
        this.sign = sign;
        this.content = content;
        this.providerName = providerName;
        this.startedAt=startedAt;
        this.endedAt=endedAt;
        this.createdAt = createdAt;
    }

    public HoroscopeMonthlyDto(String sign, String content, String providerName, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime createdAt) {
        this.sign = sign;
        this.content = content;
        this.providerName = providerName;
        this.startedAt=startedAt;
        this.endedAt=endedAt;
        this.createdAt = createdAt;
    }

    public HoroscopeMonthlyDto(String sign, String signStart, String signEnd, String content, String providerName, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime createdAt) {
        this.sign = sign;
        this.signStart = signStart;
        this.signEnd = signEnd;
        this.content = content;
        this.providerName = providerName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
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

    public String getSignStart() {
        return signStart;
    }

    public String getSignEnd() {
        return signEnd;
    }

    public HoroscopeMonthlyDto withSignStartAndEndDate(String signStart, String signEnd) {
        return new HoroscopeMonthlyDto(this.sign,
                signStart,
                signEnd,
                this.content,
                this.providerName,
                this.startedAt,
                this.endedAt,
                this.createdAt);
    }
}
