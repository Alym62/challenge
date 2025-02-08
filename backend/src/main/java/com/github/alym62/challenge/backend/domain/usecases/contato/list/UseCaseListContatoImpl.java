package com.github.alym62.challenge.backend.domain.usecases.contato.list;

import com.github.alym62.challenge.backend.domain.entities.Contato;
import com.github.alym62.challenge.backend.domain.gateways.ContatoGateway;

import java.util.List;

public class UseCaseListContatoImpl implements UseCaseListContato {
    private final ContatoGateway gateway;

    public UseCaseListContatoImpl(ContatoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Contato> execute() {
        return gateway.getList();
    }
}
