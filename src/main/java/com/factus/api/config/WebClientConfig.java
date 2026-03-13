package com.factus.api.config;

import lombok.RequiredArgsConstructor;
import reactor.netty.http.client.HttpClient;

import org.springframework.http.HttpHeaders;  // Para HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.time.Duration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.factus.api.security.TokenContextHolder;
import com.factus.api.service.AuthService;

@Configuration
@EnableConfigurationProperties(FactusProperties.class)
@RequiredArgsConstructor
public class WebClientConfig {

    //Metodo 2
    /*@Bean
    public WebClient webClient() {
        WebClient.Builder builder = WebClient.builder()
                        .baseUrl(apiconfig.getBaseUrl())
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // Si tienes un token de acceso, lo agregas al header
        if(apiconfig.getTokenAccess() != null && !apiconfig.getTokenAccess().isEmpty()){
            builder.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiconfig.getTokenAccess());
        }

        return builder.build();
    }*/
    /* 
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiconfig.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiconfig.getTokenAccess())
                .build();
    }
    */

    @Bean
    public WebClient webClient(FactusProperties apiconfig, TokenContextHolder authHolder, AuthService authService) {
         HttpClient httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(8));

        return WebClient.builder()
                .baseUrl(apiconfig.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                // 1. ELIMINAMOS el header estático (el que tenías con apiconfig.getTokenAccess())
                // 2. AGREGAMOS el filtro inteligente
                .filter(autoAuthFilter(authHolder, authService)) 
                .build();
    }

    // Este es el "Asistente" que te explicaba
    private ExchangeFilterFunction autoAuthFilter(TokenContextHolder authHolder, AuthService authService) {
    return (request, next) -> {
        // 🚨 REGLA DE ORO: Si la URL contiene "oauth/token", NO aplicar el filtro
        if (request.url().getPath().contains("/oauth/token")) {
            return next.exchange(request);
        }

        if (!authHolder.hasToken()) {
            return authService.login()
                    .flatMap(tokenResponse -> {
                        authHolder.saveToken(tokenResponse);
                        return next.exchange(addToken(request, tokenResponse.getAccessToken()));
                    });
        }
        return next.exchange(addToken(request, authHolder.getAccesToken()));
    };
}

    // Un pequeño método auxiliar para no ensuciar el código
    private ClientRequest addToken(ClientRequest request, String token) {
        return ClientRequest.from(request)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }


}
