package com.byma.emisor.application.validation;

import com.byma.emisor.application.exception.ObjetoNuloException;

public class ValidacionService {
    public static final String PARAMETROS_INVALIDOS = "Los parametros no pueden ser null";
    public static final String EMISOR_DUPLICADO = "No puede haber emisores duplicados";
    public static final String EMISOR_NO_ENCONTRADO = "Emisor no encontrado";

    public static boolean nullParams(Object ...objects){
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    public static void validarParametrosNull(Object... objects) throws ObjetoNuloException {
        if (ValidacionService.nullParams(objects)) {
            throw new ObjetoNuloException(PARAMETROS_INVALIDOS);
        }
    }
}
