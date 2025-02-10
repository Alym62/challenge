package com.github.alym62.challenge.backend.application.controllers;

import com.github.alym62.challenge.backend.application.dto.contato.ContatoRequestDTO;
import com.github.alym62.challenge.backend.application.dto.contato.ContatoResponseDTO;
import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioRequestDTO;
import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioResponseDTO;
import com.github.alym62.challenge.backend.application.services.ContatoService;
import com.github.alym62.challenge.backend.application.services.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usuarioController = new UsuarioController(usuarioService);
    }

    @BeforeEach
    void setUpEach() {
        reset(usuarioService);
    }

    @Test
    @DisplayName("Test [Create] - Usuario controller")
    void itShouldCallServiceAndReturnCreateContato() {
        var request = new UsuarioRequestDTO("teste@email.com", "teste");

        when(usuarioService.create(request)).thenReturn(new UsuarioResponseDTO(1L, "teste@email.com", LocalDateTime.now(), LocalDateTime.now()));

        var response = usuarioController.create(request);
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(request.email(), body.email());

        var captor = ArgumentCaptor.forClass(UsuarioRequestDTO.class);
        verify(usuarioService, times(1)).create(captor.capture());

        var dto = captor.getValue();
        assertEquals(dto.email(), request.email());
    }

    @Test
    @DisplayName("Test [Get List] - Usuario controller")
    void itShouldCallServiceAndReturnList() {
        var usarios = Arrays.asList(new UsuarioResponseDTO(1L, "teste@email.com", LocalDateTime.now(), LocalDateTime.now()));
        when(usuarioService.getList()).thenReturn(usarios);

        var response = usuarioController.getList();
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(1, body.size());
        assertEquals("teste@email.com", body.get(0).email());

        verify(usuarioService, times(1)).getList();
    }

    @Test
    @DisplayName("Test [Delete by id] - Contato controller")
    void itShouldCallServiceAndDeleteById() {
        var id = 1L;

        doNothing().when(usuarioService).delete(id);

        var response = usuarioController.delete(id);

        verify(usuarioService, times(1)).delete(id);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test [Update by id] - Contato controller")
    void itShouldCallServiceAndUpdateById() {
        var id = 1L;
        var request = new UsuarioRequestDTO("email@emailnovo.com", "123456");

        when(usuarioService.update(id, request)).thenReturn(new UsuarioResponseDTO(id, "email@emailnovo.com", LocalDateTime.now(), LocalDateTime.now()));

        var response = usuarioController.update(id, request);
        var body = response.getBody();

        assertNotNull(body);
        assertEquals("email@emailnovo.com", body.email());

        verify(usuarioService, times(1)).update(id, request);
    }
}