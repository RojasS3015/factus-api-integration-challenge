package com.factus.api.presentation.controller;

import com.factus.api.infrastructure.config.AuthToken;
import com.factus.api.presentation.dtos.request.FacturaRequest;
import com.factus.api.presentation.dtos.response.FacturaLegalDTO;
import com.factus.api.app.service.AuthService;
import com.factus.api.app.usecase.CatalogosUseCase;
import com.factus.api.app.usecase.CrearFacturaUseCase;
import com.factus.api.app.usecase.ObtenerFacturasUseCase;
import com.factus.api.app.usecase.ObtenerRangosUseCase;
import com.factus.api.domain.models.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Factus API Controller", description = "Endpoints para la gestión de facturación electrónica e integración con Factus")
public class FactusController {
    
    private final CrearFacturaUseCase crearFacturaUseCase;
    private final ObtenerFacturasUseCase obtenerFacturasUseCase;
    private final CatalogosUseCase catalogosUseCase;
    private final ObtenerRangosUseCase obtenerRangosUseCase;
    private final AuthService authService;

    public FactusController(
    CrearFacturaUseCase crearFacturaUseCase,
    ObtenerFacturasUseCase obtenerFacturasUseCase,
    CatalogosUseCase catalogosUseCase,
    ObtenerRangosUseCase obtenerRangosUseCase,
    AuthService authService
) {
    this.crearFacturaUseCase = crearFacturaUseCase;
    this.obtenerFacturasUseCase = obtenerFacturasUseCase;
    this.catalogosUseCase = catalogosUseCase;
    this.obtenerRangosUseCase = obtenerRangosUseCase;
    this.authService = authService;
}

    @Operation(summary = "Obtener Token de Acceso", description = "Realiza el login inicial para obtener el Bearer Token de Factus.")
    @GetMapping("/outh/token/obtener")
    public Mono<AuthToken> getLogin(){
        return authService.login();
    }

    @Operation(summary = "Refrescar Token", description = "Utiliza el refresh_token para obtener una nueva sesión sin necesidad de credenciales.")
    @GetMapping("/outh/token/refreshtoken/{refreshToken}")
    public Mono<AuthToken> getRefreshTokem(@PathVariable String refreshToken){
        return authService.getRefreshToken(refreshToken);
    }

    @Operation(summary = "Listar rangos de numeración", description = "Obtiene los prefijos y rangos de facturación autorizados por la DIAN.")
    @GetMapping("/v1/numbering-ranges")
    public Mono<String> getNumberingRanges() {
        return obtenerRangosUseCase.ejecutar();
    }

    @Operation(
        summary = "Validar y crear factura", 
        description = "Envía el cuerpo de la factura para validación local y posterior registro en Factus.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Factura procesada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los campos enviados")
        }
    )
    @PostMapping("/validate")
    public Mono<FacturaLegalDTO> getFacturaCrear(@Valid @RequestBody FacturaRequest facture){
        return crearFacturaUseCase.ejecutar(facture);
    }

    @Operation(summary = "Filtrar facturas", description = "Busca facturas emitidas aplicando filtros como identificación o estado.")
    @GetMapping("/filtrar")
    public Mono<VerYfiltrarFacturas> filtrarFacturas(
        @Parameter(description = "Identificación del cliente") @RequestParam(required = false) String identification,
        @Parameter(description = "Nombre o razón social") @RequestParam(required = false) String names,
        @Parameter(description = "Número de factura") @RequestParam(required = false) String number,
        @Parameter(description = "Prefijo de facturación") @RequestParam(required = false) String prefix,
        @Parameter(description = "Código de referencia interno") @RequestParam(required = false) String reference_code,
        @Parameter(description = "Estado (ej: 0 para borrador)") @RequestParam(required = false) String status) {
        
        return obtenerFacturasUseCase.ejecutar(identification, names, number, prefix, reference_code, status);
    }

    @Operation(summary = "Obtener municipios", description = "Consulta el catálogo oficial de municipios de Colombia.")
    @GetMapping("/municipios")
    public Mono<Municipalities> getMuncipios(
        @Parameter(description = "Nombre del municipio para filtrar") @RequestParam(required = false) String name) {
        return catalogosUseCase.municipios(name);
    }
    
    @Operation(summary = "Obtener tributos", description = "Listado de impuestos aplicables (IVA, Retenciones, etc).")
    @GetMapping("/tributos")
    public Mono<Tributos> getObtenerTributos(
        @Parameter(description = "Nombre del tributo") @RequestParam(required = false) String name) {
        return catalogosUseCase.tributos(name);
    }

    @Operation(summary = "Obtener países", description = "Catálogo internacional de países.")
    @GetMapping("/paises")
    public Mono<Paises> getPaisesYfiltrar(
        @Parameter(description = "Nombre del país") @RequestParam(required = false) String name){
        return catalogosUseCase.paises(name);
    }   

    @Operation(summary = "Unidades de medida", description = "Listado de unidades (Unidad, Kilogramo, etc) para los ítems de factura.")
    @GetMapping("measurement-units")
    public Mono<UnidadesDeMedida> getUnidadesDeMedidaFiltrar(
        @Parameter(description = "Nombre de la unidad") @RequestParam(required = false) String name){
        return catalogosUseCase.unidades(name);
    }
}