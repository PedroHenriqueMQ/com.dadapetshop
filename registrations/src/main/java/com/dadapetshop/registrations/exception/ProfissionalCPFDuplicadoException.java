package com.dadapetshop.registrations.exception;

public class ProfissionalCPFDuplicadoException extends IllegalArgumentException {
    public ProfissionalCPFDuplicadoException() {
        super("Funcionário com esse CPF já existe.");
    }
}
