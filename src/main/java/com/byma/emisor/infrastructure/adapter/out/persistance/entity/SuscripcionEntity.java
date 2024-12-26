package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Suscripcion")
@Table(name = "suscripciones")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SuscripcionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUSCRIPCION")
    private long idSuscripcion;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "FECHA_ALTA")
    private LocalDateTime fechaAlta;

    @Column(name = "NRO_CERTIFICADO")
    private int nroCertificado;

    @Column(name = "ID_ESPECIE")
    private long idEspecie;

    @Column(name = "CANT_CUOTAPARTES")
    private int cantCuotapartes;

    @Column(name = "ID_ACDI")
    private long idAcdi;
  
    private long codigoDeAcdi;
  
    private long cuentaOperativa;
  
    @Column(name = "ID_EMISOR")
    private long idEmisor;

    @Column(name = "NRO_PEDIDO")
    private int nroPedido;

    @Column(name = "NRO_SECUENCIA")
    private int nroSecuencia;

    @Column(name = "FECHA_CAMBIO_DE_ESTADO")
    private String fechaCambioDeEstado;

    @Column(name = "ROL_INGRESANTE")
    private String rolIngresante;

    @Column(name = "MONTO")
    private double monto;

    @Column(name = "LIQUIDA_EN_BYMA")
    private boolean liquidaEnByma;

    @Column(name = "NUMERO_REFERENCIA")
    private int numeroReferencia;

    @Column(name = "PROCESADO_CUSTODIA")
    private boolean procesadoCustodia;

    @Column(name = "ULTIMO_ERROR")
    private String ultimoError;

    @Column(name = "COMMAND")
    private String command;

    @Column(name = "PROCESADO_LIQUIDACIONES_SLYQ")
    private boolean procesadoLiquidacionesSlyq;

    @Column(name = "ID_GERENTE")
    private long idGerente;

    @Column(name = "OBLIGACION_DE_PAGO_GENERADA")
    private boolean obligacionDePagoGenerada;

    @Column(name = "ID_BILLETERA")
    private Long idBilletera;

    @Column(name = "FECHA_SINCRONIZACION")
    private String fechaSincronizacion;

    @Column(name = "NASDAQ_SI_STATUS_REASON")
    private String nasdaqSiStatusReason;

    @Column(name = "MDW_STATUS_CODE")
    private int mdwStatusCode;

    @Column(name = "MDW_BUSINESS_MESSAGE_ID")
    private String mdwBusinessMessageId;

    @Column(name = "MDW_RESPONSE_MESSAGE")
    private String mdwResponseMessage;

    @Column(name = "MDW_RESPONSE_DATETIME")
    private String mdwResponseDatetime;

    @Column(name = "NASDAQ_SI_STATUS")
    private String nasdaqSiStatus;

    private double precio;
}
