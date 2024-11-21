package com.dadapetshop.registrations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionListener {
    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler(PetDuplicadoException.class)
    public ResponseEntity handlePetDuplicadoException(PetDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(PetNaoEncontradoException.class)
    public ResponseEntity handlePetNaoEncontradoException(PetNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler(ProdutoDuplicadoException.class)
    public ResponseEntity handleProdutoDuplicadoException(ProdutoDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ProfissionalCPFDuplicadoException.class)
    public ResponseEntity handleProfissionalCPFDuplicadoException(ProfissionalCPFDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UsuarioEmailDuplicadoException.class)
    public ResponseEntity handleUsuarioEmailDuplicadoException(UsuarioEmailDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity handleUsuarioNaoEncontradoException(ProdutoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}

