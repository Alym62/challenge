package com.github.alym62.challenge.backend.application.services;

import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioResponseDTO;
import com.github.alym62.challenge.backend.infrastructure.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UsuarioRepository repository;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expiration;

    public String gerarToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            return getClaims(token)
                    .getExpiration()
                    .after(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public UsuarioResponseDTO getUserDetails(String token) {
        return repository.findByEmail(getClaims(token).getSubject())
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getEmail(), usuario.getDataCriacao(), usuario.getDataAtualizacao()))
                .orElseThrow(() -> new UsernameNotFoundException("Ops! Usuário não encontrado."));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
