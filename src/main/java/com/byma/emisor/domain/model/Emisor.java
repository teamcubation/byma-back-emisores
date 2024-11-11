package com.byma.emisor.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Emisor {
    private long id;

    private String denominacion;

    private String email;

    private LocalDate fechaAlta;

    private String cuentaEmisor;

    private long idOrganizacion;

    private long idEntidadLegal;


}
