package com.byma.emisor.infrastructure.adapter.in.web.mapper;


import com.byma.emisor.domain.model.Acdi;
import com.byma.emisor.domain.model.EstadoAcdi;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.ActualizarAcdiRequest;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.CrearAcdiRequest;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.AcdiResponseDTO;
import com.byma.emisor.util.validation.Validador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AcdiControllerMapper {

    public static Acdi mapRequestToModel(CrearAcdiRequest crearAcdiRequest) {
        Validador.validarNoNulo(crearAcdiRequest);
        return Acdi.builder()
                .codigoDeAcdi(crearAcdiRequest.getCodigoDeAcdi())
                .denominacion(crearAcdiRequest.getDenominacion())
                .liquidaEnByma(crearAcdiRequest.getLiquidaEnByma())
                .habilitado(crearAcdiRequest.getHabilitado())
                .billeteras(crearAcdiRequest.getBilleteras())
                .observaciones(crearAcdiRequest.getObservaciones())
                .fechaAlta(LocalDateTime.now())
                .mail(crearAcdiRequest.getMail())
                .estado(EstadoAcdi.CREADA)
                .build();
    }

    public static AcdiResponseDTO mapModelToResponseDTO(Acdi acdi) {
        Validador.validarNoNulo(acdi);
        return AcdiResponseDTO.builder()
                .idAcdi(acdi.getIdAcdi())
                .codigoDeAcdi(acdi.getCodigoDeAcdi())
                .denominacion(acdi.getDenominacion())
                .liquidaEnByma(acdi.acdiLiquidaEnByma())
                .habilitado(acdi.acdiEstaHabilitado())
                .billeteras(acdi.acdiTieneBilleteras())
                .observaciones(acdi.getObservaciones())
                .fechaAlta(acdi.getFechaAlta().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .mail(acdi.getMail())
                .estado(acdi.getEstado())
                .build();
    }

    public static Acdi acdiActualizarSolicitudAAcdiModel(ActualizarAcdiRequest actualizarAcdiRequest) {
        Validador.validarNoNulo(actualizarAcdiRequest);
        return Acdi.builder()
                .mail(actualizarAcdiRequest.getMail())
                .liquidaEnByma(actualizarAcdiRequest.getLiquidaEnByma())
                .build();
    }

}
