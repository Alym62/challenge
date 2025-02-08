package com.github.alym62.challenge.backend.infrastructure.repositories;

import com.github.alym62.challenge.backend.infrastructure.persistences.ContatoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    Optional<ContatoEntity> findByEmail(String email);

    Page<ContatoEntity> findByNomeContainingIgnoreCaseAndAndEmailContainingIgnoreCase(
      String nome, String email, Pageable pageable
    );
}
