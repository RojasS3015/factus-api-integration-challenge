package com.factus.api.dtos.response;

import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DataResponse {

    private Company company;
    private CustomerResponse customer;

    @JsonProperty("numbering_range")
    private NumberingRange numberingRange;

    @JsonProperty("billing_period")
    private List<Object> billingPeriod;

    private Bill bill;

    @JsonProperty("related_documents")
    private List<Object> relatedDocuments;

    private List<ItemResponse> items;

    @JsonProperty("withholding_taxes")
    private List<WithholdingTaxResponse> withholdingTaxes;

    @JsonProperty("credit_notes")
    private List<Object> creditNotes;

    @JsonProperty("debit_notes")
    private List<Object> debitNotes;
}
