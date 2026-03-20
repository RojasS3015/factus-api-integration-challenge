package com.factus.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Establishment {
    
    @NotBlank(message = "El nombre del establecimiento es obligatorio")
    private String name;

    @NotBlank(message = "La dirección del establecimiento es obligatoria")
    private String address;

    @NotBlank(message = "El número de teléfono del establecimiento es obligatorio")
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    @NotBlank(message = "El email del establecimiento es obligatorio")
    @Email(message = "El formato del email del establecimiento no es válido")
    private String email;

    @NotNull(message = "El ID del municipio del establecimiento es obligatorio")
    @JsonProperty("municipality_id")
    private Integer municipalityId;
}
