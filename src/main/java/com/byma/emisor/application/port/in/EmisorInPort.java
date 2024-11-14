package com.byma.emisor.application.port.in;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.domain.model.Emisor;

import java.util.List;

public interface EmisorInPort {

    Emisor crear(Emisor emisorRequestDTO) throws ObjetoNuloException, EmisorDuplicadoException, EmisorNoEncontradoException;

    List<Emisor> listarEmisores();

    Emisor obtenerPorId(long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException;

    Emisor actualizar(Emisor emisorRequestDTO, Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException, EmisorDuplicadoException;

    void eliminar(long idEmisor) throws EmisorNoEncontradoException;
}
