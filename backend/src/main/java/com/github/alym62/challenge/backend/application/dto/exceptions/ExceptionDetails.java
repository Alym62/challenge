package com.github.alym62.challenge.backend.application.dto.exceptions;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionDetails(
        String titulo,
        int status,
        String detalhes,
        LocalDateTime timestamp
) {
}
