package com.factus.api.service;

import com.factus.api.dtos.request.FacturaRequest;
import com.factus.api.dtos.response.FacturaLegalDTO;
import com.factus.api.models.Municipalities;
import com.factus.api.models.Paises;
import com.factus.api.models.Tributos;
import com.factus.api.models.UnidadesDeMedida;
import com.factus.api.models.VerYfiltrarFacturas;

import reactor.core.publisher.Mono;

public interface FacturarService {
    Mono<String> getNumberingRanges();

    Mono<FacturaLegalDTO> getCreateFacture(FacturaRequest facture);

    Mono<VerYfiltrarFacturas> getVerFacturasYfiltrar(
        String identification,
        String names,
        String number,
        String prefix,
        String reference_code,
        String status
    );

    Mono<Municipalities> getMunicipiosFiltrar(String name);

    Mono<Tributos> getTributos(String name);

    Mono<Paises> getPaises(String name);

    Mono<UnidadesDeMedida> getUnidadesDeMedida(String name);
}
