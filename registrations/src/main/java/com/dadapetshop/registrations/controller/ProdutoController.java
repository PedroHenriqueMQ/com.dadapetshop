package com.dadapetshop.registrations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.registrations.dto.ProdutoDTO;
import com.dadapetshop.registrations.service.ProdutoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @Transactional
    @PostMapping("/register-product")
    public ResponseEntity<String> saveProduto(@RequestBody ProdutoDTO produto){
        produtoService.saveProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso!");
    }
}
