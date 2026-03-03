package com.factus.api.dtos.response;

import lombok.Data;

@Data
public class FacturaResponse {
    
    private String status;
    private String message;
    private DataResponse data;
}
