package com.factus.api.app.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.factus.api.domain.models.VerYfiltrarFacturas;
import com.factus.api.infrastructure.client.FactusClient;

import reactor.core.publisher.Mono;

@Service
public class ObtenerFacturasUseCase {

    private final FactusClient factusClient;

    public ObtenerFacturasUseCase(FactusClient factusClient) {
        this.factusClient = factusClient;
    }

    public Mono<VerYfiltrarFacturas> ejecutar(
        String identification,
        String names,
        String number,
        String prefix,
        String reference_code,
        String status
    ) {

        return factusClient.get(
            uri -> uri.path("/v1/bills")
                .queryParamIfPresent("filter[identification]", Optional.ofNullable(identification))
                .queryParamIfPresent("filter[names]", Optional.ofNullable(names))
                .queryParamIfPresent("filter[number]", Optional.ofNullable(number))
                .queryParamIfPresent("filter[prefix]", Optional.ofNullable(prefix))
                .queryParamIfPresent("filter[reference_code]", Optional.ofNullable(reference_code))
                .queryParamIfPresent("filter[status]", Optional.ofNullable(status))
                .build(),
            VerYfiltrarFacturas.class,
            "Factura obtenida correctamente"
        );
    }
}