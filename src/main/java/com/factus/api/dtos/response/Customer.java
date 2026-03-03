package com.factus.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Customer {

    private String identification;
    private String dv;

    @JsonProperty("graphic_representation_name")
    private String graphicRepresentationName;

    @JsonProperty("trade_name")
    private String tradeName;

    private String company;
    private String names;
    private String address;
    private String email;
    private String phone;

    @JsonProperty("legal_organization")
    private LegalOrganization legalOrganization;

    private Tribute tribute;
    private Municipality municipality;

}