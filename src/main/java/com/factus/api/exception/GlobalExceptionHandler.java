package com.factus.api.exception;


import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.factus.api.dtos.response.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(FactusClientException.class)
    public ResponseEntity<ErrorResponse> handleFactusClientException(FactusClientException ex){
        System.out.println("JSON crudo que llegó de Factus: " + ex.getMessage());
        List<String> detalles = new ArrayList<>();
        //ingresamos la excepcion de FactusClientException.getMesaage
        String errorRaw = ex.getMessage();

        try {
            JsonNode root = objectMapper.readTree(errorRaw);
            JsonNode errorsNode = root.path("data").path("errors");

            // Si el nodo "errors" es un objeto (como el 422: campo -> [error])
            if (errorsNode.isObject()) {
                errorsNode.fields().forEachRemaining(entry -> {
                    entry.getValue().forEach(node -> detalles.add(node.asText()));
                });
                
            
            }// Si el nodo "errors" es una lista (como el 409)
            else if (errorsNode.isArray()) {
                errorsNode.forEach(node -> detalles.add(node.path("message").asText()));

            }else{
                detalles.add(root.path("message").asText("Error desconocido en Factus"));
            }
        } catch (Exception e) {
            detalles.add("No se pudo procesar la respuesta de error original: " + errorRaw);
        }
        ErrorResponse error = new ErrorResponse(
            "FACTUS_CLIENT_ERROR",
            "La API de Factus rechazo la solicitud",
            detalles,
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
