package com.factus.api.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.factus.api.models.FacturaRequest;
import com.factus.api.models.FacturaResponse;
import com.factus.api.models.Municipalities;
import com.factus.api.models.Paises;
import com.factus.api.models.Tributos;
import com.factus.api.models.UnidadesDeMedida;
import com.factus.api.models.VerYfiltrarFacturas;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class FacturarServiceImpl {

    private WebClient webClient;

    public FacturarServiceImpl(WebClient webClient){
        this.webClient = webClient;

    }
    
      // Método para obtener los rangos de numeración
    public Mono<String> getNumberingRanges() {
        return webClient.get()
                .uri("/v1/numbering-ranges?filter[id]&filter[document]&filter[resolution_number]&filter[technical_key]&filter[is_active]")
                .retrieve()
                .bodyToMono(String.class);  // Asumimos que la respuesta será un String (JSON)
    }


    public Mono<FacturaResponse> getFacture(FacturaRequest facture) {

        return webClient.post()
                .uri("/v1/bills/validate")
                .bodyValue(facture)
                .exchangeToMono(response -> {

                    HttpStatus status = (HttpStatus) response.statusCode();

                    // 🟥 Manejo de errores
                    if (status.is4xxClientError() || status.is5xxServerError()) {
                        return response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    System.out.println("❌ FACTUS ERROR [" + status + "]");
                                    System.out.println(errorBody);
                                    return Mono.error(
                                            new RuntimeException("Factus error " + status + ": " + errorBody)
                                    );
                                });
                    }

                    // 🟩 Respuesta OK - Ahora deserializa a DocumentoFacturaResponse
                    return response.bodyToMono(FacturaResponse.class)
                            .doOnNext(resp -> {
                                try {
                                    ObjectMapper mapper = new ObjectMapper();
                                    System.out.println("✅ FACTUS SANDBOX RESPUESTA:");
                                    System.out.println(
                                            mapper.writerWithDefaultPrettyPrinter()
                                                    .writeValueAsString(resp)
                                    );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                });
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
                //.doOnSubscribe(sub -> log.info("=== Iniciando búsqueda filtrada de facturas ==="))
                .doOnSubscribe(sub -> log.info("🔍 Consultando facturas - Filtros: Identificación={}, Número={}, Estado={}", 
                identification, number, status))
                .doOnNext(res -> {
                    //Si el modelo tiene datos anidados: res.getObjeto().getLista().size().
                    if(res.getData() != null && res.getData().getData() != null){
                        int total = res.getData().getData().size();
                        log.info("✅ Consulta exitosa: Se recuperaron {} facturas.", total);
                    }else{
                        log.warn("⚠️ La API respondió correctamente pero la lista de facturas está vacía.");
                    }
                });
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
                        .doOnSubscribe(sub -> log.info("Buscando municipios..... name={}", name))
                        .doOnNext(res -> {
                            // 1. Llamamos a getData() una sola vez porque ahí está nuestra lista
                            if (res.getData() != null) {
                                // 2. Obtenemos el tamaño de la lista directamente
                                int total = res.getData().size();

                                log.info("Se consulto con exito los municipios: {} municipios.", total);
                            }else{
                                log.warn("La api respondio correctamente pero los municipios estan vacios");
                            }
                        });
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
                        .doOnSubscribe(sub -> log.info("Buscando tributos..... name={}", name))
                        .doOnNext(res -> {
                            if (res.getData() != null) {
                                int total = res.getData().size();

                                log.info("Se Consulto con exito los tributos: {} tributos.", total);
                            }else{
                                log.warn("La api respondio correctamente pero los tributos estan vacios");
                            }
                        });
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
                            .doOnSubscribe(sub -> log.info("Buscando paises..... name={}", name))
                            .doOnNext(res -> {
                                if (res.getData() != null) {

                                    int total = res.getData().size();
                                    log.info("Se consulto con exito los paises: {} paises", total);
                                }else{
                                    log.warn("La api respondio correctamente pero los atributos estan vacios");
                                }
                            });
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
                        .doOnSubscribe(sub -> log.info("Buscando Unidades de medida.... name={}", name))
                        .doOnNext(res -> {
                            if (res.getData() != null) {
                                int total = res.getData().size();
                                log.info("Se consulto con exito las unidades de medida: {} Unidades de medida", total);
                            }else{
                                log.warn("La api respondio correctamente pero los atributos estan vacios");
                            }
                        });
    }
}
