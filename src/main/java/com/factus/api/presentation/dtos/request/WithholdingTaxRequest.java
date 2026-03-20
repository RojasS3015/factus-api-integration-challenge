package com.factus.api.presentation.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WithholdingTaxRequest{

    private String code;

    @JsonProperty("withholding_tax_rate")
    private Float withholdingTaxRate;
}