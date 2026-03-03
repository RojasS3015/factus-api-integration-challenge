package com.factus.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Establishment {
    
    private String name;
    private String address;

    @JsonProperty("phone_number")
    private String phoneNumber;
    
    private String email;

    @JsonProperty("municipality_id")
    private Integer municipalityId;
}
