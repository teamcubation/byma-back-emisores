package com.byma.emisor.infrastructure.adapter.out.persistance.validation;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.infrastructure.adapter.in.web.validation.ValidacionController;

public class ValidacionPersistance {
    public static final String PARAMETRO_INVALIDO = "Los parametros no pueden ser null";
    public static final String OBJETO_NO_ENCONTRADO = "Objeto no encontrado";

    public static boolean nullParams(Object ...objects){
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    public static void validarParametrosNull(Object... objects) throws ObjetoNuloException {
        if (ValidacionController.nullParams(objects)) {
            throw new ObjetoNuloException(PARAMETRO_INVALIDO);
        }
    }
}
