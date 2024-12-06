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
public class Billetera {

    private long id;
    private String mail;
    private String idCuenta;
    private String denominacion;
    private Boolean liquidaEnByma;
    private Boolean habilitado;
    private LocalDateTime fechaAlta;
    private String observaciones;
    private String idAcdi;

}
