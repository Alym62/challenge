package com.github.alym62.challenge.backend.domain.entities;

import com.github.alym62.challenge.backend.domain.core.AbstractEntity;
import com.github.alym62.challenge.backend.domain.entities.enums.Permissao;

import java.util.Set;

public class Usuario extends AbstractEntity {
    private String email;
    private String senha;
    private Set<Permissao> permissoes;

    public Usuario() {}

    public Usuario(String email, String senha, Set<Permissao> permissoes) {
        this.email = email;
        this.senha = senha;
        this.permissoes = permissoes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
