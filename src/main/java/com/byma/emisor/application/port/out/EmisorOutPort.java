package com.byma.emisor.application.port.out;

import com.byma.emisor.domain.model.Emisor;

import java.util.List;
import java.util.Optional;

public interface EmisorOutPort {

    Optional<Emisor> obtenerPorId(long id);

    List<Emisor> listarEmisores();

    Emisor crear(Emisor emisor);

    void eliminarPorId(long id);

    boolean existeEmisorPorEmailIgnorarMayusculas(String email);

}
