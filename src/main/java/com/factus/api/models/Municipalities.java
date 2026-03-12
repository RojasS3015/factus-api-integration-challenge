package com.factus.api.models;

import java.util.List;

import com.factus.api.dtos.common.MunicipiosDataDTO;

import lombok.Data;

@Data
public class Municipalities {

    private String status;
    private String message;
    private List<MunicipiosDataDTO> data;

    @Data
    public static class MunicipiosData{
        private int id;
        private String code;
        private String name;
        private String department;
    }
    
}
