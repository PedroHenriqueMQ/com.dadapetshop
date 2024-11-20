package com.dadapetshop.registrations.exception;

public class UsuarioNaoEncontradoException extends IllegalArgumentException {
    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }
}
