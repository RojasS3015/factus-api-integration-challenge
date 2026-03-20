package com.factus.api.infrastructure.exception;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class FactusFilters {
    
    public static ExchangeFilterFunction errorHandler(){
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            //Manejo de errores 4xx
            if (response.statusCode().is4xxClientError()) {
                return response.bodyToMono(String.class)
                    .flatMap(errorBody -> {
                        log.warn("⚠️ Error de Cliente en Factus: {}", errorBody);
                        return Mono.error(new FactusClientException(errorBody));
                    });
            }

            //Manejo de errores 5xx
            if (response.statusCode().is5xxServerError()) {
                log.warn("🔥 Error de Servidor en Factus. Status: {}", response.statusCode());
                return Mono.error(new FactusServerException("El servicio de Factus no está disponible"));
            }
            // Si todo está bien (2xx), dejamos pasar la respuesta intacta
            return Mono.just(response);
        });
    }
}