package com.factus.api.models;

import java.util.List;

import lombok.Data;

@Data
public class UnidadesDeMedida {
    private String status;
    private String message;
    private List<UnidadesDeMedidaData> data;

    @Data
    public static class UnidadesDeMedidaData{
        private int id;
        private String code;
        private String name;
    }
}
