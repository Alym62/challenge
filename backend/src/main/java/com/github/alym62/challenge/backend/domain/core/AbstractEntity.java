package com.github.alym62.challenge.backend.domain.core;

import java.time.LocalDateTime;

public abstract class AbstractEntity {
    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public AbstractEntity() {}

    public AbstractEntity(Long id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
