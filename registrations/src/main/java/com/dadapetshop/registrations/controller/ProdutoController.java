package com.dadapetshop.registrations.controller;

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
    public ResponseEntity<String> saveProduto(@RequestBody ProdutoDTO produto){
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
        System.out.println(categoria);
        var produto = produtoService.findProdutoByCategoria(categoria);
        return ResponseEntity.ok(produto);
    }

}
