package com.byma.emisor.infrastructure.adapter.out.persistance.mapper;

import com.byma.emisor.domain.model.Acdi;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.AcdiEntity;
import com.byma.emisor.util.validation.Validador;

import java.util.List;
import java.util.stream.Collectors;

public class AcdiPersistanceMapper {

    public static Acdi acdiEntityAAcdiModel(AcdiEntity acdiEntity) {
        Validador.validarNoNulo(acdiEntity);
        return Acdi.builder()
                .idAcdi(acdiEntity.getIdAcdi())
                .codigoDeAcdi(acdiEntity.getCodigoDeAcdi())
                .denominacion(acdiEntity.getDenominacion())
                .liquidaEnByma(acdiEntity.getLiquidaEnByma())
                .habilitado(acdiEntity.getHabilitado())
                .billeteras(acdiEntity.getBilleteras())
                .observaciones(acdiEntity.getObservaciones())
                .fechaAlta(acdiEntity.getFechaAlta())
                .mail(acdiEntity.getMail())
                .estado(acdiEntity.getEstado())
                .build();
    }

    public static AcdiEntity acdiModelAAcdiEntity(Acdi acdi) {
        Validador.validarNoNulo(acdi);
        return AcdiEntity.builder()
                .idAcdi(acdi.getIdAcdi())
                .codigoDeAcdi(acdi.getCodigoDeAcdi())
                .denominacion(acdi.getDenominacion())
                .liquidaEnByma(acdi.acdiLiquidaEnByma())
                .habilitado(acdi.acdiEstaHabilitado())
                .billeteras(acdi.acdiTieneBilleteras())
                .observaciones(acdi.getObservaciones())
                .fechaAlta(acdi.getFechaAlta())
                .mail(acdi.getMail())
                .estado(acdi.getEstado())
                .build();
    }

    public static List<Acdi> acdiEntitiesAAcdiModels(List<AcdiEntity> acdiEntities) {
        return acdiEntities.stream()
                .map(AcdiPersistanceMapper::acdiEntityAAcdiModel)
                .collect(Collectors.toList());
    }
}
