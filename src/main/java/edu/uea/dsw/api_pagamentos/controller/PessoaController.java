package edu.uea.dsw.api_pagamentos.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<PessoaDTO> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    // GET /pessoas/{codigo}
    @GetMapping("/{codigo}")
    public PessoaDTO buscarPessoa(@PathVariable Long codigo) {
        return pessoaService.buscarPessoaPorCodigo(codigo);
    }

    // POST /pessoas
    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@Valid @RequestBody PessoaDTO pessoa) {
        PessoaDTO pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    // PUT /pessoas/{codigo}
    @PutMapping("/{codigo}")
    public PessoaDTO atualizarPessoa(@Valid @PathVariable Long codigo, @RequestBody PessoaDTO pessoa) {
        return pessoaService.atualizarPessoa(codigo, pessoa);
    }

    // DELETE /pessoas/{codigo}
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long codigo) {
        pessoaService.deletarPessoa(codigo);
        return ResponseEntity.noContent().build();
    }

    // PATCH /pessoas/{codigo}/ativo
    @PatchMapping("/{codigo}/ativo")
    public PessoaDTO atualizarStatus(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        return pessoaService.atualizarStatus(codigo, ativo);
    }
}
