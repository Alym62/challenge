package com.github.alym62.challenge.backend.domain.usecases.contato.update;

import com.github.alym62.challenge.backend.domain.entities.Contato;

public interface UseCaseUpdateContato {
    Contato execute(Long id, Contato entity);
}
