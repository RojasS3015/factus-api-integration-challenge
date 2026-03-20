package com.factus.api.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "api")
public class FactusProperties {

    private String baseUrl;
    private String email;
    private String password;
    private String clientId;
    private String clientSecret;

}
