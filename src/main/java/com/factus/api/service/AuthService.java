package com.factus.api.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.factus.api.config.FactusProperties;
import com.factus.api.config.AuthToken;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class AuthService {

    private final WebClient webClient;
    private final FactusProperties ApiConfig;

    
    public AuthService (@Lazy WebClient webClient, FactusProperties ApiConfig){
        this.webClient = webClient;
        this.ApiConfig = ApiConfig;
    }

    //permite obtener un token de acceso para autenticar solicitudes a la API mediante las credenciales del sistema.
    public Mono<AuthToken> login(){

        return webClient.post()
                        .uri("/oauth/token")
                        .body(BodyInserters.fromFormData("grant_type", "password")
                        .with("client_id", ApiConfig.getClientId())
                        .with("client_secret", ApiConfig.getClientSecret())
                        .with("username", ApiConfig.getEmail())
                        .with("password", ApiConfig.getPassword()))
                    .retrieve()
                    .bodyToMono(AuthToken.class)
                    //nueva linea
                    .doOnError(e -> log.error("Error en login inicial: {}", e.getMessage()));
                    
    }

    //permite actualizar el token de acceso mediante el uso de un refresh token previamente generado.
    public Mono<AuthToken> getRefreshToken(String refresh_token){

        return webClient.post()
                        .uri("/oauth/token")
                        .body(BodyInserters.fromFormData("grant_type", "refresh_token")
                        .with("client_id", ApiConfig.getClientId())
                        .with("client_secret", ApiConfig.getClientSecret())
                        .with("refresh_token", refresh_token))
                    .retrieve()
                    .bodyToMono(AuthToken.class)
                    //nueva linea
                    .doOnError(e -> log.error("Error al refrescar token: {}", e.getMessage()));
    }
    
}
