package com.github.alym62.challenge.backend.domain.usecases.contato.update;

import com.github.alym62.challenge.backend.domain.entities.Contato;
import com.github.alym62.challenge.backend.domain.gateways.ContatoGateway;

public class UseCaseUpdateContatoImpl implements UseCaseUpdateContato {
    private final ContatoGateway gateway;

    public UseCaseUpdateContatoImpl(ContatoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Contato execute(Long id, Contato entity) {
        return gateway.updateById(id, entity);
    }
}
