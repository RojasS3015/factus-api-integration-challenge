package com.factus.api.presentation.dtos.response;

import lombok.Data;

@Data
public class FacturaResponse {
    
    private String status;
    private String message;
    private DataResponse data;
}
