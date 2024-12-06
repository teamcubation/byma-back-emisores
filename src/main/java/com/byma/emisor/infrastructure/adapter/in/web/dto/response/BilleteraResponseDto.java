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

    private long id;
    private String mail;
    private String idCuenta;
    private String denominacion;
    private boolean liquidaEnByma;
    private boolean habilitado;
    private String fechaAlta;
    private String observaciones;
    private String idAcdi;
}
