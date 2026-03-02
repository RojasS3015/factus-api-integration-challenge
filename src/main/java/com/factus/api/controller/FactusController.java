package com.factus.api.controller;

import com.factus.api.models.FacturaRequest;
import com.factus.api.models.FacturaResponse;
import com.factus.api.models.Municipalities;
import com.factus.api.models.Paises;
import com.factus.api.models.Tributos;
import com.factus.api.models.UnidadesDeMedida;
import com.factus.api.models.VerYfiltrarFacturas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.factus.api.config.OauthToken;
import com.factus.api.service.FacturarServiceImpl;
import com.factus.api.service.OauthService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class FactusController {
    
    private final FacturarServiceImpl factureService;
    private final OauthService oauthService;

    public FactusController(FacturarServiceImpl factureService, OauthService oauthService) {
        this.factureService = factureService;
        this.oauthService = oauthService;
    }

    @GetMapping("/outh/token/obtener")
    public Mono<OauthToken> getLogin(){

        return oauthService.login();
    }

    @GetMapping("/outh/token/refreshtoken/{refreshToken}")
    public Mono<OauthToken> getRefreshTokem(@PathVariable String refreshToken){

        return oauthService.getRefreshToken(refreshToken);
    }

    // Endpoint para obtener los rangos de numeración
    @GetMapping("/v1/numbering-ranges")
    public Mono<String> getNumberingRanges() {

        return factureService.getNumberingRanges();
    }

    //Crear y validar factura
    @PostMapping("/validate")
    public Mono<ResponseEntity<FacturaResponse>> getFacturaCrear(@RequestBody FacturaRequest facture){

        return factureService.getFacture(facture)
                .map(factura -> ResponseEntity
                        .status(HttpStatus.CREATED)
                .body(factura));
    }

    //Ver y Filtrar Factura
    @GetMapping("/filtrar")
    public Mono<VerYfiltrarFacturas> filtrarFacturas(@RequestParam(required = false) String identification,
        @RequestParam(required = false) String names,
        @RequestParam(required = false) String number,
        @RequestParam(required = false) String prefix,
        @RequestParam(required = false) String reference_code,
        @RequestParam(required = false) String status) {
        
        return factureService.getVerFacturasYfiltrar(identification, names, number, prefix, reference_code, status);
        
    }

    //Obtener Municipios
    @GetMapping("/municipios")
    public Mono<Municipalities> getMuncipios(@RequestParam(required = false) String name) {

        return factureService.getMunicipiosFiltrar(name);
        
    }
    
    //Obtener Tributos y Filtrar
    @GetMapping("/tributos")
    public Mono<Tributos> getObtenerTributos(@RequestParam(required = false) String name) {
        
        return factureService.getTributos(name);
    }

    //Obtener Paises y Filtrar
    @GetMapping("/paises")
    public Mono<Paises> getPaisesYfiltrar(@RequestParam(required = false) String name){
        
        return factureService.getPaises(name);
    }   

    //Obtener Unidades de Medida y Filtrar
    @GetMapping("measurement-units")
    public Mono<UnidadesDeMedida> getUnidadesDeMedidaFiltrar(@RequestParam(required = false) String name){

        return factureService.getUnidadesDeMedida(name);
    }
    
    
}
