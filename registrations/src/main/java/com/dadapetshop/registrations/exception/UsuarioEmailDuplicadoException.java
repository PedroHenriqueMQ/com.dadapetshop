package com.dadapetshop.registrations.exception;

public class UsuarioEmailDuplicadoException extends IllegalArgumentException {
    public UsuarioEmailDuplicadoException() {
        super("Usuário com esse e-mail já existe.");
    }
}
