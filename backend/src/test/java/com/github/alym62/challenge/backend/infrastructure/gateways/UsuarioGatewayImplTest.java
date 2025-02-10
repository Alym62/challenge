package com.github.alym62.challenge.backend.infrastructure.gateways;

import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioRequestDTO;
import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioResponseDTO;
import com.github.alym62.challenge.backend.domain.entities.Usuario;
import com.github.alym62.challenge.backend.infrastructure.mappers.UsuarioMapper;
import com.github.alym62.challenge.backend.infrastructure.persistences.UsuarioEntity;
import com.github.alym62.challenge.backend.infrastructure.repositories.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioGatewayImplTest {
    private UsuarioGatewayImpl gateway;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UsuarioMapper mapper;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.gateway = new UsuarioGatewayImpl(repository, encoder);
    }

    @BeforeEach
    void setUpEach() {
        reset(encoder, repository, mapper);
    }

    @Test
    @DisplayName("Test [Create] - Usuario Gateway")
    void itShouldCreateTheUserIfEmailIsAvailable() {
        var usuario = new Usuario("aly@email.com", "123", Set.of());
        var usuarioPersisted = new UsuarioEntity("aly@email.com", "123", Set.of());
        var usuarioDTO = new UsuarioResponseDTO(1L, "aly@email.com", LocalDateTime.now(), LocalDateTime.now());

        when(repository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        when(encoder.encode(usuario.getSenha())).thenReturn("123");
        when(repository.save(any(UsuarioEntity.class))).thenReturn(usuarioPersisted);
        when(mapper.entityToPersistence(usuario)).thenReturn(usuarioPersisted);
        when(mapper.persistenceToEntity(usuarioPersisted)).thenReturn(usuario);

        var createdUsuario = gateway.create(usuario);

        assertNotNull(createdUsuario);
        assertEquals(usuarioDTO.email(), createdUsuario.getEmail());

        verify(repository, times(1)).findByEmail(usuario.getEmail());
        verify(repository, times(1)).save(any(UsuarioEntity.class));
        verify(encoder, times(1)).encode(usuario.getSenha());
    }

    @Test
    @DisplayName("Test [Get by id] - Usuario Gateway")
    void itShouldGetUsuarioById() {
        var id = 1L;
        var usuario = new Usuario("aly@email.com", "123", Set.of());
        var usuarioPersisted = new UsuarioEntity("aly@email.com", "123", Set.of());
        var usuarioDTO = new UsuarioResponseDTO(id, "aly@email.com", LocalDateTime.now(), LocalDateTime.now());

        when(repository.findById(id)).thenReturn(Optional.of(usuarioPersisted));
        when(mapper.persistenceToEntity(usuarioPersisted)).thenReturn(usuario);

        var result = gateway.getById(id);

        assertNotNull(result);
        assertEquals(usuarioDTO.email(), result.getEmail());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test [Get by id] - Usuario Gateway - Throw")
    void itShouldThrowIfUsuarioNotFound() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gateway.getById(id));

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test [Find by id] - Usuario Gateway")
    void itShouldFindById() {
        var id = 1L;
        var usuarioPersistedExists = new UsuarioEntity("aly@email.com", "123", Set.of());
        var updatedUsuario = new Usuario("aly@email.com", "123456", Set.of());
        var updatedUsuarioPersisted = new UsuarioEntity("aly@email.com", "123456", Set.of());
        var usuarioDTO = new UsuarioResponseDTO(id, "aly@email.com", LocalDateTime.now(), LocalDateTime.now());

        when(repository.findById(id)).thenReturn(Optional.of(usuarioPersistedExists));
        when(repository.save(any(UsuarioEntity.class))).thenReturn(updatedUsuarioPersisted);
        when(mapper.entityToPersistence(updatedUsuario)).thenReturn(updatedUsuarioPersisted);
        when(mapper.persistenceToEntity(updatedUsuarioPersisted)).thenReturn(updatedUsuario);

        var result = gateway.updateById(id, updatedUsuario);

        assertNotNull(result);
        assertEquals(usuarioDTO.email(), result.getEmail());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Test [Delete by id] - Usuario service")
    void itShouldDeleteById() {
        var id = 1L;
        var usuarioPersistedExists = new UsuarioEntity("aly@email.com", "123", Set.of());
        usuarioPersistedExists.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(usuarioPersistedExists));
        doNothing().when(repository).deleteById(id);

        gateway.delete(id);

        verify(repository, times(1)).deleteById(id);
    }
}