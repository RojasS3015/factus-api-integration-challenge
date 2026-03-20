package com.factus.api.controller;

import com.factus.api.dtos.request.FacturaRequest;
import com.factus.api.dtos.response.FacturaResponse;
import com.factus.api.models.Municipalities;
import com.factus.api.models.Paises;
import com.factus.api.models.Tributos;
import com.factus.api.models.UnidadesDeMedida;
import com.factus.api.models.VerYfiltrarFacturas;

import org.springframework.web.bind.annotation.RestController;
import com.factus.api.config.AuthToken;
import com.factus.api.service.AuthService;
import com.factus.api.service.FacturarService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class FactusController {
    
    private final FacturarService facturarService;
    private final AuthService oauthService;

    public FactusController(FacturarService facturarService, AuthService oauthService) {
        this.facturarService = facturarService;
        this.oauthService = oauthService;
    }

    @GetMapping("/outh/token/obtener")
    public Mono<AuthToken> getLogin(){

        return oauthService.login();
    }

    @GetMapping("/outh/token/refreshtoken/{refreshToken}")
    public Mono<AuthToken> getRefreshTokem(@PathVariable String refreshToken){

        return oauthService.getRefreshToken(refreshToken);
    }

    // Endpoint para obtener los rangos de numeración
    @GetMapping("/v1/numbering-ranges")
    public Mono<String> getNumberingRanges() {

        return facturarService.getNumberingRanges();
    }

    //Crear y validar factura
    @PostMapping("/validate")
    public Mono<FacturaResponse> getFacturaCrear(@Valid @RequestBody FacturaRequest facture){

        return facturarService.getFacture(facture);
    }

    //Ver y Filtrar Factura
    @GetMapping("/filtrar")
    public Mono<VerYfiltrarFacturas> filtrarFacturas(@RequestParam(required = false) String identification,
        @RequestParam(required = false) String names,
        @RequestParam(required = false) String number,
        @RequestParam(required = false) String prefix,
        @RequestParam(required = false) String reference_code,
        @RequestParam(required = false) String status) {
        
        return facturarService.getVerFacturasYfiltrar(identification, names, number, prefix, reference_code, status);
        
    }

    //Obtener Municipios
    @GetMapping("/municipios")
    public Mono<Municipalities> getMuncipios(@RequestParam(required = false) String name) {

        return facturarService.getMunicipiosFiltrar(name);
        
    }
    
    //Obtener Tributos y Filtrar
    @GetMapping("/tributos")
    public Mono<Tributos> getObtenerTributos(@RequestParam(required = false) String name) {
        
        return facturarService.getTributos(name);
    }

    //Obtener Paises y Filtrar
    @GetMapping("/paises")
    public Mono<Paises> getPaisesYfiltrar(@RequestParam(required = false) String name){
        
        return facturarService.getPaises(name);
    }   

    //Obtener Unidades de Medida y Filtrar
    @GetMapping("measurement-units")
    public Mono<UnidadesDeMedida> getUnidadesDeMedidaFiltrar(@RequestParam(required = false) String name){

        return facturarService.getUnidadesDeMedida(name);
    }
    
    
}
