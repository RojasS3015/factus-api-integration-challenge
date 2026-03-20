package com.factus.api.controller;

import com.factus.api.dtos.request.FacturaRequest;
import com.factus.api.dtos.response.FacturaResponse;
import com.factus.api.models.*;
import com.factus.api.config.AuthToken;
import com.factus.api.service.AuthService;
import com.factus.api.service.FacturarService;

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
    
    private final FacturarService facturarService;
    private final AuthService oauthService;

    public FactusController(FacturarService facturarService, AuthService oauthService) {
        this.facturarService = facturarService;
        this.oauthService = oauthService;
    }

    @Operation(summary = "Obtener Token de Acceso", description = "Realiza el login inicial para obtener el Bearer Token de Factus.")
    @GetMapping("/outh/token/obtener")
    public Mono<AuthToken> getLogin(){
        return oauthService.login();
    }

    @Operation(summary = "Refrescar Token", description = "Utiliza el refresh_token para obtener una nueva sesión sin necesidad de credenciales.")
    @GetMapping("/outh/token/refreshtoken/{refreshToken}")
    public Mono<AuthToken> getRefreshTokem(@PathVariable String refreshToken){
        return oauthService.getRefreshToken(refreshToken);
    }

    @Operation(summary = "Listar rangos de numeración", description = "Obtiene los prefijos y rangos de facturación autorizados por la DIAN.")
    @GetMapping("/v1/numbering-ranges")
    public Mono<String> getNumberingRanges() {
        return facturarService.getNumberingRanges();
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
    public Mono<FacturaResponse> getFacturaCrear(@Valid @RequestBody FacturaRequest facture){
        return facturarService.getCreateFacture(facture);
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
        
        return facturarService.getVerFacturasYfiltrar(identification, names, number, prefix, reference_code, status);
    }

    @Operation(summary = "Obtener municipios", description = "Consulta el catálogo oficial de municipios de Colombia.")
    @GetMapping("/municipios")
    public Mono<Municipalities> getMuncipios(
        @Parameter(description = "Nombre del municipio para filtrar") @RequestParam(required = false) String name) {
        return facturarService.getMunicipiosFiltrar(name);
    }
    
    @Operation(summary = "Obtener tributos", description = "Listado de impuestos aplicables (IVA, Retenciones, etc).")
    @GetMapping("/tributos")
    public Mono<Tributos> getObtenerTributos(
        @Parameter(description = "Nombre del tributo") @RequestParam(required = false) String name) {
        return facturarService.getTributos(name);
    }

    @Operation(summary = "Obtener países", description = "Catálogo internacional de países.")
    @GetMapping("/paises")
    public Mono<Paises> getPaisesYfiltrar(
        @Parameter(description = "Nombre del país") @RequestParam(required = false) String name){
        return facturarService.getPaises(name);
    }   

    @Operation(summary = "Unidades de medida", description = "Listado de unidades (Unidad, Kilogramo, etc) para los ítems de factura.")
    @GetMapping("measurement-units")
    public Mono<UnidadesDeMedida> getUnidadesDeMedidaFiltrar(
        @Parameter(description = "Nombre de la unidad") @RequestParam(required = false) String name){
        return facturarService.getUnidadesDeMedida(name);
    }
}