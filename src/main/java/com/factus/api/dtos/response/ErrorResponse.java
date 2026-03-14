package com.factus.api.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse (
    String code,          // Ejemplo: "VAL-422" o "CON-409"
    String message,       // Un resumen del error
    List<String> errors,  // La lista aplanada de errores específicos
    LocalDateTime timestamp //Cuándo ocurrió el error.
) {
    
}
