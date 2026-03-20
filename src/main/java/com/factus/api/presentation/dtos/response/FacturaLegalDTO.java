package com.factus.api.presentation.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Respuesta simplificada con los datos legales de la factura")
public class FacturaLegalDTO {

    @Schema(description = "Número oficial de la factura", example = "SET12345")
    private String numeroFactura;

    @Schema(description = "Código Único de Factura Electrónica (CUFE)", example = "ad78...c4ef")
    private String cufe;

    @Schema(description = "Enlace directo al PDF", example = "https://...")
    private String publicUrl;

    @Schema(description = "Estado de la transacción", example = "Created")
    private String estado;
    
    @Schema(description = "Mensaje de confirmación", example = "Éxito")
    private String mensaje;
    
    // ¡NO AGREGUES MÁS MÉTODOS AQUÍ! Lombok (@Data) ya hace el trabajo.
}