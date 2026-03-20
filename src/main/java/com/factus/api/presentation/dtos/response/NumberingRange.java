package com.factus.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NumberingRange {

    private String prefix;
    private long from;
    private long to;

    @JsonProperty("resolution_number")
    private String resolutionNumber;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
    
    private int months;
}
