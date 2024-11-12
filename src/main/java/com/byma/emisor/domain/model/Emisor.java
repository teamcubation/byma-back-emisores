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
public class Emisor {
    private long id;

    private String denominacion;

    private String email;

    private LocalDateTime fechaAlta;

    private String cuentaEmisor;

    private Long idOrganizacion;

    private Long idEntidadLegal;
}
