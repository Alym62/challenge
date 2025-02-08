package com.github.alym62.challenge.backend.infrastructure.mappers;

import com.github.alym62.challenge.backend.application.dto.contato.ContatoRequestDTO;
import com.github.alym62.challenge.backend.application.dto.contato.ContatoResponseDTO;
import com.github.alym62.challenge.backend.domain.entities.Contato;
import com.github.alym62.challenge.backend.infrastructure.persistences.ContatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContatoMapper {
    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    ContatoResponseDTO persistenceToDto(ContatoEntity persistence);
    ContatoEntity entityToPersistence(Contato entity);
    ContatoResponseDTO entityToDto(Contato entity);
    Contato persistenceToEntity(ContatoEntity persistence);
    Contato dtoRequestToEntity(ContatoRequestDTO requestDTO);
}
