package com.factus.api.infrastructure.exception;


import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.factus.api.presentation.dtos.response.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private final ObjectMapper objectMapper;

    // Spring inyecta el ObjectMapper configurado automáticamente
    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
    // Este método captura los errores de @Valid en WebFlux
    @ExceptionHandler(org.springframework.web.bind.support.WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(org.springframework.web.bind.support.WebExchangeBindException ex) {
        List<String> detalles = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse error = new ErrorResponse(
                "VALIDATION_ERROR",
                "Los datos de entrada no cumplen con los requisitos",
                detalles,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
