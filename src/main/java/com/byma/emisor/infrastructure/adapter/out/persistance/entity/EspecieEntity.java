package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "especie")
@Table(name = "especie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspecieEntity {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecie;
    private String codigoCVSA;
    private String denominacion;
    private Integer laminaMinima;
    private Double precio;
    private String cafci;
    private String cuentaDeEmision;
    private String estado;
    private Long idEmisor;
    private Long idGerente;
    private LocalDate vigencia;
    private LocalDate plazoDeLiquidacion;
    private String codigoCNV;
    private String isin;
    private String familiaDeFondos;
    private String observaciones;
    private Long idMoneda;
    private LocalDate fechaAlta;
}
