package com.factus.api.dtos.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FacturaRequest {

    private String document;

    @JsonProperty("numbering_range_id")
    private Integer numberingRangeId;

    @JsonProperty("reference_code")
    private String referenceCode;

    private String observation;

    @JsonProperty("payment_method_code")
    private Integer paymentMethodCode;

    private Establishment establishment;
    private CustomerRequest customer;

    private List<ItemRequest> items; 
}