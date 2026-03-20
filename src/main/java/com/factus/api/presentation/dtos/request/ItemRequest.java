package com.factus.api.presentation.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank(message = "El código de referencia del producto es obligatorio")
    @JsonProperty("code_reference")
    private String codeReference;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;

    private Float discount;

    @JsonProperty("discount_rate")
    private Float discountRate;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private Float price;

    @NotBlank(message = "La tasa de impuesto (IVA) es obligatoria")
    @JsonProperty("tax_rate")
    private String taxRate;

    @NotNull(message = "La unidad de medida es obligatoria")
    @JsonProperty("unit_measure_id")
    private Integer unitMeasureId;

    @JsonProperty("standard_code_id")
    private Integer standardCodeId;

    @JsonProperty("is_excluded")
    private Integer isExcluded;

    @NotNull(message = "El ID del tributo del producto es obligatorio")
    @JsonProperty("tribute_id")
    private Integer tributeId;

    @Valid // Validar en cascada si hay impuestos retenidos
    @JsonProperty("withholding_taxes")
    private List<WithholdingTaxRequest> withholdingTaxes;
}