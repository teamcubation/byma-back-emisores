package com.byma.emisor.application.port.in;

import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.EspecieConIdExistenteException;
import com.byma.emisor.application.exception.especie.EspecieNoEncontradaException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.domain.model.Especie;

import java.util.List;

public interface EspecieInPort {

    Especie crear(Especie especie) throws EspecieConIdExistenteException, AtributosNulosException, ObjetoEnviadoNuloException;

    List<Especie> listarEspecies() throws ObjetoEnviadoNuloException;

    Especie obtenerPorId(Long idEspecie) throws EspecieNoEncontradaException, AtributosNulosException, ObjetoEnviadoNuloException;

    Especie actualizar(Long idEspecie, Especie especie) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException, EspecieNoEncontradaException;

    void eliminar(Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException;
}
