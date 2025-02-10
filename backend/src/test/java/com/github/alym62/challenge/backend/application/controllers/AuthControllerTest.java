package com.github.alym62.challenge.backend.application.controllers;

import com.github.alym62.challenge.backend.application.dto.auth.AuthRequestDTO;
import com.github.alym62.challenge.backend.application.dto.auth.AuthResponseDTO;
import com.github.alym62.challenge.backend.application.services.AuthService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {
    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.authController = new AuthController(authService);
    }

    @BeforeEach
    void setUpEach() {
        reset(authService);
    }

    @Test
    @DisplayName("Test [Login] - Auth controller")
    void itShouldCallServiceAndReturnToken() {
        var request = new AuthRequestDTO("email@email.com", "123456");
        when(this.authService.login(request)).thenReturn(new AuthResponseDTO("accessToken"));

        var response = this.authController.login(request);
        var body = response.getBody();

        assertNotNull(body);
        assertEquals(body.accessToken(), "accessToken");

        verify(this.authService, times(1)).login(request);
    }
}