package com.factus.api.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factus.api.app.service.AuthService;
import com.factus.api.infrastructure.config.AuthToken;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Obtener Token de Acceso", description = "Realiza el login inicial para obtener el Bearer Token de Factus.")
    @GetMapping("/outh/token/obtener")
    public Mono<AuthToken> login() {
        return authService.login();
    }

    @Operation(summary = "Refrescar Token", description = "Utiliza el refresh_token para obtener una nueva sesión sin necesidad de credenciales.")
    @GetMapping("/outh/token/refreshtoken/{refreshToken}")
    public Mono<AuthToken> refresh(@PathVariable String refreshToken) {
        return authService.getRefreshToken(refreshToken);
    }
}