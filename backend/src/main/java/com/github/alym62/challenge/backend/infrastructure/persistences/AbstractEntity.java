package com.github.alym62.challenge.backend.infrastructure.persistences;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.setDataCriacao(LocalDateTime.now());
        this.setDataAtualizacao(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setDataAtualizacao(LocalDateTime.now());
    }
}
