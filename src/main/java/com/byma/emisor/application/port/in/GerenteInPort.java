package com.byma.emisor.application.port.in;


import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.domain.model.Gerente;

import java.util.List;

public interface GerenteInPort {

    Gerente crear(Gerente gerenteRequestDTO);

    List<Gerente> listarGerentes();

    Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException;

    Gerente actualizar(Long idRegistro, Gerente gerenteRequestDTO) throws GerenteNoEncontradoException;

    Gerente toggleHabilitar(Long idRegistro) throws GerenteNoEncontradoException;
}
