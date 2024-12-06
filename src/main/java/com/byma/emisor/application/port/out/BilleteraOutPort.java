package com.byma.emisor.application.port.out;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.domain.model.Billetera;

import java.util.List;

public interface BilleteraOutPort {
    Billetera obtenerPorId(long id) throws BilleteraNoEncontradoException, ObjetoNuloException;

    List<Billetera> listarBilleteras() throws ObjetoNuloException;

    Billetera crear(Billetera billetera) throws ObjetoNuloException;

    void eliminarPorId(long id) throws BilleteraNoEncontradoException;

    Billetera actualizar(Billetera billetera) throws ObjetoNuloException;
}
