package com.factus.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank(message = "La identificación del cliente es obligatoria")
    private String identification;
    
    private Integer dv; // Dígito de verificación (opcional según el tipo de doc)

    @NotNull(message = "El tipo de documento de identidad es obligatorio")
    @JsonProperty("identification_document_id")
    private Integer identificationDocumentId;

    private String company; // Razón social (opcional si es persona natural)

    @JsonProperty("trade_name")
    private String tradeName;

    @NotNull(message = "La organización legal es obligatoria")
    @JsonProperty("legal_organization_id")
    private Integer legalOrganizationId;

    @NotNull(message = "El tributo del cliente es obligatorio")
    @JsonProperty("tribute_id")
    private Integer tributeId;

    @NotBlank(message = "El nombre o razón social es obligatorio")
    private String names;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido") // 👈 Validación de formato real
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    @NotNull(message = "El municipio es obligatorio")
    @JsonProperty("municipality_id")
    private Integer municipalityId;
}