package com.byma.emisor.application.service;


import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.application.port.in.GerenteInPort;
import com.byma.emisor.application.port.out.GerenteOutPort;
import com.byma.emisor.domain.model.Gerente;
import com.byma.emisor.util.validation.Validador;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class GerenteService implements GerenteInPort {

    private final GerenteOutPort gerenteOutPort;

    @Override
    public Gerente crear(Gerente gerenteRequestDTO) {
        log.info("Crear gerente: {}", gerenteRequestDTO);
        Validador.validadorParametrosNull(gerenteRequestDTO);
        return gerenteOutPort.crear(gerenteRequestDTO);
    }

    @Override
    public List<Gerente> listarGerentes() {
        log.info("Obtener todos los gerentes");
        return gerenteOutPort.listarGerentes();
    }

    @Override
    public Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException {
        log.info("Obtener gerente por el id de organizacion: {}", idRegistro);
        Validador.validadorParametrosNull(idRegistro);
        return gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro);
    }

    @Override
    public Gerente actualizar(Long idRegistro, Gerente gerenteRequestDTO) throws GerenteNoEncontradoException {
        log.info("Actualizar gerente con id de organizacion: {}, datos a actualizar: {}", idRegistro, gerenteRequestDTO);
        Validador.validadorParametrosNull(idRegistro, gerenteRequestDTO);
        Gerente gerenteDb = this.gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro);
        gerenteDb.setEmailGerente(gerenteRequestDTO.getEmailGerente());
        gerenteDb.setLiquidaEnByma(gerenteRequestDTO.liquidaEnByma());

        return gerenteOutPort.actualizar(gerenteDb);
    }

    @Override
    public Gerente toggleHabilitar(Long idRegistro) throws GerenteNoEncontradoException {
        log.info("Eliminar gerente");
        Validador.validadorParametrosNull(idRegistro);
        Gerente gerenteDb = this.gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro);
        gerenteDb.setHabilitado(!gerenteDb.estaHabilitado());
        return gerenteOutPort.actualizar(gerenteDb);
    }
}
