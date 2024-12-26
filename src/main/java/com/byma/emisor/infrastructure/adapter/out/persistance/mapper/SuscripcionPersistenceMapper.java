package com.byma.emisor.infrastructure.adapter.out.persistance.mapper;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.domain.model.SuscripcionModel;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.SuscripcionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SuscripcionPersistenceMapper {

    public static SuscripcionModel SuscripcionEntityToModel(SuscripcionEntity suscripcionEntity) {
        validarNulo(suscripcionEntity);
        return SuscripcionModel.builder()
                .idSuscripcion(suscripcionEntity.getIdSuscripcion())
                .estado(suscripcionEntity.getEstado())
                .fechaAlta(suscripcionEntity.getFechaAlta())
                .nroCertificado(suscripcionEntity.getNroCertificado())
                .idEspecie(suscripcionEntity.getIdEspecie())
                .cantCuotapartes(suscripcionEntity.getCantCuotapartes())
                .idAcdi(suscripcionEntity.getIdAcdi())
                .codigoDeAcdi(suscripcionEntity.getCodigoDeAcdi())
                .cuentaOperativa(suscripcionEntity.getCuentaOperativa())
                .idEmisor(suscripcionEntity.getIdEmisor())
                .nroPedido(suscripcionEntity.getNroPedido())
                .nroSecuencia(suscripcionEntity.getNroSecuencia())
                .fechaCambioDeEstado(suscripcionEntity.getFechaCambioDeEstado())
                .rolIngresante(suscripcionEntity.getRolIngresante())
                .monto(suscripcionEntity.getMonto())
                .liquidaEnByma(suscripcionEntity.isLiquidaEnByma())
                .numeroReferencia(suscripcionEntity.getNumeroReferencia())
                .procesadoCustodia(suscripcionEntity.isProcesadoCustodia())
                .ultimoError(suscripcionEntity.getUltimoError())
                .command(suscripcionEntity.getCommand())
                .procesadoLiquidacionesSlyq(suscripcionEntity.isProcesadoLiquidacionesSlyq())
                .idGerente(suscripcionEntity.getIdGerente())
                .obligacionDePagoGenerada(suscripcionEntity.isObligacionDePagoGenerada())
                .idBilletera(suscripcionEntity.getIdBilletera())
                .fechaSincronizacion(suscripcionEntity.getFechaSincronizacion())
                .nasdaqSiStatusReason(suscripcionEntity.getNasdaqSiStatusReason())
                .mdwStatusCode(suscripcionEntity.getMdwStatusCode())
                .mdwBusinessMessageId(suscripcionEntity.getMdwBusinessMessageId())
                .mdwResponseMessage(suscripcionEntity.getMdwResponseMessage())
                .mdwResponseDatetime(suscripcionEntity.getMdwResponseDatetime())
                .nasdaqSiStatus(suscripcionEntity.getNasdaqSiStatus())
                .precio(suscripcionEntity.getPrecio())
                .build();
    }


    public static SuscripcionEntity SuscripcionModelToEntity(SuscripcionModel suscripcionModel) {
        validarNulo(suscripcionModel);
        return SuscripcionEntity.builder()
                .idSuscripcion(suscripcionModel.getIdSuscripcion())
                .estado(suscripcionModel.getEstado())
                .fechaAlta(suscripcionModel.getFechaAlta())
                .nroCertificado(suscripcionModel.getNroCertificado())
                .idEspecie(suscripcionModel.getIdEspecie())
                .cantCuotapartes(suscripcionModel.getCantCuotapartes())
                .idAcdi(suscripcionModel.getIdAcdi())
                .codigoDeAcdi(suscripcionModel.getCodigoDeAcdi())
                .cuentaOperativa(suscripcionModel.getCuentaOperativa())
                .idEmisor(suscripcionModel.getIdEmisor())
                .nroPedido(suscripcionModel.getNroPedido())
                .nroSecuencia(suscripcionModel.getNroSecuencia())
                .fechaCambioDeEstado(suscripcionModel.getFechaCambioDeEstado())
                .rolIngresante(suscripcionModel.getRolIngresante())
                .monto(suscripcionModel.getMonto())
                .liquidaEnByma(suscripcionModel.isLiquidaEnByma())
                .numeroReferencia(suscripcionModel.getNumeroReferencia())
                .procesadoCustodia(suscripcionModel.isProcesadoCustodia())
                .ultimoError(suscripcionModel.getUltimoError())
                .command(suscripcionModel.getCommand())
                .procesadoLiquidacionesSlyq(suscripcionModel.isProcesadoLiquidacionesSlyq())
                .idGerente(suscripcionModel.getIdGerente())
                .obligacionDePagoGenerada(suscripcionModel.isObligacionDePagoGenerada())
                .idBilletera(suscripcionModel.getIdBilletera())
                .fechaSincronizacion(suscripcionModel.getFechaSincronizacion())
                .nasdaqSiStatusReason(suscripcionModel.getNasdaqSiStatusReason())
                .mdwStatusCode(suscripcionModel.getMdwStatusCode())
                .mdwBusinessMessageId(suscripcionModel.getMdwBusinessMessageId())
                .mdwResponseMessage(suscripcionModel.getMdwResponseMessage())
                .mdwResponseDatetime(suscripcionModel.getMdwResponseDatetime())
                .nasdaqSiStatus(suscripcionModel.getNasdaqSiStatus())
                .precio(suscripcionModel.getPrecio())
                .build();
    }

    public static List<SuscripcionModel> suscripcionEntitiesToSuscripcionModels(List<SuscripcionEntity> suscripcionEntities) {
        validarNulo(suscripcionEntities);
        return suscripcionEntities.stream()
                .map(SuscripcionPersistenceMapper::SuscripcionEntityToModel)
                .collect(Collectors.toList());
    }

    private static void validarNulo(Object objeto) {
        if (objeto == null) {
            throw new ObjetoNuloException("El objeto no puede ser nulo.");
        }
    }
}
