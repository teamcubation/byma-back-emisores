package com.byma.emisor.application.port.in;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.domain.model.Billetera;

import java.util.List;

public interface BilleteraInPort {
    Billetera crear(Billetera billeteraRequestDTO) throws ObjetoNuloException;

    List<Billetera> listarBilleteras() throws ObjetoNuloException;

    Billetera obtenerPorId(long idBillertera) throws BilleteraNoEncontradoException, ObjetoNuloException;

    Billetera actualizar(Billetera billeteraRequestDTO, Long idBilletera) throws BilleteraNoEncontradoException, ObjetoNuloException;

    void eliminar(long idBilletera) throws BilleteraNoEncontradoException, ObjetoNuloException;
}
