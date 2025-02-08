package com.github.alym62.challenge.backend.application.dto.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequestDTO(
        @NotEmpty String email,
        @NotEmpty String senha
) {
}
