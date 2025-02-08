package com.github.alym62.challenge.backend.domain.usecases.usuario.list;

import com.github.alym62.challenge.backend.domain.entities.Usuario;
import com.github.alym62.challenge.backend.domain.gateways.UsuarioGateway;

import java.util.List;

public class UseCaseListUsuarioImpl implements UseCaseListUsuario {
    private final UsuarioGateway gateway;

    public UseCaseListUsuarioImpl(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Usuario> execute() {
        return gateway.getList();
    }
}
