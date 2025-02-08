package com.github.alym62.challenge.backend.infrastructure.gateways;

import com.github.alym62.challenge.backend.domain.entities.Usuario;
import com.github.alym62.challenge.backend.domain.gateways.UsuarioGateway;
import com.github.alym62.challenge.backend.infrastructure.mappers.UsuarioMapper;
import com.github.alym62.challenge.backend.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper = UsuarioMapper.INSTANCE;

    @Override
    public List<Usuario> getList() {
        return repository.findAll().stream()
                .map(mapper::persistenceToEntity).toList();
    }

    @Override
    public Usuario getById(Long id) {
        return repository.findById(id)
                .map(mapper::persistenceToEntity)
                .orElseThrow(() -> new RuntimeException("Ops! Usuário não encontrado."));
    }

    @Override
    @Transactional
    public Usuario create(Usuario entity) {
        var usuarioExists = repository.findByEmail(entity.getEmail());
        if (usuarioExists.isPresent()) {
            throw new RuntimeException("Ops! Usuário já cadastrado.");
        }

        return mapper.persistenceToEntity(repository.save(mapper.entityToPersistence(entity)));
    }

    @Override
    public Usuario updateById(Long id, Usuario entity) {
        var usuario = getById(id);
        BeanUtils.copyProperties(entity, usuario, "id");
        return mapper.persistenceToEntity(repository.save(mapper.entityToPersistence(usuario)));
    }

    @Override
    public void delete(Long id) {
        var usuario = getById(id);
        repository.deleteById(usuario.getId());
    }
}
