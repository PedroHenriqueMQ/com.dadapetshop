package com.dadapetshop.service_flow_control.dto;

import com.dadapetshop.service_flow_control.validation.annotation.HorarioPermitido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProcedimentoDTO {
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    private String codigoFiscal;
    @NotBlank(message = "Campo atendente não pode estar vazio.")
    private String atendente;
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    private String cliente;
    @NotBlank(message = "Campo procedimento não pode estar vazio.")
    private String procedimento;
    @HorarioPermitido
    private LocalDateTime horarioMarcado;
    @NotNull(message = "Campo valor não pode estar nulo.")
    private BigDecimal valor;
}
