package com.factus.api.dtos.response;

import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DataResponse {

    private Company company;
    private Customer customer;

    @JsonProperty("numbering_range")
    private NumberingRange numberingRange;

    @JsonProperty("billing_period")
    private List<Object> billingPeriod;

    private Bill bill;

    @JsonProperty("related_documents")
    private List<Object> relatedDocuments;

    private List<Item> items;

    @JsonProperty("withholding_taxes")
    private List<WithholdingTax> withholdingTaxes;

    @JsonProperty("credit_notes")
    private List<Object> creditNotes;

    @JsonProperty("debit_notes")
    private List<Object> debitNotes;
}
