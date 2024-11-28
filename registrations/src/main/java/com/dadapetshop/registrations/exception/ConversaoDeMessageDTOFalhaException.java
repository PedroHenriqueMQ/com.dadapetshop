package com.dadapetshop.registrations.exception;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ConversaoDeMessageDTOFalhaException extends IllegalArgumentException {
    public ConversaoDeMessageDTOFalhaException(String errorMsg) {
        super(errorMsg);
    }
}
