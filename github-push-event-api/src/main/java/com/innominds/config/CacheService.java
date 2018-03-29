package com.innominds.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheService {
     
    private static Map<String, Token> store = new HashMap<String, Token>();
    /*static{
        store.put("access_token", new Token());
    }*/
         
    @CachePut(value="token", key="#access_token")
    public Token putToken(Token token){
        //Token token = store.get("access_token");
        //token.setAccess_token(access_token);
    	store.put("access_token", token);
        return token;
    }
     
    @Cacheable(value="token", key="#access_token")
    public Token get(String access_token){
        System.out.println("Service processing...");
        try{
            Thread.sleep(3000); 
        }catch(Exception e){
        }
        Token token = store.get(access_token);
        return token;
    }
     
    @CacheEvict(value = "token", key = "#access_token")
    public void evict(long id){
    }
}