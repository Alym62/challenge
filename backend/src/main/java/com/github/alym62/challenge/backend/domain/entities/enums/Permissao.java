package com.github.alym62.challenge.backend.domain.entities.enums;

public enum Permissao {
    ADMIN("admin"),
    USUARIO("usuario");

    private String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
