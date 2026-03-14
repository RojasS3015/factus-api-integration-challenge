package com.factus.api.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    private final WebClient webClient;

    public FacturarServiceImpl(WebClient webClient){
        this.webClient = webClient;

    }
    
      // Método para obtener los rangos de numeración
    public Mono<String> getNumberingRanges() {
        return webClient.get()
                .uri("/v1/numbering-ranges?filter[id]&filter[document]&filter[resolution_number]&filter[technical_key]&filter[is_active]")
                .retrieve()
            .bodyToMono(String.class)
            .timeout(Duration.ofSeconds(5))
            .doOnNext(res -> log.info("✅ Rangos obtenidos correctamente"))
            .doOnError(error -> log.error("❌ Fallo total en la consulta: {}", error.getMessage()));

    }

    public Mono<FacturaResponse> getFacture(FacturaRequest facture) {
        // Log de intención: Corto y con dato clave
    log.info("Validando factura en Factus. Ref: {}", facture.getReferenceCode());
    return webClient.post()
            .uri("/v1/bills/validate")
            .bodyValue(facture)
            .retrieve()
            .bodyToMono(FacturaResponse.class)
            .timeout(Duration.ofSeconds(5))

            // 🟩 Log de éxito: Confirmación breve
            .doOnNext(res -> log.info("✅ Validación exitosa para: {}", facture.getReferenceCode()))

            .doOnError(error ->
                    log.error("Error validando factura {} : {}",
                            facture.getReferenceCode(),
                            error.getMessage())
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

        return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1/bills")
                        .queryParamIfPresent("filter[identification]", Optional.ofNullable(identification))
                        .queryParamIfPresent("filter[names]", Optional.ofNullable(names))
                        .queryParamIfPresent("filter[number]", Optional.ofNullable(number))
                        .queryParamIfPresent("filter[prefix]", Optional.ofNullable(prefix))
                        .queryParamIfPresent("filter[reference_code]", Optional.ofNullable(reference_code))
                        .queryParamIfPresent("filter[status]", Optional.ofNullable(status))
                    .build())
                .retrieve()
                .bodyToMono(VerYfiltrarFacturas.class)
                .timeout(Duration.ofSeconds(5))
                .doOnNext(res -> log.info("✅ Facturas obtenidas correctamente"))
                .doOnError(error -> log.error("❌ Fallo total en la consulta: {}", error.getMessage()));

    }
    //Obtener municipios y filtrar
    public Mono<Municipalities> getMunicipiosFiltrar(String name){
        
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                            .path("/v1/municipalities")
                            .queryParamIfPresent("name", Optional.ofNullable(name))
                        .build())
                        .retrieve()
                        .bodyToMono(Municipalities.class)
                        .timeout(Duration.ofSeconds(5))
                        .doOnNext(res -> log.info("✅ Municipios obtenidos correctamente"))
                        .doOnError(error -> log.error("❌ Fallo total en la consulta: {}", error.getMessage()));
    }

    //Obtener Tributos y Flitrar
    public Mono<Tributos> getTributos(String name){
        
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                            .path("/v1/tributes/products")
                            .queryParamIfPresent("name", Optional.ofNullable(name))
                        .build())
                        .retrieve()
                        .bodyToMono(Tributos.class)
                        .timeout(Duration.ofSeconds(5))
                        .doOnNext(res -> log.info("✅ Tributos obtenidos correctamente"))
                        .doOnError(error -> log.error("❌ Fallo total en la consulta: {}", error.getMessage()));
    }

    //Obtener Paises y Filtrar
    public Mono<Paises> getPaises(String name){

        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                            .path("/v1/countries")
                            .queryParamIfPresent("name", Optional.ofNullable(name))
                            .build())
                            .retrieve()
                        .bodyToMono(Paises.class)
                        .timeout(Duration.ofSeconds(5))
                        .doOnNext(res -> log.info("✅ Paises obtenidos correctamente"))
                        .doOnError(error -> log.error("❌ Fallo total en la consulta: {}", error.getMessage()));
    }

    //Obtener Unidades de Medida y Filtrar
    public Mono<UnidadesDeMedida> getUnidadesDeMedida(String name){

        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                            .path("/v1/measurement-units")
                            .queryParamIfPresent("name", Optional.ofNullable(name))
                            .build())
                        .retrieve()
                        .bodyToMono(UnidadesDeMedida.class)
                        .timeout(Duration.ofSeconds(5))
                        .doOnNext(res -> log.info("✅ Unidades de medidas obtenidos correctamente"))
                        .doOnError(error -> log.error("❌ Fallo total en la consulta: {}", error.getMessage()));
    }
}
