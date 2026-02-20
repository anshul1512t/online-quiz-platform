package com.example.onlineQuiz.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;




@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    private final long jwtExpirationMs = 600000; //10 mins

    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username){
        return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
        .signWith(getSignKey())
        .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
            
            return true;
        }
        catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String getUsername(String token){
        return Jwts.parser()
        .verifyWith(getSignKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
    }

}
