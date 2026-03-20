package com.factus.api.models;

import java.util.List;

import lombok.Data;

@Data
public class Paises {
    private String status;
    private String message;
    private List<PaisesData> data;

    @Data
    public static class PaisesData{
        private int id;
        private String code;
        private String name;
    }
}
