package com.byma.emisor.infrastructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EspecieResponseDTO {
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
