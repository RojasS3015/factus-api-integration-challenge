package com.factus.api.presentation.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Company {

    @JsonProperty("url_logo")
    private String urlLogo;

    private String nit;
    private String dv;
    private String company;
    private String name;

    @JsonProperty("graphic_representation_name")
    private String graphicRepresentationName;

    @JsonProperty("registration_code")
    private String registrationCode;

    @JsonProperty("economic_activity")
    private String economicActivity;

    private String phone;
    private String email;
    private String direction;
    private String municipality;
}
