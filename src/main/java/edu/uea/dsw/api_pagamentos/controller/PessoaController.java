package edu.uea.dsw.api_pagamentos.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.uea.dsw.api_pagamentos.dto.PessoaDTO;
import edu.uea.dsw.api_pagamentos.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // GET /pessoas
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<PessoaDTO> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    // GET /pessoas/{codigo}
    @GetMapping("/{codigo}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public PessoaDTO buscarPessoa(@PathVariable Long codigo) {
        return pessoaService.buscarPessoaPorCodigo(codigo);
    }

    // POST /pessoas
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<PessoaDTO> criarPessoa(@Valid @RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    // PUT /pessoas/{codigo}
    @PutMapping("/{codigo}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public PessoaDTO atualizarPessoa(@Valid @PathVariable Long codigo, @RequestBody PessoaDTO pessoa) {
        return pessoaService.atualizarPessoa(codigo, pessoa);
    }

    // DELETE /pessoas/{codigo}
    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long codigo) {
        pessoaService.deletarPessoa(codigo);
        return ResponseEntity.noContent().build();
    }

    // PATCH /pessoas/{codigo}/ativo
    @PatchMapping("/{codigo}/ativo")
    @PreAuthorize("hasRole('ADMIN')")
    public PessoaDTO atualizarStatus(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        return pessoaService.atualizarStatus(codigo, ativo);
    }
}
