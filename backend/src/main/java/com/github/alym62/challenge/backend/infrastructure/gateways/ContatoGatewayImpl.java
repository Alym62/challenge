package com.github.alym62.challenge.backend.infrastructure.gateways;

import com.github.alym62.challenge.backend.domain.entities.Contato;
import com.github.alym62.challenge.backend.domain.gateways.ContatoGateway;
import com.github.alym62.challenge.backend.infrastructure.exceptions.BusinessException;
import com.github.alym62.challenge.backend.infrastructure.mappers.ContatoMapper;
import com.github.alym62.challenge.backend.infrastructure.repositories.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContatoGatewayImpl implements ContatoGateway {
    private final ContatoRepository repository;
    private final ContatoMapper mapper = ContatoMapper.INSTANCE;

    @Override
    public List<Contato> getList() {
        return repository.findAll().stream()
                .map(mapper::persistenceToEntity).toList();
    }

    @Override
    public Contato getById(Long id) {
        return repository.findById(id)
                .map(mapper::persistenceToEntity)
                .orElseThrow(() -> new BusinessException("Ops! Contato não encontrado."));
    }

    @Override
    @Transactional
    public Contato create(Contato entity) {
        var contatoExists = repository.findByEmail(entity.getEmail());
        if (contatoExists.isPresent()) {
            throw new BusinessException("Ops! Contato já cadastrado.");
        }

        return mapper.persistenceToEntity(repository.save(mapper.entityToPersistence(entity)));
    }

    @Override
    @Transactional
    public Contato updateById(Long id, Contato entity) {
        var contato = getById(id);
        BeanUtils.copyProperties(entity, contato, "id");
        return mapper.persistenceToEntity(repository.save(mapper.entityToPersistence(contato)));
    }

    @Override
    public void delete(Long id) {
        var contato = getById(id);
        repository.deleteById(contato.getId());
    }

    public Page<Contato> getPager(String nome, String email, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCaseAndAndEmailContainingIgnoreCase(
                nome, email, pageable
        ).map(mapper::persistenceToEntity);
    }
}
