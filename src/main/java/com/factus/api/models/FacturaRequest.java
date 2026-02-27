package com.factus.api.models;

import lombok.Data;

import java.util.List;

@Data
public class FacturaRequest {

    private String document;
    private Integer numbering_range_id;
    private String reference_code;
    private String observation;
    private Integer payment_method_code;
    private Establishment establishment;
    private Customer customer;
    private List<Items> items;

    @Data
    public static class Establishment{
        private String name;
        private String address;
        private String phone_number;
        private String email;
        private Integer municipality_id;
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
        private Integer legal_organization_id;
        private Integer tribute_id;
        private Integer identification_document_id;
        private Integer municipality_id;
    }

    @Data
    public static class Items {
        private String code_reference;
        private String name;
        private Integer quantity;
        private Float discount;
        private Float discount_rate;
        private Float price;
        private String tax_rate;
        private Integer unit_measure_id;
        private Integer standard_code_id;
        private Integer is_excluded;
        private Integer tribute_id;
        private List<WithholdingTax> withholding_taxes;
    }

    @Data
    public static class WithholdingTax {
        private String code;
        private Float withholding_tax_rate;
        
    }
}
