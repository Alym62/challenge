package com.github.alym62.challenge.backend.application.dto.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ContatoRequestDTO(
        @NotEmpty(message = "Nome é obrigatório") String nome,
        @NotEmpty(message = "Sobrenome é obrigatório") String  sobrenome,
        @NotEmpty(message = "Telefone é obrigatório") String telefone,
        @Email(message = "E-mail inválido")
        @NotEmpty(message = "Email é obrigatório")
        String email
) {
}
