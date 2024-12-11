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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "compra")
@Builder
public class Compra {
    @Id
    private String id;
    private String codigoFiscal;
    private String usuarioComprou;
    private List<String> produtos = new ArrayList<>();
    private BigDecimal valorTotal;
}
