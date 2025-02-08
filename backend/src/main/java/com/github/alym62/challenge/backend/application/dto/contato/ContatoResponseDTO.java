package com.github.alym62.challenge.backend.application.dto.contato;

import java.time.LocalDateTime;

public record ContatoResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String telefone,
        String email,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
