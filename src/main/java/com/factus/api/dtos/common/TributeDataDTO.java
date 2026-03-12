package com.factus.api.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Oculta campos nulos en el JSON
public class TributeDataDTO {
    private Integer id;
    private String code;
    private String name;
    private String description; // En la factura será null y @JsonInclude lo ocultará
}