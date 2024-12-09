package com.byma.emisor.application.port.out;

import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.EspecieNoEncontradaException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.domain.model.Especie;

import java.util.List;

public interface EspecieOutPort {

    Especie crear(Especie especie) throws ObjetoEnviadoNuloException;

    List<Especie> listarEspecies() throws ObjetoEnviadoNuloException;

    Especie obtenerPorId(Long idEspecie) throws AtributosNulosException, ObjetoEnviadoNuloException, EspecieNoEncontradaException;

    Especie actualizar(Especie especie) throws ObjetoEnviadoNuloException;

    void eliminar(Long idEspecie) throws ObjetoEnviadoNuloException, AtributosNulosException;

    boolean existeElID(Long idEspecie);
}