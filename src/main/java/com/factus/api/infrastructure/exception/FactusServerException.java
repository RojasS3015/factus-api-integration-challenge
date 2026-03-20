package com.factus.api.infrastructure.exception;

// Archivo: FactusServerException.java
public class FactusServerException extends RuntimeException {
    public FactusServerException(String message) {
        super(message);
    }
}