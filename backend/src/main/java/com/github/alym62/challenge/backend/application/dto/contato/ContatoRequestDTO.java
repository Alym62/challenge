package com.github.alym62.challenge.backend.application.dto.contato;

public record ContatoRequestDTO(
        String nome,
        String  sobrenome,
        String telefone,
        String email
) {
}
