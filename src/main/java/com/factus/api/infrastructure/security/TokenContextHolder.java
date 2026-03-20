package com.factus.api.infrastructure.security;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.factus.api.infrastructure.config.AuthToken;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenContextHolder {
    
    private final AtomicReference<AuthToken> currentToken = new AtomicReference<>();

    public void saveToken(AuthToken token){
        this.currentToken.set(token);
    }

    public String getAccesToken(){
        AuthToken token = currentToken.get();
        return (token != null) ? token.getAccessToken() : null;
    }

    public boolean hasToken(){
        return currentToken.get() != null;
    }
}
