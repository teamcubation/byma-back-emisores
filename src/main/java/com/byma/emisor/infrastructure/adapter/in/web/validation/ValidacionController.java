package com.byma.emisor.infrastructure.adapter.in.web.validation;

import com.byma.emisor.application.exception.ObjetoNuloException;

public class ValidacionController {
    public static final String PARAMETRO_INVALIDO = "Los parametros no pueden ser null";

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
