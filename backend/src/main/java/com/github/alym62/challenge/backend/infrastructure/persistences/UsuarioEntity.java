package com.github.alym62.challenge.backend.infrastructure.persistences;

import com.github.alym62.challenge.backend.domain.entities.enums.Permissao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioEntity extends AbstractEntity {
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Set<Permissao> permissoes;
}
