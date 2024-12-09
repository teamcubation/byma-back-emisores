package com.byma.emisor.application.service;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.application.port.in.BilleteraInPort;
import com.byma.emisor.application.port.out.BilleteraOutPort;
import com.byma.emisor.application.validation.ValidacionService;
import com.byma.emisor.domain.model.Billetera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BilleteraService implements BilleteraInPort {

    private final BilleteraOutPort billeteraOutPort;

    @Override
    public Billetera crear(Billetera billetera) throws ObjetoNuloException {
        ValidacionService.validarParametrosNull(billetera);
        return billeteraOutPort.crear(billetera);
    }

    @Override
    public List<Billetera> listarBilleteras() throws ObjetoNuloException {
        return billeteraOutPort.listarBilleteras();
    }

    @Override
    public Billetera obtenerPorId(long idBilletera) throws BilleteraNoEncontradoException, ObjetoNuloException {
        ValidacionService.validarParametrosNull(idBilletera);
        billeteraOutPort.obtenerPorId(idBilletera);

        return billeteraOutPort.obtenerPorId(idBilletera);
    }

    @Override
    public Billetera actualizar(Billetera billetera, Long idBilletera) throws BilleteraNoEncontradoException, ObjetoNuloException {
        ValidacionService.validarParametrosNull(idBilletera, billetera);
        billetera.setId(idBilletera);

        return billeteraOutPort.actualizar(billetera);
    }

    @Override
    public void eliminar(long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException {
        ValidacionService.validarParametrosNull(idBilletera);
        billeteraOutPort.eliminarPorId(idBilletera);
    }
}
