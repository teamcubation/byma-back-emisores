package com.byma.emisor.infrastructure.adapter.in.web.mapper;

import com.byma.emisor.domain.model.SuscripcionModel;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.SuscripcionRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.SuscripcionResponseDTO;
import com.byma.emisor.infrastructure.adapter.in.web.validation.ValidacionController;

public class SuscripcionControllerMapper {
    public static SuscripcionResponseDTO suscripcionASuscripcionResponseDto(SuscripcionModel suscripcion) {
        ValidacionController.validarObjetoNotNull(suscripcion);
        return SuscripcionResponseDTO.builder()
                .idSuscripcion(suscripcion.getIdSuscripcion())
                .estado(suscripcion.getEstado())
                .fechaAlta(String.valueOf(suscripcion.getFechaAlta()))
                .nroCertificado(suscripcion.getNroCertificado())
                .idEspecie(suscripcion.getIdEspecie())
                .cantCuotapartes(suscripcion.getCantCuotapartes())
                .idAcdi(suscripcion.getIdAcdi())
                .codigoDeAcdi(suscripcion.getCodigoDeAcdi())
                .cuentaOperativa(suscripcion.getCuentaOperativa())
                .idEmisor(suscripcion.getIdEmisor())
                .nroPedido(suscripcion.getNroPedido())
                .nroSecuencia(suscripcion.getNroSecuencia())
                .fechaCambioDeEstado(suscripcion.getFechaCambioDeEstado())
                .rolIngresante(suscripcion.getRolIngresante())
                .monto(suscripcion.getMonto())
                .liquidaEnByma(suscripcion.isLiquidaEnByma())
                .numeroReferencia(suscripcion.getNumeroReferencia())
                .procesadoCustodia(suscripcion.isProcesadoCustodia())
                .ultimoError(suscripcion.getUltimoError())
                .command(suscripcion.getCommand())
                .procesadoLiquidacionesSlyq(suscripcion.isProcesadoLiquidacionesSlyq())
                .idGerente(suscripcion.getIdGerente())
                .obligacionDePagoGenerada(suscripcion.isObligacionDePagoGenerada())
                .idBilletera(suscripcion.getIdBilletera())
                .fechaSincronizacion(suscripcion.getFechaSincronizacion())
                .nasdaqSiStatusReason(suscripcion.getNasdaqSiStatusReason())
                .mdwStatusCode(suscripcion.getMdwStatusCode())
                .mdwBusinessMessageId(suscripcion.getMdwBusinessMessageId())
                .mdwResponseMessage(suscripcion.getMdwResponseMessage())
                .mdwResponseDatetime(suscripcion.getMdwResponseDatetime())
                .nasdaqSiStatus(suscripcion.getNasdaqSiStatus())
                .build();
    }
    public static SuscripcionModel suscripcionRequestDTOASuscripcionModel(SuscripcionRequestDTO requestDTO) {
        ValidacionController.validarObjetoNotNull(requestDTO);
        return SuscripcionModel.builder()
                .estado(requestDTO.getEstado())
                .nroCertificado(requestDTO.getNroCertificado())
                .idEspecie(requestDTO.getIdEspecie())
                .cantCuotapartes(requestDTO.getCantCuotapartes())
                .idAcdi(requestDTO.getIdAcdi())
                .codigoDeAcdi(requestDTO.getCodigoDeAcdi())
                .cuentaOperativa(requestDTO.getCuentaOperativa())
                .idEmisor(requestDTO.getIdEmisor())
                .nroPedido(requestDTO.getNroPedido())
                .nroSecuencia(requestDTO.getNroSecuencia())
                .fechaCambioDeEstado(requestDTO.getFechaCambioDeEstado())
                .rolIngresante(requestDTO.getRolIngresante())
                .monto(requestDTO.getMonto())
                .liquidaEnByma(requestDTO.isLiquidaEnByma())
                .numeroReferencia(requestDTO.getNumeroReferencia())
                .procesadoCustodia(requestDTO.isProcesadoCustodia())
                .ultimoError(requestDTO.getUltimoError())
                .command(requestDTO.getCommand())
                .procesadoLiquidacionesSlyq(requestDTO.isProcesadoLiquidacionesSlyq())
                .idGerente(requestDTO.getIdGerente())
                .obligacionDePagoGenerada(requestDTO.isObligacionDePagoGenerada())
                .idBilletera(requestDTO.getIdBilletera())
                .fechaSincronizacion(requestDTO.getFechaSincronizacion())
                .nasdaqSiStatusReason(requestDTO.getNasdaqSiStatusReason())
                .mdwStatusCode(requestDTO.getMdwStatusCode())
                .mdwBusinessMessageId(requestDTO.getMdwBusinessMessageId())
                .mdwResponseMessage(requestDTO.getMdwResponseMessage())
                .mdwResponseDatetime(requestDTO.getMdwResponseDatetime())
                .nasdaqSiStatus(requestDTO.getNasdaqSiStatus())
                .build();
    }

}
