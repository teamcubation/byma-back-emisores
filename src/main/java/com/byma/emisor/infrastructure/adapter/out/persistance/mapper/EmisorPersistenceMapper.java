package com.byma.emisor.infrastructure.adapter.out.persistance.mapper;

import com.byma.emisor.domain.model.Emisor;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.EmisorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmisorPersistenceMapper {

    public static Emisor emisorEntityToEmisorModel(EmisorEntity emisorEntity) {
        validarNulo(emisorEntity);
        return Emisor.builder()
                .id(emisorEntity.getId())
                .denominacion(emisorEntity.getDenominacion())
                .mail(emisorEntity.getMail())
                .fechaAlta(emisorEntity.getFechaAlta())
                .cuentaEmisor(emisorEntity.getCuentaEmisor())
                .idOrganizacion(emisorEntity.getIdOrganizacion())
                .idEntidadLegal(emisorEntity.getIdEntidadLegal())
                .build();
    }

    public static EmisorEntity emisorModelToEmisorEntity(Emisor emisor) {
        validarNulo(emisor);
        return EmisorEntity.builder()
                .id(emisor.getId())
                .denominacion(emisor.getDenominacion())
                .mail(emisor.getMail())
                .fechaAlta(emisor.getFechaAlta())
                .cuentaEmisor(emisor.getCuentaEmisor())
                .idOrganizacion(emisor.getIdOrganizacion())
                .idEntidadLegal(emisor.getIdEntidadLegal())
                .build();
    }

    public static List<Emisor> emisorEntitiesToEmisorModels(List<EmisorEntity> emisorEntities) {
        validarNulo(emisorEntities);
        return emisorEntities.stream()
                .map(EmisorPersistenceMapper::emisorEntityToEmisorModel)
                .collect(Collectors.toList());
    }

    private static boolean validarNulo(Object objeto) {
        return objeto == null;
    }

}
