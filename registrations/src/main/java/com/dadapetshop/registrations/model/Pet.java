package com.dadapetshop.registrations.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pet {
    @Id
    private Long id;
    private String nome;
    private String raca;
    private Integer idade;
    private BigDecimal peso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Usuario tutor;
}
