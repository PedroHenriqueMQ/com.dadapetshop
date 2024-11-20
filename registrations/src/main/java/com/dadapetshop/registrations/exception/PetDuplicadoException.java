package com.dadapetshop.registrations.exception;

public class PetDuplicadoException extends IllegalArgumentException {
    public PetDuplicadoException() {
        super("Foi informado um Pet já cadastrado.");
    }
}
