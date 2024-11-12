package com.byma.emisor.infrastructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmisorResponseDTO {
    private long id;

    private String denominacion;

    private String email;

    private LocalDate fechaAlta;

    private String cuentaEmisor;

    private long idOrganizacion;

    private long idEntidadLegal;
}
