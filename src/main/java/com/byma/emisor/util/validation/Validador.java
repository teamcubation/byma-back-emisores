package com.byma.emisor.util.validation;


import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.domain.model.Especie;

public class Validador {

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
}
