package com.github.alym62.challenge.backend.infrastructure.mappers;

import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioRequestDTO;
import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioResponseDTO;
import com.github.alym62.challenge.backend.domain.entities.Usuario;
import com.github.alym62.challenge.backend.infrastructure.persistences.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioResponseDTO persistenceToDto(UsuarioEntity persistence);
    UsuarioEntity entityToPersistence(Usuario entity);
    UsuarioResponseDTO entityToDto(Usuario entity);
    Usuario persistenceToEntity(UsuarioEntity persistence);
    Usuario dtoRequestToEntity(UsuarioRequestDTO requestDTO);
}
