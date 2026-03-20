package com.factus.api.models;

import java.util.List;

import com.factus.api.dtos.common.UnitMeasureDTO;

import lombok.Data;

@Data
public class UnidadesDeMedida {
    private String status;
    private String message;
    private List<UnitMeasureDTO> data;

}
