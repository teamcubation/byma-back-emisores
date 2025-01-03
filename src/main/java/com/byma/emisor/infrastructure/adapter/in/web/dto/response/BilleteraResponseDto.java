package com.byma.emisor.infrastructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BilleteraResponseDto {

    private Long id;
    private String mail;
    private Long idCuenta;
    private String denominacion;
    private Boolean liquidaEnByma;
    private Boolean habilitado;
    private String fechaAlta;
    private String observaciones;
    private Long idAcdi;
}
