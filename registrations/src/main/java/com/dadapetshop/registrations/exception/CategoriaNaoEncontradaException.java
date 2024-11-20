package com.dadapetshop.registrations.exception;

public class CategoriaNaoEncontradaException extends IllegalArgumentException {
    public CategoriaNaoEncontradaException() {
        super("Categoria não encontrada.");
    }
}
