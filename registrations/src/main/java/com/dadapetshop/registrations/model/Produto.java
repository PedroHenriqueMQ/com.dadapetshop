package com.dadapetshop.registrations.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
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
    @Enumerated(EnumType.STRING)
    private ProdutoCategoria categoria;
}
