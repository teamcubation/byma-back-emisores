package com.byma.emisor.application.port.in;

import com.byma.emisor.domain.model.Emisor;

import java.util.List;

public interface EmisorInPort {

    Emisor crear(Emisor emisorRequestDTO);

    List<Emisor> listarEmisores();

    Emisor obtenerPorId(Long idEmisor);

    Emisor actualizar(Emisor emisorRequestDTO, Long idEmisor);

    void eliminar(Long idEmisor);
}
