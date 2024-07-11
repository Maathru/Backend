package com.maathru.backend.Application.config;

import com.maathru.backend.Domain.entity.Token;
import com.maathru.backend.External.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        String token = authHeader.substring(7);

        // get stored token from database
        Token storedToken = tokenRepository.findByAccessToken(token).orElse(null);
        if (storedToken != null) {
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }
    }
}