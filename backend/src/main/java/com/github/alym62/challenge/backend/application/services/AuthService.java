package com.github.alym62.challenge.backend.application.services;

import com.github.alym62.challenge.backend.application.dto.auth.AuthRequestDTO;
import com.github.alym62.challenge.backend.application.dto.auth.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponseDTO login(AuthRequestDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = authenticationManager.authenticate(token);

        return new AuthResponseDTO(jwtService.gerarToken((UserDetails) auth.getPrincipal()));
    }
}
