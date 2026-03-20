package com.factus.api.app.usecase;

import org.springframework.stereotype.Service;

import com.factus.api.infrastructure.client.FactusClient;

import reactor.core.publisher.Mono;

@Service
public class ObtenerRangosUseCase {

    private final FactusClient factusClient;

    public ObtenerRangosUseCase(FactusClient factusClient) {
        this.factusClient = factusClient;
    }

    public Mono<String> ejecutar() {
        return factusClient.get(
            uri -> uri.path("/v1/numbering-ranges")
                .query("filter[id]&filter[document]&filter[resolution_number]&filter[technical_key]&filter[is_active]")
                .build(),
            String.class,
            "Rangos obtenidos correctamente"
        );
    }
}