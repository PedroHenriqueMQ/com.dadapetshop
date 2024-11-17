package com.dadapetshop.registrations.dto;

import java.math.BigDecimal;

public record PetDTO(
    String nome,
    String raca,
    Integer idade,
    BigDecimal peso
) {}
