package com.dadapetshop.registrations.exception;

public class ProdutoDuplicadoException extends IllegalArgumentException {
    public ProdutoDuplicadoException() {
        super("Um produto com esse código já existe.");
    }
}
