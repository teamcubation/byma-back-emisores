package com.byma.emisor.application.service;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.port.in.EmisorInPort;
import com.byma.emisor.application.port.out.EmisorOutPort;
import com.byma.emisor.application.validation.ValidacionService;
import com.byma.emisor.domain.model.Emisor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.byma.emisor.application.validation.ValidacionService.EMISOR_DUPLICADO;
import static com.byma.emisor.application.validation.ValidacionService.EMISOR_NO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class EmisorService implements EmisorInPort {

    private final EmisorOutPort emisorOutPort;

    @Override
    public Emisor crear(Emisor emisor) throws ObjetoNuloException, EmisorDuplicadoException, EmisorNoEncontradoException {
        ValidacionService.validarParametrosNull(emisor);
        validarEmisorDuplicado(emisor.getEmail());
        emisor.setFechaAlta(LocalDateTime.now());
        return emisorOutPort.crear(emisor);
    }

    @Override
    public List<Emisor> listarEmisores() {
        return emisorOutPort.listarEmisores();
    }

    @Override
    public Emisor obtenerPorId(long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException {
        ValidacionService.validarParametrosNull(idEmisor);
        validarEmisorNoEncontrado(idEmisor);
        return emisorOutPort.obtenerPorId(idEmisor).get();
    }

    @Override
    public Emisor actualizar(Emisor emisor, Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException, EmisorDuplicadoException {
        ValidacionService.validarParametrosNull(idEmisor);
        validarEmisorNoEncontrado(idEmisor);
        validarEmisorDuplicado(emisor.getEmail());
        Emisor emisorAActualizar = this.obtenerPorId(idEmisor);
        emisor.setFechaAlta(emisorAActualizar.getFechaAlta());
        emisor.setId(idEmisor);
        return emisorOutPort.actualizar(emisor);
    }

    @Override
    public void eliminar(long idEmisor) throws EmisorNoEncontradoException {
        validarEmisorNoEncontrado(idEmisor);
        emisorOutPort.eliminarPorId(idEmisor);
    }

    private void validarEmisorDuplicado(String email) throws EmisorDuplicadoException {
        if (emisorOutPort.existeEmisorPorEmailIgnorarMayusculas(email))
            throw new EmisorDuplicadoException(EMISOR_DUPLICADO);
    }

    private void validarEmisorNoEncontrado(Long id) throws EmisorNoEncontradoException {
        if (emisorOutPort.obtenerPorId(id).isEmpty())
            throw new EmisorNoEncontradoException(EMISOR_NO_ENCONTRADO);
    }

}
