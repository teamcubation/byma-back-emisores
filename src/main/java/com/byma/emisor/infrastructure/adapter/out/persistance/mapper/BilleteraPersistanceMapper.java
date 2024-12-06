package com.byma.emisor.infrastructure.adapter.out.persistance.mapper;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.domain.model.Billetera;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.BilleteraEntity;
import com.byma.emisor.infrastructure.adapter.out.persistance.validation.ValidacionPersistance;

import java.time.LocalDateTime;

public class BilleteraPersistanceMapper {
    public static BilleteraEntity billeteraABilleteraEntity(Billetera billetera) throws ObjetoNuloException {
        ValidacionPersistance.validarParametrosNull(billetera);
        return BilleteraEntity.builder()
                .mail(billetera.getMail())
                .idCuenta(billetera.getIdCuenta())
                .denominacion(billetera.getDenominacion())
                .liquidaEnByma(billetera.getLiquidaEnByma())
                .habilitado(billetera.getHabilitado())
                .fechaAlta(LocalDateTime.now())
                .observaciones(billetera.getObservaciones())
                .idAcdi(billetera.getIdAcdi())
                .build();
    }

    public static Billetera billeteraEntityABilletera(BilleteraEntity billeteraEntity) throws ObjetoNuloException {
        ValidacionPersistance.validarParametrosNull(billeteraEntity);
        return Billetera.builder()
                .id(billeteraEntity.getId())
                .mail(billeteraEntity.getMail())
                .idCuenta(billeteraEntity.getIdCuenta())
                .denominacion(billeteraEntity.getDenominacion())
                .liquidaEnByma(billeteraEntity.getLiquidaEnByma())
                .habilitado(billeteraEntity.getHabilitado())
                .fechaAlta(billeteraEntity.getFechaAlta())
                .observaciones(billeteraEntity.getObservaciones())
                .idAcdi(billeteraEntity.getIdAcdi())
                .build();
    }
}
