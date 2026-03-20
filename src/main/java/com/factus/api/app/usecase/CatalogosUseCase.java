package com.factus.api.app.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.factus.api.domain.models.Municipalities;
import com.factus.api.domain.models.Paises;
import com.factus.api.domain.models.Tributos;
import com.factus.api.domain.models.UnidadesDeMedida;
import com.factus.api.infrastructure.client.FactusClient;

import reactor.core.publisher.Mono;

@Service
public class CatalogosUseCase {

    private final FactusClient factusClient;

    public CatalogosUseCase(FactusClient factusClient) {
        this.factusClient = factusClient;
    }

    public Mono<Municipalities> municipios(String name) {
        return factusClient.get(
            uri -> uri.path("/v1/municipalities")
                .queryParamIfPresent("name", Optional.ofNullable(name))
                .build(),
            Municipalities.class,
            "Municipios obtenidos correctamente"
        );
    }

    public Mono<Tributos> tributos(String name) {
        return factusClient.get(
            uri -> uri.path("/v1/tributes/products")
                .queryParamIfPresent("name", Optional.ofNullable(name))
                .build(),
            Tributos.class,
            "Tributos obtenidos correctamente"
        );
    }

    public Mono<Paises> paises(String name) {
        return factusClient.get(
            uri -> uri.path("/v1/countries")
                .queryParamIfPresent("name", Optional.ofNullable(name))
                .build(),
            Paises.class,
            "Paises obtenidos correctamente"
        );
    }

    public Mono<UnidadesDeMedida> unidades(String name) {
        return factusClient.get(
            uri -> uri.path("/v1/measurement-units")
                .queryParamIfPresent("name", Optional.ofNullable(name))
                .build(),
            UnidadesDeMedida.class,
            "Unidades obtenidas correctamente"
        );
    }
}