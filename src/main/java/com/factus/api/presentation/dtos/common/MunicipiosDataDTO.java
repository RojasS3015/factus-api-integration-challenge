package com.factus.api.presentation.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MunicipiosDataDTO {
    private Integer id;
    private String code;
    private String name;
    private String department;
}
