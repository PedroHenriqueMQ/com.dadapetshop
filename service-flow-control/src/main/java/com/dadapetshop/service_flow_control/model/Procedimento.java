package com.dadapetshop.service_flow_control.model;

import com.dadapetshop.service_flow_control.dto.ProfissionalDTO;
import com.dadapetshop.service_flow_control.dto.UsuarioDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "atendimento")
@Builder
public class Procedimento {
    @Id
    private String id;
    private String codigoFiscal;
    private String atendente;
    private String usuarioComprou;
    private String tipoProcedimento;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioTermino;
    private BigDecimal valor;
}
