package com.factus.api.models;

import lombok.Data;
import java.util.List;
import java.util.Map;


@Data
public class FacturaResponse {

    private String status;
    private String message;
    private DataResponse data;


    @lombok.Data
    public static class DataResponse {
        private Company company;
        private Customer customer;
        private NumberingRange numbering_range;
        private List<Object> billing_period;
        private Bill bill;
        private List<Object> related_documents;
        private List<Item> items;
        private List<WithholdingTax> withholding_taxes;
        private List<Object> credit_notes;
        private List<Object> debit_notes;

        @lombok.Data
        public static class Company {
            private String url_logo;
            private String nit;
            private String dv;
            private String company;
            private String name;
            private String graphic_representation_name;
            private String registration_code;
            private String economic_activity;
            private String phone;
            private String email;
            private String direction;
            private String municipality;
        }

        @lombok.Data
        public static class Customer {
            private String identification;
            private String dv;
            private String graphic_representation_name;
            private String trade_name;
            private String company;
            private String names;
            private String address;
            private String email;
            private String phone;
            private LegalOrganization legal_organization;
            private Tribute tribute;
            private Municipality municipality;

            @lombok.Data
            public static class LegalOrganization {
                private int id;
                private String code;
                private String name;
            }

            @lombok.Data
            public static class Tribute {
                private int id;
                private String code;
                private String name;
            }

            @lombok.Data
            public static class Municipality {
                private int id;
                private String code;
                private String name;
            }
        }

        @lombok.Data
        public static class NumberingRange {
            private String prefix;
            private long from;
            private long to;
            private String resolution_number;
            private String start_date;
            private String end_date;
            private int months;
        }

        @lombok.Data
        public static class Bill {
            private int id;
            private Document document;
            private OperationType operation_type;
            private String order_reference;
            private String number;
            private String reference_code;
            private int status;
            private int send_email;
            private String qr;
            private String cufe;
            private String validated;
            private String discount_rate;
            private String discount;
            private double gross_value;
            private double taxable_amount;
            private double tax_amount;
            private double total;
            private String observation;
            private Map<String, String> errors;
            private String created_at;
            private String payment_due_date;
            private String qr_image;
            private int is_negotiable_instrument;
            private PaymentForm payment_form;
            private PaymentMethod payment_method;

            @lombok.Data
            public static class Document {
                private String code;
                private String name;
            }

            @lombok.Data
            public static class OperationType {
                private String code;
                private String name;
            }

            @lombok.Data
            public static class PaymentForm {
                private String code;
                private String name;
            }

            @lombok.Data
            public static class PaymentMethod {
                private String code;
                private String name;
            }
        }

        @lombok.Data
        public static class Item {
            private String scheme_id;
            private String note;
            private String code_reference;
            private String name;
            private String quantity;
            private String discount_rate;
            private double discount;
            private double gross_value;
            private String tax_rate;
            private double taxable_amount;
            private double tax_amount;
            private double price;
            private int is_excluded;
            private UnitMeasure unit_measure;
            private StandardCode standard_code;
            private Tribute tribute;
            private double total;
            private List<WithholdingTax> withholding_taxes;
            private String mandate;

            @lombok.Data
            public static class UnitMeasure {
                private int id;
                private String code;
                private String name;
            }

            @lombok.Data
            public static class StandardCode {
                private int id;
                private String code;
                private String name;
            }

            @lombok.Data
            public static class Tribute {
                private int id;
                private String code;
                private String name;
            }
        }

        @lombok.Data
        public static class WithholdingTax {
            private String tribute_code;
            private String name;
            private double value;
            private List<Rate> rates;

            @lombok.Data
            public static class Rate {
                private String code;
                private String name;
                private String rate;
            }
        }
    }
}