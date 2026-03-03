package com.factus.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Item {

    @JsonProperty("code_reference")
    private String codeReference;

    private String name;
    private Integer quantity;

    private Float discount;

    @JsonProperty("discount_rate")
    private Float discountRate;

    private Float price;

    @JsonProperty("tax_rate")
    private String taxRate;

    @JsonProperty("unit_measure_id")
    private Integer unitMeasureId;

    @JsonProperty("standard_code_id")
    private Integer standardCodeId;

    @JsonProperty("is_excluded")
    private Integer isExcluded;

    @JsonProperty("tribute_id")
    private Integer tributeId;

    @JsonProperty("withholding_taxes")
    private List<WithholdingTax> withholdingTaxes;
}