package com.dadapetshop.service_flow_control.dto;

import com.dadapetshop.service_flow_control.validation.annotation.HorarioPermitido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ConsultaDTO {
    @NotBlank(message = "Campo código fiscal não pode estar vazio.")
    private String codigoFiscal;
    @NotBlank(message = "Campo veterinário não pode estar vazio.")
    private String veterinario;
    @NotBlank(message = "Campo cliente não pode estar vazio.")
    private String cliente;
    @HorarioPermitido
    @NotNull(message = "Campo horário não pode ser nulo.")
    private LocalDateTime horarioMarcado;
    @NotBlank(message = "Campo status não pode estar vazio.")
    private String status;
    private List<ProdutoDTO> medicamentos;
    @Min(message = "O valor não pode ser menor que 0.", value = 0)
    @NotNull(message = "Campo valor não pode ser nulo.")
    private BigDecimal valor;
}
