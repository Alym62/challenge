package com.github.alym62.challenge.backend.application.controllers;

import com.github.alym62.challenge.backend.application.dto.contato.ContatoRequestDTO;
import com.github.alym62.challenge.backend.application.dto.contato.ContatoResponseDTO;
import com.github.alym62.challenge.backend.application.services.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contato")
@RequiredArgsConstructor
public class ContatoController {
    private final ContatoService service;

    @GetMapping("/list")
    public ResponseEntity<List<ContatoResponseDTO>> getList() {
        return ResponseEntity.ok().body(service.getList());
    }

    @GetMapping("/pager")
    public ResponseEntity<Page<ContatoResponseDTO>> getPager(@RequestParam(defaultValue = "") String nome,
                                                             @RequestParam(defaultValue = "") String email,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int perPage) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("nome")));
        return ResponseEntity.ok().body(service.pager(nome, email, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<ContatoResponseDTO> create(@RequestBody @Valid ContatoRequestDTO dto) {
        var dtoResponse = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContatoResponseDTO> update(@PathVariable Long id, @RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
