package com.dadapetshop.registrations.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.service.ProdutoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/product")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @Transactional
    @PostMapping("/register-product")
    public ResponseEntity<String> saveProduto(@Valid @RequestBody ProdutoDTO produto) {
        produtoService.saveProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> getProdutosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        Page<ProdutoDTO> produtos = produtoService.findProdutosPaginados(page, size);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ProdutoDTO> findProdutoByCodigo(@PathVariable String codigo) {
        var produto = produtoService.findProdutoByCodigo(codigo);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ProdutoDTO> findProdutoBycategoria(@PathVariable String categoria) {
        var produto = produtoService.findProdutoByCategoria(categoria);
        return ResponseEntity.ok(produto);
    }

    @PatchMapping("/update/estoque")
    public ResponseEntity<String> updateProdutoQuantidade(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.updateProdutoQuantidade(produtoDTO);
        return ResponseEntity.ok("Quantidade do produto atualizada com sucesso!");
    }

    @PatchMapping("/update/valor")
    public ResponseEntity<String> updateProdutoValor(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.updateProdutoValor(produtoDTO);
        return ResponseEntity.ok("Valor do produto atualizado com sucesso!");
    }

    @PatchMapping("/update/nome")
    public ResponseEntity<String> updateProdutoNome(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.updateProdutoNome(produtoDTO);
        return ResponseEntity.ok("Nome do produto atualizado com sucesso!");
    }

    @DeleteMapping("/delete/{codigoProduto}")
    public ResponseEntity<String> deleteProduto(@Valid @PathVariable String codigoProduto) {
        produtoService.deleteProduto(codigoProduto);
        return ResponseEntity.ok("Produto removido com sucesso!");
    } 
}
