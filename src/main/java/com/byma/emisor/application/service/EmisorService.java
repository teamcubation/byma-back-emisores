package com.byma.emisor.application.service;

import com.byma.emisor.application.port.in.EmisorInPort;
import com.byma.emisor.application.port.out.EmisorOutPort;
import com.byma.emisor.domain.model.Emisor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmisorService implements EmisorInPort {

    private final EmisorOutPort emisorOutPort;

    @Override
    public Emisor crear(Emisor emisor) {
        // TODO: validar que los parametros no sean null
        //  y que no tenga email duplicado (custom exception)
        return emisorOutPort.crear(emisor);
    }

    @Override
    public List<Emisor> listarEmisores() {
        return emisorOutPort.listarEmisores();
    }

    @Override
    public Emisor obtenerPorId(long idEmisor) {
        // TODO: validar que exista el emisor con ese id (custom exception)
        return emisorOutPort.obtenerPorId(idEmisor).orElseThrow(RuntimeException::new);
    }

    @Override
    public Emisor actualizar(Emisor emisor, Long idEmisor) {
        // TODO: validar que los parametros no sean null,
        //  que el email no sea duplicado (custom exception)
        //  y que exista el emisor con ese id (custom exception)
        emisor.setId(idEmisor);
        return emisorOutPort.actualizar(emisor);
    }

    @Override
    public void eliminar(long idEmisor) {
        //TODO: validar que el emisor a eliminar exista
        emisorOutPort.eliminarPorId(idEmisor);
    }
}
