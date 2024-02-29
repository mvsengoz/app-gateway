package com.gateway.app.dto;

import com.gateway.app.mapper.Default;

public class SignDto {

    private String id;
    private String name;
    private String startDate;
    private String endDate;

    public SignDto() {
    }
    @Default
    public SignDto(String id, String name, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SignDto(String name, String startDate, String endDate) {

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
