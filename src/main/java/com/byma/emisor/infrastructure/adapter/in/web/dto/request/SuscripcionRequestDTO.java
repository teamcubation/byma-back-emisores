package com.byma.emisor.infrastructure.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionRequestDTO {

    private String estado;
    private int nroCertificado;
    private long idEspecie;
    private int cantCuotapartes;
    private long idAcdi;
    private long idEmisor;
    private int nroPedido;
    private int nroSecuencia;
    private String fechaCambioDeEstado;
    private String rolIngresante;
    private double monto;
    private boolean liquidaEnByma;
    private int numeroReferencia;
    private boolean procesadoCustodia;
    private String ultimoError;
    private String command;
    private boolean procesadoLiquidacionesSlyq;
    private long idGerente;
    private boolean obligacionDePagoGenerada;
    private long idBilletera;
    private String fechaSincronizacion;
    private String nasdaqSiStatusReason;
    private int mdwStatusCode;
    private String mdwBusinessMessageId;
    private String mdwResponseMessage;
    private String mdwResponseDatetime;
    private String nasdaqSiStatus;
}