package edu.uea.dsw.api_pagamentos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Implementação da classe Categoria
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    private Long codigo;
    private String nome;
}
