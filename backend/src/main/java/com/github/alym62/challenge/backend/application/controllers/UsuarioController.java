package com.github.alym62.challenge.backend.application.controllers;

import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioRequestDTO;
import com.github.alym62.challenge.backend.application.dto.usuario.UsuarioResponseDTO;
import com.github.alym62.challenge.backend.application.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioResponseDTO>> getList() {
        return ResponseEntity.ok().body(service.getList());
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
