package com.github.alym62.challenge.backend.application.services;

import com.github.alym62.challenge.backend.application.dto.contato.ContatoRequestDTO;
import com.github.alym62.challenge.backend.application.dto.contato.ContatoResponseDTO;
import com.github.alym62.challenge.backend.infrastructure.gateways.ContatoGatewayImpl;
import com.github.alym62.challenge.backend.infrastructure.mappers.ContatoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoGatewayImpl gateway;
    private final ContatoMapper mapper = ContatoMapper.INSTANCE;

    public List<ContatoResponseDTO> getList() {
        return gateway.getList().stream()
                .map(mapper::entityToDto).toList();
    }

    public ContatoResponseDTO create(ContatoRequestDTO dto) {
        var contato = gateway.create(mapper.dtoRequestToEntity(dto));
        return mapper.entityToDto(contato);
    }

    public Page<ContatoResponseDTO> pager(String nome, String email, Pageable pageable) {
        return gateway.getPager(nome, email, pageable).map(mapper::entityToDto);
    }

    public ContatoResponseDTO update(Long id, ContatoRequestDTO dto) {
        var contato = gateway.updateById(id, mapper.dtoRequestToEntity(dto));
        return mapper.entityToDto(contato);
    }

    public void delete(Long id) {
        gateway.delete(id);
    }
}
