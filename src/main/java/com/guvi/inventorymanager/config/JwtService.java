package com.guvi.inventorymanager.config;

import com.guvi.inventorymanager.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {

    @Value("${app.jwt.expirationMinutes}")
    private long expirationMinutes;

    @Value("${app.jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    private long expirationMs;

    @PostConstruct
    public void init() {
        expirationMs = expirationMinutes * 60 * 1000L;
        secretKey = Keys.hmacShaKeyFor(
                        secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, Role role) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .subject(email)
                .claim("role", role.name())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    public String extractEmail(String token) {
        return parseClaims(token)
                .getSubject();
    }

    public String extractRole(String token) {
        return parseClaims(token)
                .get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}