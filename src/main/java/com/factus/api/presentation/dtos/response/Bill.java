package com.factus.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class Bill {

    private int id;

    private Document document;

    @JsonProperty("operation_type")
    private OperationType operationType;

    @JsonProperty("order_reference")
    private String orderReference;

    private String number;

    @JsonProperty("reference_code")
    private String referenceCode;

    private int status;

    @JsonProperty("send_email")
    private int sendEmail;

    private String qr;
    private String cufe;
    private String validated;

    @JsonProperty("discount_rate")
    private BigDecimal discountRate;

    private BigDecimal discount;

    @JsonProperty("gross_value")
    private BigDecimal grossValue;

    @JsonProperty("taxable_amount")
    private BigDecimal taxableAmount;

    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    private BigDecimal total;

    private String observation;

    private Map<String, String> errors;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("payment_due_date")
    private String paymentDueDate;

    @JsonProperty("qr_image")
    private String qrImage;

    @JsonProperty("is_negotiable_instrument")
    private int isNegotiableInstrument;

    @JsonProperty("payment_form")
    private PaymentForm paymentForm;

    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    // Cambia esto en Bill.java
    @JsonProperty("public_url")
    private String publicUrl;
}