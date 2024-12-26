package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "especie")
@Table(name = "especies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspecieEntity {

    @Id
    @Column(name = "ID_ESPECIE", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecie;

    @Column(name = "CODIGO_CVSA")
    private String codigoCVSA;

    @Column(name = "DENOMINACION")
    private String denominacion;

    @Column(name = "LAMINA_MINIMA")
    private Integer laminaMinima;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "CAFCI")
    private String cafci;

    @Column(name = "CUENTA_DE_EMISION")
    private String cuentaDeEmision;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "ID_EMISOR")
    private Long idEmisor;

    @Column(name = "ID_GERENTE")
    private Long idGerente;

    @Column(name = "VIGENCIA")
    private LocalDate vigencia;

    @Column(name = "PLAZO_DE_LIQUIDACION")
    private LocalDate plazoDeLiquidacion;

    @Column(name = "CODIGO_CNV")
    private String codigoCNV;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "FAMILIA_DE_FONDOS")
    private String familiaDeFondos;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "ID_MONEDA")
    private Long idMoneda;

    @Column(name = "FECHA_ALTA")
    private LocalDate fechaAlta;

}
