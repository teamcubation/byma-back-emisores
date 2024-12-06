package com.byma.emisor.application.port.out;


import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.domain.model.SuscripcionModel;

import java.util.List;
import java.util.Optional;

public interface SuscripcionOutPort {
    Optional<SuscripcionModel> obtenerPorId(long id);
    List<SuscripcionModel> listarSuscripciones();
    SuscripcionModel crear(SuscripcionModel suscripcion);
    void eliminarPorId(long id) throws SuscripcionNoEncontradaException;
    SuscripcionModel actualizar(SuscripcionModel suscripcion);
}
