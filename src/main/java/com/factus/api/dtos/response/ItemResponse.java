package com.factus.api.dtos.response;

import com.factus.api.dtos.common.TributeDataDTO;
import com.factus.api.dtos.common.UnitMeasureDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemResponse {

    @JsonProperty("scheme_id")
    private String schemeId;

    private String note;

    @JsonProperty("code_reference")
    private String codeReference;

    private String name;
    private String quantity;

    @JsonProperty("discount_rate")
    private BigDecimal discountRate;

    private BigDecimal discount;

    @JsonProperty("gross_value")
    private BigDecimal grossValue;

    @JsonProperty("tax_rate")
    private String taxRate;

    @JsonProperty("taxable_amount")
    private BigDecimal taxableAmount;

    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    private BigDecimal price;

    @JsonProperty("is_excluded")
    private int isExcluded;

    @JsonProperty("unit_measure")
    private UnitMeasureDTO unitMeasure;

    @JsonProperty("standard_code")
    private StandardCode standardCode;

    private TributeDataDTO tribute;

    private BigDecimal total;

    @JsonProperty("withholding_taxes")
    private List<WithholdingTaxResponse> withholdingTaxes;

    private String mandate;
}