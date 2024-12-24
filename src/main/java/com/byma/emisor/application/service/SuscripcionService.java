package com.byma.emisor.application.service;

import com.byma.emisor.application.exception.IdNuloException;
import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.application.port.in.SuscripcionInPort;
import com.byma.emisor.application.port.out.SuscripcionOutPort;
import com.byma.emisor.application.validation.ValidacionSuscripcion;
import com.byma.emisor.domain.model.SuscripcionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuscripcionService implements SuscripcionInPort {

    private final SuscripcionOutPort suscripcionOutPort;

    @Override
    public SuscripcionModel crear(SuscripcionModel suscripcion) {
        ValidacionSuscripcion.validarSuscripcion(suscripcion);
        suscripcion.setFechaAlta(LocalDateTime.now());
        setPrecio(suscripcion);
        return suscripcionOutPort.crear(suscripcion);
    }


    @Override
    public List<SuscripcionModel> listarSuscripciones() {
        return suscripcionOutPort.listarSuscripciones();
    }

    @Override
    public SuscripcionModel obtenerPorId(long idSuscripcion) throws IdNuloException, SuscripcionNoEncontradaException {
        ValidacionSuscripcion.validarIdNotNull(idSuscripcion);
        validarSuscripcionNoEncontrada(idSuscripcion);
        return suscripcionOutPort.obtenerPorId(idSuscripcion).get();
    }

    @Override
    public SuscripcionModel actualizar(SuscripcionModel suscripcion, long idSuscripcion) throws IdNuloException, SuscripcionNoEncontradaException {
        ValidacionSuscripcion.validarSuscripcion(suscripcion);
        ValidacionSuscripcion.validarIdNotNull(idSuscripcion);
        SuscripcionModel suscripcionAActualizar = this.obtenerPorId(idSuscripcion);
        suscripcion.setFechaAlta(suscripcionAActualizar.getFechaAlta());
        suscripcion.setPrecio(suscripcionAActualizar.getPrecio());
        setPrecio(suscripcion);
        suscripcion.setIdSuscripcion(idSuscripcion);
        return suscripcionOutPort.actualizar(suscripcion);
    }

    private static void setPrecio(SuscripcionModel suscripcion) {
        double precio = suscripcion.getMonto() / suscripcion.getCantCuotapartes();
        BigDecimal precioRedondeado = BigDecimal.valueOf(precio)
                .setScale(2, RoundingMode.HALF_UP);
        suscripcion.setPrecio(precioRedondeado.doubleValue());
    }

    @Override
    public void eliminar(long idSuscripcion) throws SuscripcionNoEncontradaException {
        validarSuscripcionNoEncontrada(idSuscripcion);
        suscripcionOutPort.eliminarPorId(idSuscripcion);
    }
    private void validarSuscripcionNoEncontrada(long id) throws SuscripcionNoEncontradaException {
        if (suscripcionOutPort.obtenerPorId(id).isEmpty())
            throw new SuscripcionNoEncontradaException("La suscripcion no ha sido encontrada.");
    }
}
