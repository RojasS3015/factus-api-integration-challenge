package com.factus.api.models;

import java.util.List;

import lombok.Data;

@Data
public class Tributos {
    
    private String status;
    private String message;
    private List<TributeData> data;

    @Data
    public static class TributeData{
        private Integer id;
        private String code;
        private String name;
        private String description;
    }

    
}
