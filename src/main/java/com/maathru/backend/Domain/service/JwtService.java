package com.maathru.backend.Domain.service;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.External.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.access-token-expiration}")
    private long accessTokenExpire;
    @Value("${application.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpire;

    private final TokenRepository tokenRepository;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isAccessTokenValid(String accessToken, UserDetails user) {
        String email = extractEmail(accessToken);
        boolean isValidAccessToken = tokenRepository.findByAccessToken(accessToken).map(t -> !t.isLoggedOut()).orElse(false);
        return email.equals(user.getUsername()) && isTokenExpired(accessToken) && isValidAccessToken;
    }

    public boolean isValidRefreshToken(String refreshToken, User user) {
        String email = extractEmail(refreshToken);
        boolean isValidRefreshToken = tokenRepository.findByRefreshToken(refreshToken).map(t -> !t.isLoggedOut()).orElse(false);
        return email.equals(user.getUsername()) && isTokenExpired(refreshToken) && isValidRefreshToken;
    }

    private boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(User user) {
        return generateToken(user, accessTokenExpire);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpire);
    }

    private String generateToken(User user, long expiredTime) {
        return Jwts
                .builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
