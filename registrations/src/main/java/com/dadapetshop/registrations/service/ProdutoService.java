package com.dadapetshop.registrations.service;

import org.springframework.stereotype.Service;

import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.mapper.ProdutoMapper;
import com.dadapetshop.registrations.repository.ProdutoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private ProdutoMapper produtoMapper;

    public void saveProduto(ProdutoDTO produtoDTO){
        if (produtoRepository.findByNome(produtoDTO.nome()).isPresent())
        throw new IllegalStateException("Um produto com esse nome já existe.");

        var produto = produtoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
        
    }
}
