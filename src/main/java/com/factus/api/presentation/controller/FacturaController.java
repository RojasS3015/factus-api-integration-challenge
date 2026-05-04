package com.factus.api.presentation.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factus.api.app.usecase.CrearFacturaUseCase;
import com.factus.api.app.usecase.EliminarFacturaUseCase;
import com.factus.api.app.usecase.ObtenerFacturasUseCase;
import com.factus.api.domain.models.EliminateFactura;
import com.factus.api.domain.models.VerYfiltrarFacturas;
import com.factus.api.presentation.dtos.request.FacturaRequest;
import com.factus.api.presentation.dtos.response.FacturaLegalDTO;


import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final CrearFacturaUseCase crearFacturaUseCase;
    private final ObtenerFacturasUseCase obtenerFacturasUseCase;
    private final EliminarFacturaUseCase eliminarFacturaUseCase;

    public FacturaController(
        CrearFacturaUseCase crearFacturaUseCase,
        ObtenerFacturasUseCase obtenerFacturasUseCase,
        EliminarFacturaUseCase eliminarFacturaUseCase
    ) {
        this.crearFacturaUseCase = crearFacturaUseCase;
        this.obtenerFacturasUseCase = obtenerFacturasUseCase;
        this.eliminarFacturaUseCase = eliminarFacturaUseCase;
    }

    @PostMapping
    public Mono<FacturaLegalDTO> crear(@RequestBody FacturaRequest request) {
        return crearFacturaUseCase.ejecutar(request);
    }

    @GetMapping
    public Mono<VerYfiltrarFacturas> listar(
        @RequestParam(required = false) String identification,
        @RequestParam(required = false) String names,
        @RequestParam(required = false) String number,
        @RequestParam(required = false) String prefix,
        @RequestParam(required = false) String reference_code,
        @RequestParam(required = false) String status
    ) {
        return obtenerFacturasUseCase.ejecutar(
            identification, names, number, prefix, reference_code, status
        );
    }

    @DeleteMapping("/{referenceCode}")
    public Mono<EliminateFactura> eliminar(@PathVariable String referenceCode){
        return eliminarFacturaUseCase.ejecutar(referenceCode);

    }
}