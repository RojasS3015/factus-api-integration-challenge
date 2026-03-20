package com.factus.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.factus.api.client.FactusClient;
import com.factus.api.dtos.request.FacturaRequest;
import com.factus.api.dtos.response.FacturaResponse;
import com.factus.api.models.Municipalities;
import com.factus.api.models.Paises;
import com.factus.api.models.Tributos;
import com.factus.api.models.UnidadesDeMedida;
import com.factus.api.models.VerYfiltrarFacturas;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FacturarServiceImpl implements FacturarService{

    private final FactusClient factusClient;

    public FacturarServiceImpl(FactusClient factusClient){
        this.factusClient = factusClient;

    }
    
      // Método para obtener los rangos de numeración
    public Mono<String> getNumberingRanges() {
        return factusClient.get(
            uri -> uri.path("/v1/numbering-ranges")
            .query("filter[id]&filter[document]&filter[resolution_number]&filter[technical_key]&filter[is_active]")
            .build(), String.class, "Rango de numeros obtenidos."
        );

    }

    public Mono<FacturaResponse> getCreateFacture(FacturaRequest facture) {
        // Log de intención: Corto y con dato clave
        log.info("Validando factura en Factus. Ref: {}", facture.getReferenceCode());

        return factusClient.post(
            uri -> uri.path("/v1/bills/validate")
            .build(), facture, FacturaResponse.class, "Factura Creada OK"
        );
    }

    //Ver y Filtrar Facturas
    public Mono<VerYfiltrarFacturas> getVerFacturasYfiltrar(
        String identification,
        String names,
        String number,
        String prefix,
        String reference_code,
        String status){

        return factusClient.get(
            uri -> uri.path("/v1/bills")
                        .queryParamIfPresent("filter[identification]", Optional.ofNullable(identification))
                        .queryParamIfPresent("filter[names]", Optional.ofNullable(names))
                        .queryParamIfPresent("filter[number]", Optional.ofNullable(number))
                        .queryParamIfPresent("filter[prefix]", Optional.ofNullable(prefix))
                        .queryParamIfPresent("filter[reference_code]", Optional.ofNullable(reference_code))
                        .queryParamIfPresent("filter[status]", Optional.ofNullable(status))
                    .build(), VerYfiltrarFacturas.class, "Factura obtenida correctamente"
                );
                
    }
    //Obtener municipios y filtrar
    public Mono<Municipalities> getMunicipiosFiltrar(String name){
        
        return factusClient.get(
            uri -> uri.path("/v1/municipalities")
            .queryParamIfPresent("name", Optional.ofNullable(name))
            .build(), Municipalities.class, "Municipios obtenidos correctamente"
        );
    }

    //Obtener Tributos y Flitrar
    public Mono<Tributos> getTributos(String name){
        
        return factusClient.get(
            uri -> uri.path("/v1/tributes/products")
            .queryParamIfPresent("name", Optional.ofNullable(name))
            .build(), Tributos.class, "Tributos obtenidos correctamente"
        );
    }

    //Obtener Paises y Filtrar
    public Mono<Paises> getPaises(String name){

        return factusClient.get(uri -> uri.path("/v1/countries")
                .queryParamIfPresent("name", Optional.ofNullable(name))
                .build(), Paises.class, "Paises obtenidos correctamente"

        );
    }

    //Obtener Unidades de Medida y Filtrar
    public Mono<UnidadesDeMedida> getUnidadesDeMedida(String name){

        return factusClient.get(
            uri -> uri.path("/v1/measurement-units")
            .queryParamIfPresent("name", Optional.ofNullable(name))
            .build(), UnidadesDeMedida.class, "Unidades de medidas obtenidos correctamente"
        );
    }
}