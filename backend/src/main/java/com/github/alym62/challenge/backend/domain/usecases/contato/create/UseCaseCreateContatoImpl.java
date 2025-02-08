package com.github.alym62.challenge.backend.domain.usecases.contato.create;

import com.github.alym62.challenge.backend.domain.entities.Contato;
import com.github.alym62.challenge.backend.domain.gateways.ContatoGateway;

public class UseCaseCreateContatoImpl implements UseCaseCreateContato {
    private final ContatoGateway gateway;

    public UseCaseCreateContatoImpl(ContatoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Contato execute(Contato entity) {
        return gateway.create(entity);
    }
}
