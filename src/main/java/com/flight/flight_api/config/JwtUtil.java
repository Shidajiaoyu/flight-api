package com.flight.flight_api.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<String, Object>();
        String encodedSecret = Base64.getEncoder().encodeToString(SECRET.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Base64.getDecoder().decode(encodedSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 mins
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        String encodedSecret = Base64.getEncoder().encodeToString(SECRET.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Base64.getDecoder().decode(encodedSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        String encodedSecret = Base64.getEncoder().encodeToString(SECRET.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Base64.getDecoder().decode(encodedSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration()
                .before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
