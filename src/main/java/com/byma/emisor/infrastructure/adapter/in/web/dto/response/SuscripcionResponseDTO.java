package com.byma.emisor.infrastructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionResponseDTO {
    private long idSuscripcion;
    private String estado;
    private String fechaAlta;
    private int nroCertificado;
    private long idEspecie;
    private int cantCuotapartes;
    private long idAcdi;
    private long codigoDeAcdi;
    private long cuentaOperativa;
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
    private double precio;
}
