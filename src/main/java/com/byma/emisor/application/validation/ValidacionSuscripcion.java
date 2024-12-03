package com.byma.emisor.application.validation;

import com.byma.emisor.application.exception.IdNuloException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.domain.model.SuscripcionModel;

public class ValidacionSuscripcion {
    public static void validarIdNotNull(long id) throws IdNuloException {
        if (id == 0) {
            throw new IdNuloException("El id enviado no puede ser nulo.");
        }
    }
    public static void validarSuscripcion(SuscripcionModel suscripcion) throws ObjetoNuloException {
        validarObjetoNotNull(suscripcion);
        validarAtributosNulos(suscripcion);
    }

    public static void validarObjetoNotNull(Object object) throws ObjetoNuloException {
        if (object == null) {
            throw new ObjetoNuloException("El objeto enviado no puede ser nulo.");
        }
    }

    public static void validarAtributosNulos(SuscripcionModel suscripcion) {

    }
}
