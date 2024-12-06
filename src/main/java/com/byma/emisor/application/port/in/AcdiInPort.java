package com.byma.emisor.application.port.in;

import com.byma.emisor.domain.model.Acdi;

import java.util.List;

public interface AcdiInPort {
    Acdi crearAcdi(Acdi acdi);

    Acdi actualizarAcdi(Long idAcdi, Acdi acdi);

    List<Acdi> obtenerTodosLosAcdis();

    Acdi obtenerAcdiPorId(Long idAcdi);

    void eliminarAcdi(Long idAcdi);

    Acdi darDeBajaAcdi(Long idAcdi);
}
