package com.factus.api.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factus.api.app.usecase.CatalogosUseCase;
import com.factus.api.domain.models.Municipalities;
import com.factus.api.domain.models.Paises;
import com.factus.api.domain.models.Tributos;
import com.factus.api.domain.models.UnidadesDeMedida;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {

    private final CatalogosUseCase catalogosUseCase;

    public CatalogoController(CatalogosUseCase catalogosUseCase) {
        this.catalogosUseCase = catalogosUseCase;
    }

    @GetMapping("/municipios")
    public Mono<Municipalities> municipios(@RequestParam(required = false) String name) {
        return catalogosUseCase.municipios(name);
    }

    @GetMapping("/tributos")
    public Mono<Tributos> tributos(@RequestParam(required = false) String name) {
        return catalogosUseCase.tributos(name);
    }

    @GetMapping("/paises")
    public Mono<Paises> paises(@RequestParam(required = false) String name) {
        return catalogosUseCase.paises(name);
    }

    @GetMapping("/unidades")
    public Mono<UnidadesDeMedida> unidades(@RequestParam(required = false) String name) {
        return catalogosUseCase.unidades(name);
    }
}