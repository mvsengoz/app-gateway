package com.gateway.app.dto;

import com.gateway.app.mapper.Default;

import java.time.LocalDateTime;

public class MessageDto {

    private String id;
    private String name;
    private String email;
    private String message;
    private LocalDateTime createdAt;


    public MessageDto() {
    }
    @Default
    public MessageDto(String id, String name, String email, String message, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.createdAt = createdAt;
    }

    public MessageDto(String name, String email, String message, LocalDateTime createdAt) {

        this.name = name;
        this.email = email;
        this.message = message;
        this.createdAt = createdAt;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
