package com.github.alym62.challenge.backend.application.controllers;

import com.github.alym62.challenge.backend.application.dto.contato.ContatoRequestDTO;
import com.github.alym62.challenge.backend.application.dto.contato.ContatoResponseDTO;
import com.github.alym62.challenge.backend.application.services.ContatoService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContatoControllerTest {
    private ContatoController contatoController;

    @Mock
    private ContatoService contatoService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.contatoController = new ContatoController(contatoService);
    }

    @BeforeEach
    void setUpEach() {
        reset(contatoService);
    }

    @Test
    @DisplayName("Test [Create] - Contato controller")
    void itShouldCallServiceAndReturnCreateContato() {
        var request = new ContatoRequestDTO("teste", "teste", "619999999", "email@email.com");

        when(contatoService.create(request)).thenReturn(new ContatoResponseDTO(1L, "teste", "teste", "619999999", "email@email.com", LocalDateTime.now(), LocalDateTime.now()));

        var response = contatoController.create(request);
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(request.nome(), body.nome());
        assertEquals(request.email(), body.email());
        assertEquals(request.telefone(), body.telefone());
        assertEquals(request.sobrenome(), body.sobrenome());

        var captor = ArgumentCaptor.forClass(ContatoRequestDTO.class);
        verify(contatoService, times(1)).create(captor.capture());

        var dto = captor.getValue();
        assertEquals(dto.nome(), request.nome());
        assertEquals(dto.email(), request.email());
        assertEquals(dto.telefone(), request.telefone());
        assertEquals(dto.sobrenome(), request.sobrenome());
    }

    @Test
    @DisplayName("Test [Get List] - Contato controller")
    void itShouldCallServiceAndReturnList() {
        var contatos = Arrays.asList(new ContatoResponseDTO(1L, "teste", "teste", "619999999", "email@email.com", LocalDateTime.now(), LocalDateTime.now()));
        when(contatoService.getList()).thenReturn(contatos);

        var response = contatoController.getList();
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(1, body.size());
        assertEquals("teste", body.get(0).nome());

        verify(contatoService, times(1)).getList();
    }

    @Test
    @DisplayName("Test [Pager] - Contato controller")
    void itShouldCallServiceAndReturnPager() {
        var pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("nome")));
        var page = new PageImpl<>(List.of(new ContatoResponseDTO(1L, "teste", "teste", "619999999", "email@email.com", LocalDateTime.now(), LocalDateTime.now())), pageable, 1);

        when(contatoService.pager("", "", pageable)).thenReturn(page);

        var response = contatoController.getPager("", "", 0, 10);
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(1, body.getContent().size());
        assertEquals("teste", body.getContent().get(0).nome());

        verify(contatoService, times(1)).pager("", "", pageable);
    }

    @Test
    @DisplayName("Test [Delete by id] - Contato controller")
    void itShouldCallServiceAndDeleteById() {
        var id = 1L;

        doNothing().when(contatoService).delete(id);

        var response = contatoController.delete(id);

        verify(contatoService, times(1)).delete(id);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test [Update by id] - Contato controller")
    void itShouldCallServiceAndUpdateById() {
        var id = 1L;
        var request = new ContatoRequestDTO("Novo", "Nome", "618888888", "novoemail@teste.com");

        when(contatoService.update(id, request)).thenReturn(new ContatoResponseDTO(id, "Novo", "Nome", "618888888", "novoemail@teste.com", LocalDateTime.now(), LocalDateTime.now()));

        var response = contatoController.update(id, request);
        var body = response.getBody();

        assertNotNull(body);
        assertEquals("Novo", body.nome());
        assertEquals("novoemail@teste.com", body.email());

        verify(contatoService, times(1)).update(id, request);
    }
}