package com.dadapetshop.registrations.exception;

public class PetNaoEncontradoException extends IllegalArgumentException {
    public PetNaoEncontradoException() {
        super("Pet não encontrado.");
    }
}
