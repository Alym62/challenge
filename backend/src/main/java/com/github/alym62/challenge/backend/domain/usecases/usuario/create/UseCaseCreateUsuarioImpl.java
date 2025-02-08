package com.github.alym62.challenge.backend.domain.usecases.usuario.create;

import com.github.alym62.challenge.backend.domain.entities.Usuario;
import com.github.alym62.challenge.backend.domain.gateways.UsuarioGateway;

public class UseCaseCreateUsuarioImpl implements UseCaseCreateUsuario {
    private final UsuarioGateway gateway;

    public UseCaseCreateUsuarioImpl(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Usuario execute(Usuario entity) {
        return gateway.create(entity);
    }
}
