package com.dadapetshop.registrations.exception;

public class ProdutoNaoEncontradoException extends IllegalArgumentException {
    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado.");
    }
}
