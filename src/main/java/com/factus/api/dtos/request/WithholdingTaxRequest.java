package com.factus.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WithholdingTax{

    private String code;

    @JsonProperty("withholding_tax_rate")
    private Float withholdingTaxRate;
}