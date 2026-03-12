package com.factus.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerRequest {

    private String identification;
    private Integer dv;

    @JsonProperty("identification_document_id")
    private Integer identificationDocumentId;

    private String company;

    @JsonProperty("trade_name")
    private String tradeName;

    @JsonProperty("legal_organization_id")
    private Integer legalOrganizationId;

    @JsonProperty("tribute_id")
    private Integer tributeId;

    private String names;
    private String address;
    private String email;
    private String phone;

    @JsonProperty("municipality_id")
    private Integer municipalityId;
}