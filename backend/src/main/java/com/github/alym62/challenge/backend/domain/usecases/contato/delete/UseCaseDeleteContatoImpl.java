package com.github.alym62.challenge.backend.domain.usecases.contato.delete;

import com.github.alym62.challenge.backend.domain.gateways.ContatoGateway;

public class UseCaseDeleteContatoImpl implements UseCaseDeleteContato {
    private final ContatoGateway gateway;

    public UseCaseDeleteContatoImpl(ContatoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(Long id) {
        gateway.delete(id);
    }
}
