package com.byma.emisor.application.port.out;

import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.domain.model.Gerente;

import java.util.List;

public interface GerenteOutPort {

    Gerente crear(Gerente gerente);

    List<Gerente> listarGerentes();

    Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException;

    Gerente actualizar(Gerente gerente);

    void eliminar(Long idOrganizacionGerente);

    boolean existePorIdRegistro(Long idRegistro);
}
