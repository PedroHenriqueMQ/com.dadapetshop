package com.dadapetshop.service_flow_control.controller;

import com.dadapetshop.service_flow_control.dto.AtendimentoDTO;
import com.dadapetshop.service_flow_control.dto.ConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dadapetshop.service_flow_control.dto.ProcedimentoDTO;
import com.dadapetshop.service_flow_control.service.AtendimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/service")
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping("/schedule-appointment")
    public ResponseEntity<String> marcarAtendimento(@RequestBody AtendimentoDTO atendimentoDTO) {
        Boolean aprovado = atendimentoService.validarAtendimento(atendimentoDTO);

        if (aprovado == null)
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Timeout na resposta do serviço!");
        else if (!aprovado)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atendimento não passou da etapa de validação!");

        var atendimentoMarcado = false;

        if (atendimentoDTO instanceof ConsultaDTO) {
            var consultaDTO = (ConsultaDTO) atendimentoDTO;
            atendimentoMarcado = atendimentoService.marcarConsulta(consultaDTO);
        } else if (atendimentoDTO instanceof ProcedimentoDTO) {
            var procedimentoDTO = (ProcedimentoDTO) atendimentoDTO;
            atendimentoMarcado = atendimentoService.marcarProcedimento(procedimentoDTO);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de atendimento não reconhecido!");

        return atendimentoMarcado ?
            ResponseEntity.ok("Atendimento marcado com sucesso!") :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atendimento não passou da etapa de validação!");
    }
}
