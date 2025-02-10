package com.github.alym62.challenge.backend.application.controllers;

import com.github.alym62.challenge.backend.application.dto.auth.AuthRequestDTO;
import com.github.alym62.challenge.backend.application.dto.auth.AuthResponseDTO;
import com.github.alym62.challenge.backend.application.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO dto) {
        return ResponseEntity.accepted().body(service.login(dto));
    }
}
