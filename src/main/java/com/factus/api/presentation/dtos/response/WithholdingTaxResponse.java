package com.factus.api.presentation.dtos.response;

import java.util.List;

import lombok.Data;

@Data
public class WithholdingTaxResponse {

    private String tribute_code;
    private String name;
    private double value;
    private List<Rate> rates;
}
