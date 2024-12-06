package com.byma.emisor.util.validation;


import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.domain.model.Especie;

public class Validador {

    public static final String CAMPO_NO_NULO = "Los campos no pueden ser nulos";

    public static void validarIdNull(Long idEspecie) throws AtributosNulosException {
        if (idEspecie == null) {
            throw new AtributosNulosException("El id no puede ser null");
        }
    }

    public static void validarEspecie(Especie especie) throws ObjetoEnviadoNuloException, AtributosNulosException {
        validarObjetoNotNull(especie);
        validarAtributosNulos(especie);
    }

    public static void validarObjetoNotNull(Object object) throws ObjetoEnviadoNuloException {
        if (object == null) {
            throw new ObjetoEnviadoNuloException("El objeto enviado no puede ser nulo.");
        }
    }

    public static void validarAtributosNulos(Especie especie) throws AtributosNulosException {
        if (especie.getCodigoCVSA() == null || especie.getDenominacion() == null
                || especie.getPlazoDeLiquidacion() == null) {
            throw new AtributosNulosException("Error. atributos nulos");
        }
    }

    public static boolean tieneCamposNulos(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    public static void validarNoNulo(Object... objects) {
        if (tieneCamposNulos(objects)) {
            throw new IllegalArgumentException(CAMPO_NO_NULO);
        }
    }

    public static boolean nullParams(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    public static void validadorParametrosNull(Object... objects) {
        if (Validador.nullParams(objects)) {
            throw new IllegalArgumentException();
        }
    }
}
