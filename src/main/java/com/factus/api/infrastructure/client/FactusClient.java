package com.factus.api.infrastructure.client;

import java.net.URI;
import java.time.Duration;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class FactusClient {
    private final WebClient webClient;

    public FactusClient(WebClient webClient) {
        this.webClient = webClient;
    }

    // Centraliza los GET
    public <T> Mono<T> get(Function<UriBuilder, URI> uriFunction, Class<T> responseType, String logMessage) {
        return webClient.get()
                .uri(uriFunction)
                .retrieve()
                .bodyToMono(responseType)
                .transform(mono -> applyCommonBehavior(mono, logMessage));
    }

    // Centraliza los POST
    public <T, R> Mono<T> post(Function <UriBuilder, URI> uriFunction, R body, Class<T> responseType, String logMessage) {
        return webClient.post()
                .uri(uriFunction)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseType)
                .transform(mono -> applyCommonBehavior(mono, logMessage));
    }

    public <T> Mono<T> delete(Function<UriBuilder, URI> urFunction, Class<T> responseType, String logMessage){
        return webClient.delete()
                .uri(urFunction)
                .retrieve()
                .bodyToMono(responseType)
                .transform(mono -> applyCommonBehavior(mono, logMessage));
    }

    // AQUÍ ESTÁ EL SECRETO: Una sola vez para toda la app
    private <T> Mono<T> applyCommonBehavior(Mono<T> mono, String successMessage) {
        return mono
                .timeout(Duration.ofSeconds(5))
                .doOnNext(res -> log.info("✅ {}", successMessage))
                .doOnError(error -> log.error("❌ Fallo en Factus: {}", error.getMessage()));
    }
}
