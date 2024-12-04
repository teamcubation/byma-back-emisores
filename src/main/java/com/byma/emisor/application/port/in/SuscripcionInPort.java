package com.byma.emisor.application.port.in;

import com.byma.emisor.application.exception.*;
import com.byma.emisor.domain.model.SuscripcionModel;

import java.util.List;

public interface SuscripcionInPort {
    SuscripcionModel crear(SuscripcionModel suscripcionRequestDTO);
    List<SuscripcionModel> listarSuscripciones();
    SuscripcionModel obtenerPorId(long idSuscripcion) throws IdNuloException, SuscripcionNoEncontradaException;
    SuscripcionModel actualizar(SuscripcionModel suscripcionRequestDTO, long idSuscripcion) throws IdNuloException, SuscripcionNoEncontradaException;
    void eliminar(long idSuscripcion) throws SuscripcionNoEncontradaException;
}