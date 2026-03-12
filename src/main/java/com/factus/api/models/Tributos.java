package com.factus.api.models;

import java.util.List;

import com.factus.api.dtos.common.TributeDataDTO;

import lombok.Data;

@Data
public class Tributos {
    
    private String status;
    private String message;
    private List<TributeDataDTO> data;
    
}
