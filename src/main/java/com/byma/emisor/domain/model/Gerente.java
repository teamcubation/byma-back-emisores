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
public class Gerente {

    private long id;
    private String denominacion;
    @Builder.Default
    private boolean liquidaEnByma = true;
    private boolean habilitado;
    private String observaciones;
    private String emailGerente;
    @Builder.Default
    private LocalDateTime fechaDeAlta = LocalDateTime.now();


    public long getId() {
        return id;
    }

    public boolean liquidaEnByma() {
        return liquidaEnByma;
    }

    public boolean estaHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setLiquidaEnByma(boolean liquidaEnByma) {
        this.liquidaEnByma = liquidaEnByma;
    }
}
