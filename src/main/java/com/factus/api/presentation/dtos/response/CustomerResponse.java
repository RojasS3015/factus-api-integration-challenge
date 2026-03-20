package com.factus.api.presentation.dtos.response;

import com.factus.api.presentation.dtos.common.MunicipiosDataDTO;
import com.factus.api.presentation.dtos.common.TributeDataDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerResponse {

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

    private TributeDataDTO tribute;
    private MunicipiosDataDTO municipality;

}