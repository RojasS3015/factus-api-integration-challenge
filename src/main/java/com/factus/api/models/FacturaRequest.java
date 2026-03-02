package com.factus.api.models;

import lombok.Data;

import java.util.List;

@Data
public class FacturaRequest {

    private String document;
    private Integer numberingRangeId;
    private String referenceCode;
    private String observation;
    private Integer paymentMethodCode;
    private Establishment establishment;
    private Customer customer;
    private List<Items> items;

    @Data
    public static class Establishment{
        private String name;
        private String address;
        private String phoneNumber;
        private String email;
        private Integer municipalityId;
    }

    @Data
    public static class Customer {
        private String identification;
        private Integer dv;
        private String company;
        private String trade_name;
        private String names;
        private String address;
        private String email;
        private String phone;
        private Integer legalOrganizationId;
        private Integer tributeId;
        private Integer identificationDocumentId;
        private Integer municipalityId;
    }

    @Data
    public static class Items {
        private String codeReference;
        private String name;
        private Integer quantity;
        private Float discount;
        private Float discountRate;
        private Float price;
        private String taxRate;
        private Integer unitMeasureId;
        private Integer standardCodeId;
        private Integer isExcluded;
        private Integer tributeId;
        private List<WithholdingTax> withholdingTaxes;
    }

    @Data
    public static class WithholdingTax {
        private String code;
        private Float withholdingTaxRate;
        
    }
}
