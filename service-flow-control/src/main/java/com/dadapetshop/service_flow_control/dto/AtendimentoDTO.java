package com.dadapetshop.service_flow_control.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProcedimentoDTO.class, name = "procedimento"),
        @JsonSubTypes.Type(value = ConsultaDTO.class, name = "consulta")
})
public interface AtendimentoDTO { }