package com.github.alym62.challenge.backend.infrastructure.persistences;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_contatos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContatoEntity extends AbstractEntity {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
}
