package com.byma.emisor.infrastructure.adapter.in.web.mapper;

import com.byma.emisor.domain.model.Emisor;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EmisorRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.EmisorResponseDTO;

public class EmisorControllerMapper {

    public static Emisor emisorRequestDtoAEmisor(EmisorRequestDTO emisorRequest) {
        //TODO: validar que todos los parametros no sean null
        return Emisor.builder()
                .denominacion(emisorRequest.getDenominacion())
                .email(emisorRequest.getEmail())
                .fechaAlta(emisorRequest.getFechaAlta())
                .cuentaEmisor(emisorRequest.getCuentaEmisor())
                .idOrganizacion(emisorRequest.getIdOrganizacion())
                .idEntidadLegal(emisorRequest.getIdEntidadLegal())
                .build();
    }

    public static EmisorResponseDTO emisorAEmisorResponseDto(Emisor emisor) {
        //TODO: validar que todos los parametros no sean null
        return EmisorResponseDTO.builder()
                .id(emisor.getId())
                .denominacion(emisor.getDenominacion())
                .email(emisor.getEmail())
                .fechaAlta(emisor.getFechaAlta())
                .cuentaEmisor(emisor.getCuentaEmisor())
                .idOrganizacion(emisor.getIdOrganizacion())
                .idEntidadLegal(emisor.getIdEntidadLegal())
                .build();
    }
}
