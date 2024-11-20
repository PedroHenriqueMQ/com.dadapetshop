package com.dadapetshop.registrations.service;

import com.dadapetshop.registrations.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void saveProduto(ProdutoDTO produtoDTO) {
        if (produtoRepository.findByCodigo(produtoDTO.codigo()).isPresent())
            throw new IllegalStateException("Um produto com esse código já existe.");

        var produto = produtoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
    }

    public ProdutoDTO findProdutoByCodigo(String codigoProduto) {
        var produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        var produtoToProdutoDTO = produtoMapper.toDTO(produto);
        return produtoToProdutoDTO;
    }

    public Page<ProdutoDTO> findProdutosPaginados(int page, int size) {
        Pageable pageableProdutos = PageRequest.of(page, size);
        Page<Produto> produtos = produtoRepository.findAll(pageableProdutos);

        return produtos.map(produtoMapper::toDTO);
    }
}
