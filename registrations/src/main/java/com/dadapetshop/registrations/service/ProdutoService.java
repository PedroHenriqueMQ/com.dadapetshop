package com.dadapetshop.registrations.service;

import com.dadapetshop.registrations.exception.CategoriaNaoEncontradaException;
import com.dadapetshop.registrations.exception.ProdutoDuplicadoException;
import com.dadapetshop.registrations.exception.ProdutoNaoEncontradoException;
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
            throw new ProdutoDuplicadoException();

        var produto = produtoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
    }

    public ProdutoDTO findProdutoByCodigo(String codigoProduto) {
        var produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        var produtoToProdutoDTO = produtoMapper.toDTO(produto);
        return produtoToProdutoDTO;
    }

    public ProdutoDTO findProdutoByCategoria(String categoria) {
        ProdutoCategoria categoriaStringToEnum;

        try {
            categoriaStringToEnum = Enum.valueOf(ProdutoCategoria.class, categoria);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CategoriaNaoEncontradaException();
        }

        var produto = produtoRepository.findByCategoria(categoriaStringToEnum)
                .orElseThrow(ProdutoNaoEncontradoException::new);

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
                .orElseThrow(ProdutoNaoEncontradoException::new);
        
        produto.setQuantidadeEstoque(produtoDTO.quantidadeEstoque());
        produtoRepository.save(produto);
    }

    public void updateProdutoValor(ProdutoDTO produtoDTO) {
        var produto = produtoRepository.findByCodigo(produtoDTO.codigo())
                .orElseThrow(ProdutoNaoEncontradoException::new);
        
        produto.setValor(produtoDTO.valor());
        produtoRepository.save(produto);
    }

    public void updateProdutoNome(ProdutoDTO produtoDTO) {
        var produto = produtoRepository.findByCodigo(produtoDTO.codigo())
                .orElseThrow(ProdutoNaoEncontradoException::new);
        
        produto.setNome(produtoDTO.nome());
        produtoRepository.save(produto);
    }

    public void deleteProduto(String codigoProduto) {
        var produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(ProdutoNaoEncontradoException::new);
                
        produtoRepository.delete(produto);
    }
}


