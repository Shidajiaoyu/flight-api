package com.flight.flight_api.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.jwtExpirationMs}")
    private long jwtExpirationMs;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpirationMs;

    private Key getSigningKey() {
        String encodedSecret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Base64.getDecoder().decode(encodedSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(String email) {
        return buildToken(email, jwtExpirationMs);
    }

    public String generateRefreshToken(String email) {
        return buildToken(email, refreshExpirationMs);
    }

    private String buildToken(String subject, long expiration) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean validateToken(String token, String email) {
        final String username = extractUsername(token);
        return username.equals(email) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
