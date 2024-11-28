package com.dadapetshop.service_flow_control.model;

import com.dadapetshop.service_flow_control.dto.ProdutoDTO;
import com.dadapetshop.service_flow_control.dto.ProfissionalDTO;
import com.dadapetshop.service_flow_control.dto.UsuarioDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "atendimento")
@Builder
public class Consulta {
    @Id
    private String id;
    private String codigoFiscal;
    @DBRef
    private ProfissionalDTO veterinario;
    @DBRef
    private UsuarioDTO usuarioComprou;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioTermino;
    private String status;
    private List<ProdutoDTO> medicamentos = new ArrayList<>();
    private BigDecimal valor;
}
