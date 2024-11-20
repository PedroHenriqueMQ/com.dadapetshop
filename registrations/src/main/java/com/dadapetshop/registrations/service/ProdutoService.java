package com.dadapetshop.registrations.service;

import com.dadapetshop.registrations.model.Produto;
import com.dadapetshop.registrations.model.ProdutoCategoria;

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

    public ProdutoDTO findProdutoByCategoria(String categoria) {
        ProdutoCategoria categoriaStringToEnum;

        try {
            categoriaStringToEnum = Enum.valueOf(ProdutoCategoria.class, categoria);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Categoria não encontrada.");
        }

        var produto = produtoRepository.findByCategoria(categoriaStringToEnum)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        var produtoToProdutoDTO = produtoMapper.toDTO(produto);
        return produtoToProdutoDTO;
    }

    public Page<ProdutoDTO> findProdutosPaginados(int page, int size) {
        Pageable pageableProdutos = PageRequest.of(page, size);
        Page<Produto> produtos = produtoRepository.findAll(pageableProdutos);

        return produtos.map(produtoMapper::toDTO);
    }

    public void updateProdutoQuantidade(ProdutoDTO produtoDTO) {
        var produto = produtoRepository.findByCodigo(produtoDTO.codigo())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
        
        produto.setQuantidadeEstoque(produtoDTO.quantidadeEstoque());
        produtoRepository.save(produto);
    }

    public void updateProdutoValor(ProdutoDTO produtoDTO) {
        var produto = produtoRepository.findByCodigo(produtoDTO.codigo())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
        
        produto.setValor(produtoDTO.valor());
        produtoRepository.save(produto);
    }

    public void updateProdutoNome(ProdutoDTO produtoDTO) {
        var produto = produtoRepository.findByCodigo(produtoDTO.codigo())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
        
        produto.setNome(produtoDTO.nome());
        produtoRepository.save(produto);
    }

    public void deleteProduto(String codigoProduto) {
        var produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
                
        produtoRepository.delete(produto);
    }
}


