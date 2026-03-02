package com.factus.api.config;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthHolder {
    
    private final AtomicReference<OauthToken> currentToken = new AtomicReference<>();

    public void saveToken(OauthToken token){
        this.currentToken.set(token);
    }

    public String getAccesToken(){
        OauthToken token = currentToken.get();
        return (token != null) ? token.getAccessToken() : null;
    }

    public boolean hasToken(){
        return currentToken.get() != null;
    }
}
