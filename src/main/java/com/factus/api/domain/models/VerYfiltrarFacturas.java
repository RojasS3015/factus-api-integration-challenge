package com.factus.api.domain.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class VerYfiltrarFacturas {

    private String status;
    private String message;
    private DataResponse data;

    @Data
    public static class DataResponse {
        private List<FacturaData> data;
        private Pagination pagination;
    }

    @Data
    public static class FacturaData {
        private Integer id;
        private Document document;
        private OperationType operation_type;
        private String number;
        private String api_client_name;
        private String reference_code;
        private String identification;
        private String graphic_representation_name;
        private String company;
        private String trade_name;
        private String names;
        private String email;
        private Double total;
        private Integer status;
        private Map<String, String> errors; // Mapeamos los errores como un Map de String a String
        private Integer send_email;
        private Integer has_claim;
        private Integer is_negotiable_instrument;
        private PaymentForm payment_form;
        private String created_at;
        private List<Object> credit_notes; // Lista vacía
        private List<Object> debit_notes;  // Lista vacía
    }

    @Data
    public static class Document {
        private String code;
        private String name;
    }

    @Data
    public static class OperationType {
        private String code;
        private String name;
    }

    @Data
    public static class PaymentForm {
        private String code;
        private String name;
    }

    @Data
    public static class Pagination {
        private Integer total;
        private Integer per_page;
        private Integer current_page;
        private Integer last_page;
        private Integer from;
        private Integer to;
        private List<Link> links;
    }

    @Data
    public static class Link {
        private String url;
        private String label;
        private Boolean active;
    }
}

