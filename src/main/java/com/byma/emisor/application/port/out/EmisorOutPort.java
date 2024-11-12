package com.byma.emisor.application.port.out;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.domain.model.Emisor;

import java.util.List;
import java.util.Optional;

public interface EmisorOutPort {

    Optional<Emisor> obtenerPorId(long id);

    List<Emisor> listarEmisores();

    Emisor crear(Emisor emisor) throws EmisorNoEncontradoException, EmisorDuplicadoException;

    void eliminarPorId(long id) throws EmisorNoEncontradoException;

    boolean existeEmisorPorEmailIgnorarMayusculas(String email);

    Emisor actualizar(Emisor emisor) throws EmisorNoEncontradoException, EmisorDuplicadoException;

}
