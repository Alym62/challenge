package com.github.alym62.challenge.backend.domain.usecases.usuario.update;

import com.github.alym62.challenge.backend.domain.entities.Usuario;

public interface UseCaseUpdateUsuario {
    Usuario execute(Long id, Usuario entity);
}
