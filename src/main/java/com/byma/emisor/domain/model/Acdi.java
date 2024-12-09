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
public class Acdi {

    private long idAcdi;
    private String idOrganizacionAcdi;
    private String denominacion;
    private boolean liquidaEnByma = true;
    private boolean habilitado;
    private boolean billeteras;
    private String observaciones;
    private LocalDateTime fechaAlta;
    private String mail;
    private EstadoAcdi estado = EstadoAcdi.CREADA;

    public boolean acdiLiquidaEnByma() {
        return liquidaEnByma;
    }

    public boolean acdiEstaHabilitado() {
        return habilitado;
    }

    public boolean acdiTieneBilleteras() {
        return billeteras;
    }

}
