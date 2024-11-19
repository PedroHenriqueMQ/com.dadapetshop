package com.dadapetshop.registrations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dadapetshop.registrations.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    Optional<Produto> findByNome(String nome);
}
