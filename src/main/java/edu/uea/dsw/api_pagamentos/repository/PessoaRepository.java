package edu.uea.dsw.api_pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uea.dsw.api_pagamentos.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Métodos customizados podem ser adicionados aqui, se necessário
}
