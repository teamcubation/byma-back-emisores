package com.byma.emisor.application.port.out;


import com.byma.emisor.domain.model.Acdi;

import java.util.List;
import java.util.Optional;

public interface AcdiOutPort {
    Acdi guardarAcdi(Acdi acdi);

    Optional<Acdi> obtenerAcdiPorId(Long idAcdi);

    List<Acdi> obtenerTodosAcdis();

    void eliminarAcdi(Long idAcdi);
}
