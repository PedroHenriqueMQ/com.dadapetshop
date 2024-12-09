package com.dadapetshop.service_flow_control.service;

import com.dadapetshop.service_flow_control.dto.ConsultaDTO;
import com.dadapetshop.service_flow_control.dto.ProcedimentoDTO;
import com.dadapetshop.service_flow_control.model.Consulta;
import com.dadapetshop.service_flow_control.model.Procedimento;
import com.dadapetshop.service_flow_control.repository.ConsultaRepository;
import com.dadapetshop.service_flow_control.repository.ProcedimentoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadapetshop.service_flow_control.constants.RabbitConstants;
import com.dadapetshop.service_flow_control.messenger.RabbbitMQProducer;

@Service
public class AtendimentoService extends RabbbitMQProducer {
    private final ProcedimentoRepository procedimentoRepository;
    private final ConsultaRepository consultaRepository;

    @Autowired
    public AtendimentoService(
        ProcedimentoRepository procedimentoRepository,
        ConsultaRepository consultaRepository,
        RabbitTemplate rabbitTemplate
    ) {
        super(rabbitTemplate);
        this.procedimentoRepository = procedimentoRepository;
        this.consultaRepository = consultaRepository;
    }

    public Boolean validarAtendimento(Object atendimentoDTO) {
        return sendMessageAndReceiveValidityConfirmation(RabbitConstants.PET_ATTENDANCE_QUEUE, atendimentoDTO);
    }

    public boolean marcarProcedimento(ProcedimentoDTO procedimentoDTO) {
        var horarioTerminoDTO = procedimentoDTO.horarioMarcado().plusHours(1L);

        if (
            procedimentoRepository.findByHorarioInicioBetween(
                procedimentoDTO.horarioMarcado(), horarioTerminoDTO
            ).isPresent()
        ) return false;

        if (procedimentoRepository.findByCodigoFiscal(procedimentoDTO.codigoFiscal()).isPresent()) return false;

        var procedimento = Procedimento.builder()
                .tipoProcedimento(procedimentoDTO.procedimento())
                .codigoFiscal(procedimentoDTO.codigoFiscal())
                .atendente(procedimentoDTO.atendente())
                .usuarioComprou(procedimentoDTO.cliente())
                .horarioInicio(procedimentoDTO.horarioMarcado())
                .horarioTermino(horarioTerminoDTO)
                .valor(procedimentoDTO.valor())
                .build();

        procedimentoRepository.save(procedimento);
        return true;
    }

    public boolean marcarConsulta(ConsultaDTO consultaDTO) {
        var horarioTerminoDTO = consultaDTO.horarioMarcado().plusHours(1L);

        if (
                consultaRepository.findByHorarioInicioBetween(
                        consultaDTO.horarioMarcado(), horarioTerminoDTO
                ).isPresent()
        ) return false;

        if (consultaRepository.findByCodigoFiscal(consultaDTO.codigoFiscal()).isPresent()) return false;

        var consulta = Consulta.builder()
                .codigoFiscal(consultaDTO.codigoFiscal())
                .status(consultaDTO.status())
                .valor(consultaDTO.valor())
                .medicamentos(consultaDTO.medicamentos())
                .horarioInicio(consultaDTO.horarioMarcado())
                .horarioTermino(horarioTerminoDTO)
                .usuarioComprou(consultaDTO.cliente())
                .veterinario(consultaDTO.veterinario())
                .build();

        consultaRepository.save(consulta);
        return true;
    }
}
