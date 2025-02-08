package com.github.alym62.challenge.backend.application.dto.usuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String email,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
