package edu.uea.dsw.api_pagamentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.uea.dsw.api_pagamentos.model.Categoria;
import edu.uea.dsw.api_pagamentos.repository.CategoriaRepository;
import edu.uea.dsw.api_pagamentos.service.exception.RecursoEmUsoException;
import edu.uea.dsw.api_pagamentos.service.exception.RecursoNaoEncontradoException;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Injeção via construtor
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorCodigo(Long codigo) {
        return categoriaRepository.findById(codigo)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException("Categoria com ID " + codigo + " não encontrada."));
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada!"));

        categoriaExistente.setNome(categoria.getNome());
        return categoriaRepository.save(categoriaExistente);
    }

    public void deletar(Long codigo) {
        Categoria categoriaExistente = categoriaRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada!"));

        try {
            categoriaRepository.delete(categoriaExistente);
        } catch (DataIntegrityViolationException ex) {
            throw new RecursoEmUsoException("Categoria em uso e não pode ser removida.", ex);
        }
    }
}
