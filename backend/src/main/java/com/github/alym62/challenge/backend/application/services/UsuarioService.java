package com.github.alym62.challenge.backend.application.services;

import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioRequestDTO;
import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioResponseDTO;
import com.github.alym62.challenge.backend.infrastructure.gateways.UsuarioGatewayImpl;
import com.github.alym62.challenge.backend.infrastructure.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioGatewayImpl gateway;
    private final UsuarioMapper mapper = UsuarioMapper.INSTANCE;

    public List<UsuarioResponseDTO> getList() {
        return gateway.getList().stream()
                .map(mapper::entityToDto).toList();
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        var contato = gateway.create(mapper.dtoRequestToEntity(dto));
        return mapper.entityToDto(contato);
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        var contato = gateway.updateById(id, mapper.dtoRequestToEntity(dto));
        return mapper.entityToDto(contato);
    }

    public void delete(Long id) {
        gateway.delete(id);
    }
}
