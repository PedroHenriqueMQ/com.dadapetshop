package com.dadapetshop.service_flow_control.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDTO(
    @NotBlank(message = "Campo nome não pode estar vazio.")
    String nome,
    @NotBlank(message = "Campo raça não pode estar vazio.")
    String raca,
    @NotNull(message = "Campo idade não pode esta nulo.")
    @Min(message = "Idade não pode ser menor que 0.", value = 0)
    Integer idade,
    @NotNull(message = "Campo peso não pode ser nulo.")
    @Min(message = "Peso não pode ser menor que 0.", value = 0)
    BigDecimal peso,
    @JsonProperty("email_tutor")
    @Email(message = "Email fornecido não está no formato de email.")
    String emailTutor
) {}
