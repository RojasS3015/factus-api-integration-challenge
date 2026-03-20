package com.factus.api.domain.models;

import java.util.List;

import com.factus.api.presentation.dtos.common.TributeDataDTO;

import lombok.Data;

@Data
public class Tributos {
    
    private String status;
    private String message;
    private List<TributeDataDTO> data;
    
}
