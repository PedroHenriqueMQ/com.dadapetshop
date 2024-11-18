package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PetDTO(
    String nome,
    String raca,
    Integer idade,
    BigDecimal peso,
    @JsonProperty("email_tutor")
    String emailTutor
) {}
