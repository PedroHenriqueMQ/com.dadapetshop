package com.dadapetshop.registrations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profissional {
    @Id
    private Long id;
    private String nome;
    private String cpf;
    private String cargo;
}
