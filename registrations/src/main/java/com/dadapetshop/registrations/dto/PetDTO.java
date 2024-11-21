package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record PetDTO(
    String nome,
    String raca,
    @Min(message = "Idade não pode ser menor que 0.", value = 0)
    Integer idade,
    @Min(message = "Peso não pode ser menor que 0.", value = 0)
    BigDecimal peso,
    @JsonProperty("email_tutor")
    @Email(message = "Email fornecido não está no formato de email.")
    String emailTutor
) {}
