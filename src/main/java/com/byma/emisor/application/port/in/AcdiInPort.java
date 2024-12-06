package com.byma.emisor.application.port.in;

import com.byma.emisor.application.exception.acdi.AcdiNoEncontradoException;
import com.byma.emisor.domain.model.Acdi;

import java.util.List;

public interface AcdiInPort {
    Acdi crearAcdi(Acdi acdi);

    Acdi actualizarAcdi(Long idAcdi, Acdi acdi) throws AcdiNoEncontradoException;

    List<Acdi> obtenerTodosLosAcdis();

    Acdi obtenerAcdiPorId(Long idAcdi) throws AcdiNoEncontradoException;

    void eliminarAcdi(Long idAcdi) throws AcdiNoEncontradoException;

    Acdi darDeBajaAcdi(Long idAcdi) throws AcdiNoEncontradoException;
}
