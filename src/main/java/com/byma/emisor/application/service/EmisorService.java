package com.byma.emisor.application.service;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.exception.ParametroNuloException;
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
    public Emisor crear(Emisor emisor) throws ParametroNuloException, EmisorDuplicadoException, EmisorNoEncontradoException {
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
    public Emisor obtenerPorId(long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException {
        ValidacionService.validarParametrosNull(idEmisor);
        validarEmisorNoEncontrado(idEmisor);
        return emisorOutPort.obtenerPorId(idEmisor).get();
    }

    @Override
    public Emisor actualizar(Emisor emisor, Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException, EmisorDuplicadoException {
        ValidacionService.validarParametrosNull(idEmisor);
        validarEmisorNoEncontrado(idEmisor);


        Emisor emisorAActualizar = emisorOutPort.obtenerPorId(idEmisor).get();

        if (emisor.getDenominacion() != null) {
            emisorAActualizar.setDenominacion(emisor.getDenominacion());
        }
        if (emisor.getEmail() != null) {
            validarEmisorDuplicado(emisor.getEmail());
            emisorAActualizar.setEmail(emisor.getEmail());
        }
        if (emisor.getCuentaEmisor() != null) {
            emisorAActualizar.setCuentaEmisor(emisor.getCuentaEmisor());
        }
        if (emisor.getIdOrganizacion() != null) {
            emisorAActualizar.setIdOrganizacion(emisor.getIdOrganizacion());
        }
        if (emisor.getIdEntidadLegal() != null) {
            emisorAActualizar.setIdEntidadLegal(emisor.getIdEntidadLegal());
        }
        return emisorOutPort.actualizar(emisorAActualizar);
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
