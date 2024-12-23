package com.byma.emisor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionModel {

    private long idSuscripcion;
    private String estado;
    private LocalDateTime fechaAlta;
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
    private Long idBilletera;
    private String fechaSincronizacion;
    private String nasdaqSiStatusReason;
    private int mdwStatusCode;
    private String mdwBusinessMessageId;
    private String mdwResponseMessage;
    private String mdwResponseDatetime;
    private String nasdaqSiStatus;
}
