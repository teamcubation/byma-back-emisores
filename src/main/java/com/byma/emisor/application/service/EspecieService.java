package com.byma.emisor.application.service;


import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.EspecieConIdExistenteException;
import com.byma.emisor.application.exception.especie.EspecieNoEncontradaException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.application.port.in.EspecieInPort;
import com.byma.emisor.application.port.out.EspecieOutPort;
import com.byma.emisor.domain.model.Especie;
import com.byma.emisor.util.validation.Validador;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class EspecieService implements EspecieInPort {

    private final EspecieOutPort especieOutPort;

    public EspecieService(EspecieOutPort especieOutPort) {
        this.especieOutPort = especieOutPort;
    }

    @Override
    public Especie crear(Especie especie) throws EspecieConIdExistenteException, ObjetoEnviadoNuloException, AtributosNulosException {
        Validador.validarEspecie(especie);
        validarEspecieNoExista(especie.getIdEspecie());
        especie.setFechaAlta(LocalDate.now());
        return especieOutPort.crear(especie);
    }

    @Override
    public List<Especie> listarEspecies() throws ObjetoEnviadoNuloException {
        return especieOutPort.listarEspecies();
    }

    public Especie obtenerPorId(Long idEspecie) throws EspecieNoEncontradaException, AtributosNulosException, ObjetoEnviadoNuloException {
        Validador.validarIdNull(idEspecie);
        Especie especieEncontrada = especieOutPort.obtenerPorId(idEspecie);
        if (especieEncontrada == null) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        return especieEncontrada;
    }

    @Override
    public Especie actualizar(Long idEspecie, Especie especie) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException, EspecieNoEncontradaException {
        Validador.validarIdNull(idEspecie);
        if (!validarIdExistente(idEspecie)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        Validador.validarEspecie(especie);
        if (!idEspecie.equals(especie.getIdEspecie())) {
            validarEspecieNoExista(especie.getIdEspecie());
        }
        especie.setIdEspecie(idEspecie);
        return especieOutPort.actualizar(especie);
    }

    @Override
    public void eliminar(Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException {
        Validador.validarIdNull(idEspecie);
        if (!validarIdExistente(idEspecie)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        especieOutPort.eliminar(idEspecie);
    }

    private boolean validarIdExistente(long idEspecie) {
        return especieOutPort.existeElID(idEspecie);
    }

    private void validarEspecieNoExista(long idEspecie) throws EspecieConIdExistenteException {
        if (validarIdExistente(idEspecie)) {
            throw new EspecieConIdExistenteException("Ya existe una especie con ese ID.");
        }
    }

}
