package com.factus.api.app.usecase;


import org.springframework.stereotype.Service;

import com.factus.api.domain.models.EliminateFactura;
import com.factus.api.infrastructure.client.FactusClient;

import reactor.core.publisher.Mono;

@Service
public class EliminarFacturaUseCase {
    
    private final FactusClient factusClient;

    public EliminarFacturaUseCase(FactusClient factusClient){
        this.factusClient = factusClient;
    }

    public Mono<EliminateFactura> ejecutar(String referenceCode){

        return factusClient.delete(
            uri -> uri.path("/v1/bills/destroy/reference/{reference_code}")
            .build(referenceCode) ,
        EliminateFactura.class, 
        "Factura Eliminada Exitosamente");
    }

}
