package com.factus.api.presentation.dtos.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FacturaRequest {

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String document;

    @NotNull(message = "El id del rango de numeracion es obligatorio")
    @JsonProperty("numbering_range_id")
    private Integer numberingRangeId;

    @NotBlank(message = "El codigo de referencia es obligatorio")
    @Size(min = 1, max = 50, message = "El codigo de referencia debe tener entre 1 y 50 caracteres")
    @JsonProperty("reference_code")
    private String referenceCode;

    private String observation;

    @NotNull(message = "El método de pago es obligatorio")
    @JsonProperty("payment_method_code")
    private Integer paymentMethodCode;

    @NotNull(message = "Los datos del establecimiento son obligatorios")
    @Valid
    private Establishment establishment;

    @NotNull(message = "Los datos del cliente son obligatorios")
    @Valid
    private CustomerRequest customer;

    @NotEmpty(message = "La factura debe contener al menos un ítem")
    @Valid
    private List<ItemRequest> items; 
}