package com.factus.api.config;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthHolder {
    
    private OauthToken currentToken;

    public synchronized void saveToken(OauthToken token){
        this.currentToken = token;
    }

    public synchronized String getAccesToken(){
        return (currentToken != null) ? currentToken.getAccess_token() : null;
    }

    public synchronized String getRefreshToken(){
        return (currentToken != null) ? currentToken.getRefresh_token() : null;
    }

    public synchronized boolean hasToken(){
        return currentToken != null;
    }
}
