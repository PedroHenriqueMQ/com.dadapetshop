package com.dadapetshop.registrations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dadapetshop.registrations.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
