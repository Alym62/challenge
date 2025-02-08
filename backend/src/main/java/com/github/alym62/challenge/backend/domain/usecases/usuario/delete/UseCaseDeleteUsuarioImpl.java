package com.github.alym62.challenge.backend.domain.usecases.usuario.delete;

import com.github.alym62.challenge.backend.domain.gateways.UsuarioGateway;

public class UseCaseDeleteUsuarioImpl implements UseCaseDeleteUsuario {
    private final UsuarioGateway gateway;

    public UseCaseDeleteUsuarioImpl(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(Long id) {
        gateway.delete(id);
    }
}
