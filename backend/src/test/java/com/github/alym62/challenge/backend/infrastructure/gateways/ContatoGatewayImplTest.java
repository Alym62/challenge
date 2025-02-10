package com.github.alym62.challenge.backend.infrastructure.gateways;

import com.github.alym62.challenge.backend.application.dto.contato.ContatoResponseDTO;
import com.github.alym62.challenge.backend.domain.entities.Contato;
import com.github.alym62.challenge.backend.infrastructure.mappers.ContatoMapper;
import com.github.alym62.challenge.backend.infrastructure.persistences.ContatoEntity;
import com.github.alym62.challenge.backend.infrastructure.repositories.ContatoRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContatoGatewayImplTest {
    private ContatoGatewayImpl gateway;

    @Mock
    private ContatoRepository repository;

    @Mock
    private ContatoMapper mapper;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.gateway = new ContatoGatewayImpl(repository);
    }

    @BeforeEach
    void setUpEach() {
        reset(repository, mapper);
    }

    @Test
    @DisplayName("Test [Create] - Contato Gateway")
    void itShouldCreateContatoIfNotExists() {
        var contato = new Contato("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoPersisted = new ContatoEntity("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoDTO = new ContatoResponseDTO(1L, "Aly", "aly@email.com", "61999999", "aly@email.com", LocalDateTime.now(), LocalDateTime.now());

        when(repository.findByEmail(contato.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any(ContatoEntity.class))).thenReturn(contatoPersisted);
        when(mapper.entityToPersistence(contato)).thenReturn(contatoPersisted);
        when(mapper.persistenceToEntity(contatoPersisted)).thenReturn(contato);

        var createdContato = gateway.create(contato);

        assertNotNull(createdContato);
        assertEquals(contatoDTO.email(), createdContato.getEmail());

        verify(repository, times(1)).findByEmail(contato.getEmail());
        verify(repository, times(1)).save(any(ContatoEntity.class));
    }

    @Test
    @DisplayName("Test [Create] - Contato Gateway - Should Throw If Contato Exists")
    void itShouldThrowIfContatoAlreadyExists() {
        var contato = new Contato("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoPersistedExists = new ContatoEntity("Aly", "aly@email.com", "61999999", "aly@email.com");

        when(repository.findByEmail(contato.getEmail())).thenReturn(Optional.of(contatoPersistedExists));

        assertThrows(RuntimeException.class, () -> gateway.create(contato));

        verify(repository, times(1)).findByEmail(contato.getEmail());
        verify(repository, times(0)).save(any(ContatoEntity.class));
    }

    @Test
    @DisplayName("Test [Get by id] - Contato Gateway")
    void itShouldGetContatoById() {
        var id = 1L;
        var contato = new Contato("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoPersistedExists = new ContatoEntity("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoDTO = new ContatoResponseDTO(id, "Aly", "aly@email.com", "61999999", "aly@email.com", LocalDateTime.now(), LocalDateTime.now());

        when(repository.findById(id)).thenReturn(Optional.of(contatoPersistedExists));
        when(mapper.persistenceToEntity(contatoPersistedExists)).thenReturn(contato);

        var result = gateway.getById(id);

        assertNotNull(result);
        assertEquals(contatoDTO.email(), result.getEmail());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test [Get by id] - Contato Gateway - Should Throw If Not Found")
    void itShouldThrowIfContatoNotFound() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gateway.getById(id));

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test [Update by id] - Contato Gateway")
    void itShouldUpdateContatoById() {
        var id = 1L;
        var contatoPersistedExists = new ContatoEntity("Aly", "aly@email.com", "61999999", "aly@email.com");
        var updatedContato = new Contato("Aly Meireles", "aly@email.com", "61999999", "aly@email.com");
        var updatedContatoPersisted = new ContatoEntity("Aly Meireles", "aly@email.com", "61999999", "aly@email.com");
        var contatoDTO = new ContatoResponseDTO(id, "Aly Meireles", "aly@email.com", "61999999", "aly@email.com", LocalDateTime.now(), LocalDateTime.now());

        when(repository.findById(id)).thenReturn(Optional.of(contatoPersistedExists));
        when(repository.save(any(ContatoEntity.class))).thenReturn(updatedContatoPersisted);
        when(mapper.entityToPersistence(updatedContato)).thenReturn(updatedContatoPersisted);
        when(mapper.persistenceToEntity(updatedContatoPersisted)).thenReturn(updatedContato);

        var result = gateway.updateById(id, updatedContato);

        assertNotNull(result);
        assertEquals(contatoDTO.email(), result.getEmail());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(ContatoEntity.class));
    }

    @Test
    @DisplayName("Test [Delete by id] - Contato Gateway")
    void itShouldDeleteContatoById() {
        var id = 1L;
        var contatoPersistedExists = new ContatoEntity("Aly", "aly@email.com", "61999999", "aly@email.com");
        contatoPersistedExists.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(contatoPersistedExists));
        doNothing().when(repository).deleteById(id);

        gateway.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Test [Pager] - Contato Gateway")
    void itShouldGetPager() {
        var pageable = PageRequest.of(0, 10);
        var contato = new Contato("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoPersistedExists = new ContatoEntity("Aly", "aly@email.com", "61999999", "aly@email.com");
        var contatoDTO = new ContatoResponseDTO(1L, "Aly Meireles", "aly@email.com", "61999999", "aly@email.com", LocalDateTime.now(), LocalDateTime.now());
        var page = new PageImpl<>(List.of(contatoPersistedExists), pageable, 1);

        when(repository.findByNomeContainingIgnoreCaseAndAndEmailContainingIgnoreCase("Aly", "aly@email.com", pageable))
                .thenReturn(page);
        when(mapper.persistenceToEntity(contatoPersistedExists)).thenReturn(contato);

        var result = gateway.getPager("Aly", "aly@email.com", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(contatoDTO.email(), result.getContent().get(0).getEmail());

        verify(repository, times(1)).findByNomeContainingIgnoreCaseAndAndEmailContainingIgnoreCase("Aly", "aly@email.com", pageable);
    }
}
