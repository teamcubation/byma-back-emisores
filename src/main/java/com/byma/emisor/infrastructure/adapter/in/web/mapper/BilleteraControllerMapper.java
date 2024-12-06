package com.byma.emisor.infrastructure.adapter.in.web.mapper;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.domain.model.Billetera;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.BilleteraRequestDto;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.BilleteraResponseDto;
import com.byma.emisor.infrastructure.adapter.in.web.validation.ValidacionController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BilleteraControllerMapper {

    public static Billetera billeteraRequestDtoABilletera(BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException {
        ValidacionController.validarParametrosNull(billeteraRequestDto);
        return Billetera.builder()
                .mail(billeteraRequestDto.getMail())
                .idCuenta(billeteraRequestDto.getIdCuenta())
                .denominacion(billeteraRequestDto.getDenominacion())
                .liquidaEnByma(billeteraRequestDto.getLiquidaEnByma())
                .habilitado(billeteraRequestDto.getHabilitado())
                .fechaAlta(LocalDateTime.now())
                .observaciones(billeteraRequestDto.getObservaciones())
                .idAcdi(billeteraRequestDto.getIdAcdi())
                .build();
    }

    public static BilleteraResponseDto billeteraABilleteraResponseDto(Billetera billetera) throws ObjetoNuloException {
        ValidacionController.validarParametrosNull(billetera);
        return BilleteraResponseDto.builder()
                .id(billetera.getId())
                .mail(billetera.getMail())
                .idCuenta(billetera.getIdCuenta())
                .denominacion(billetera.getDenominacion())
                .liquidaEnByma(billetera.getLiquidaEnByma())
                .habilitado(billetera.getHabilitado())
                .fechaAlta(billetera.getFechaAlta().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .observaciones(billetera.getObservaciones())
                .idAcdi(billetera.getIdAcdi())
                .build();
    }

}
