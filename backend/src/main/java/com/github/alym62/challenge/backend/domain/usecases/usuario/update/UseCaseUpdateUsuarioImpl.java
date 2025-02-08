package com.github.alym62.challenge.backend.domain.usecases.usuario.update;

import com.github.alym62.challenge.backend.domain.entities.Usuario;
import com.github.alym62.challenge.backend.domain.gateways.UsuarioGateway;

public class UseCaseUpdateUsuarioImpl implements UseCaseUpdateUsuario {
    private final UsuarioGateway gateway;

    public UseCaseUpdateUsuarioImpl(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Usuario execute(Long id, Usuario entity) {
        return gateway.updateById(id, entity);
    }
}
