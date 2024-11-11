package com.byma.emisor.application.service;

import com.byma.emisor.application.port.in.EmisorInPort;
import com.byma.emisor.domain.model.Emisor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmisorService implements EmisorInPort {

    @Override
    public Emisor crear(Emisor emisorRequestDTO) {
        return null;
    }

    @Override
    public List<Emisor> listarEmisores() {
        return List.of();
    }

    @Override
    public Emisor obtenerPorId(Long idEmisor) {
        return null;
    }

    @Override
    public Emisor actualizar(Emisor emisorRequestDTO, Long idEmisor) {
        return null;
    }

    @Override
    public void eliminar(Long idEmisor) {

    }
}
