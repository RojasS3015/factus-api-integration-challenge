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
        private NumberingRange numberingRange;
        private List<Object> billingPeriod;
        private Bill bill;
        private List<Object> relatedDocuments;
        private List<Item> items;
        private List<WithholdingTax> withholdingTaxes;
        private List<Object> creditNotes;
        private List<Object> debitNotes;

        @lombok.Data
        public static class Company {
            private String urlLogo;
            private String nit;
            private String dv;
            private String company;
            private String name;
            private String graphicRepresentationName;
            private String registrationCode;
            private String economicActivity;
            private String phone;
            private String email;
            private String direction;
            private String municipality;
        }

        @lombok.Data
        public static class Customer {
            private String identification;
            private String dv;
            private String graphicRepresentationName;
            private String tradeName;
            private String company;
            private String names;
            private String address;
            private String email;
            private String phone;
            private LegalOrganization legalOrganization;
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
            private String resolutionNumber;
            private String startDate;
            private String endDate;
            private int months;
        }

        @lombok.Data
        public static class Bill {
            private int id;
            private Document document;
            private OperationType operationType;
            private String orderReference;
            private String number;
            private String referenceCode;
            private int status;
            private int sendEmail;
            private String qr;
            private String cufe;
            private String validated;
            private String discountRate;
            private String discount;
            private double grossValue;
            private double taxableAmount;
            private double taxAmount;
            private double total;
            private String observation;
            private Map<String, String> errors;
            private String createdAt;
            private String paymentDueDate;
            private String qrImage;
            private int isNegotiableInstrument;
            private PaymentForm paymentForm;
            private PaymentMethod paymentMethod;

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
            private String schemeId;
            private String note;
            private String codeReference;
            private String name;
            private String quantity;
            private String discountRate;
            private double discount;
            private double grossValue;
            private String taxRate;
            private double taxableAmount;
            private double taxAmount;
            private double price;
            private int isExcluded;
            private UnitMeasure unitMeasure;
            private StandardCode standardCode;
            private Tribute tribute;
            private double total;
            private List<WithholdingTax> withholdingTaxes;
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
            private String tributeCode;
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