package com.byma.emisor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Especie {
    private long idEspecie;
    private String codigoCVSA;
    private String denominacion;
    private int laminaMinima;
    private Double precio;
    private String cafci;
    private String cuentaDeEmision;
    private String estado;
    private long idEmisor;
    private long idGerente;
    private LocalDate vigencia;
    private LocalDate plazoDeLiquidacion;
    private String codigoCNV;
    private String isin;
    private String familiaDeFondos;
    private String observaciones;
    private long idMoneda;
    private LocalDate fechaAlta;
}
