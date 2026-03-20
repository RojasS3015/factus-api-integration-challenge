package com.factus.api.app.usecase;

import org.springframework.stereotype.Service;

import com.factus.api.infrastructure.client.FactusClient;
import com.factus.api.presentation.dtos.request.FacturaRequest;
import com.factus.api.presentation.dtos.response.FacturaLegalDTO;
import com.factus.api.presentation.dtos.response.FacturaResponse;

import reactor.core.publisher.Mono;

@Service
public class CrearFacturaUseCase {

    private final FactusClient factusClient;

    public CrearFacturaUseCase(FactusClient factusClient) {
        this.factusClient = factusClient;
    }

    public Mono<FacturaLegalDTO> ejecutar(FacturaRequest facture) {

        return factusClient.post(
            uri -> uri.path("/v1/bills/validate").build(), 
            facture, 
            FacturaResponse.class, 
            "Factura Creada OK"
        )
        .map(response -> {
            FacturaLegalDTO legal = new FacturaLegalDTO();

            if (response.getData() != null && response.getData().getBill() != null) {
                var bill = response.getData().getBill();
                legal.setNumeroFactura(bill.getNumber());
                legal.setCufe(bill.getCufe());
                legal.setPublicUrl(bill.getPublicUrl());
            }

            legal.setEstado(response.getStatus());
            legal.setMensaje(response.getMessage());

            return legal;
        });
    }
}
