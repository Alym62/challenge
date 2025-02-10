package com.github.alym62.challenge.backend.application.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @Email(message = "E-mail inválido")
        @NotEmpty(message = "Email é obrigatório")
        String email,
        @Size(min = 3, message = "Senha é obrigatório") String senha
) {
}
