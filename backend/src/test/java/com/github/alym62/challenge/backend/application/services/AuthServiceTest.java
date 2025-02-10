package com.github.alym62.challenge.backend.application.services;

import com.github.alym62.challenge.backend.application.dto.auth.AuthRequestDTO;
import com.github.alym62.challenge.backend.application.dto.auth.AuthResponseDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthServiceTest {
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private Authentication authentication;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.authService = new AuthService(authenticationManager, jwtService);
    }

    @BeforeEach
    void resetMocks() {
        reset(this.authenticationManager);
        reset(this.jwtService);
    }

    @Test
    @DisplayName("Test [Login] - Auth service")
    void shouldReturnTokenJwt() {
        var dto = new AuthRequestDTO("email@email.com", "123");

        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(authentication);

        when(jwtService.gerarToken(Mockito.any()))
                .thenReturn("accessToken");

        var result = authService.login(dto);

        assertEquals(new AuthResponseDTO("accessToken"), result);

        verify(authenticationManager, times(1)).authenticate(Mockito.any());
        verify(jwtService, times(1)).gerarToken(any());
    }
}