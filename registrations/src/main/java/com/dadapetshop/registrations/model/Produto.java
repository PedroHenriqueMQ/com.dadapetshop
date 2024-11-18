package com.dadapetshop.registrations.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;
    @Column(name = "quantidade_prateleira")
    private int quantidadePrateleira;
    private BigDecimal valor;
}
