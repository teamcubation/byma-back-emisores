package com.byma.emisor.infrastructure.adapter.out;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.application.port.out.BilleteraOutPort;
import com.byma.emisor.domain.model.Billetera;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.BilleteraEntity;
import com.byma.emisor.infrastructure.adapter.out.persistance.mapper.BilleteraPersistanceMapper;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.BilleteraRepository;
import com.byma.emisor.infrastructure.adapter.out.persistance.validation.ValidacionPersistance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BilleteraPersistanceAdapter implements BilleteraOutPort {

    private final BilleteraRepository billeteraRepository;

    @Override
    public Billetera obtenerPorId(long id) throws BilleteraNoEncontradoException, ObjetoNuloException {
        log.info("Obteniendo billetera con id: {}", id);
        Optional<BilleteraEntity> billeteraEntity = billeteraRepository.findById(id);
        if (billeteraEntity.isEmpty()) {
            throw new BilleteraNoEncontradoException();
        }
        return BilleteraPersistanceMapper.billeteraEntityABilletera(billeteraEntity.get());
    }

    @Override
    public List<Billetera> listarBilleteras() throws ObjetoNuloException {
        log.info("Iniciando obtencion de todas las billeteras");
        List<BilleteraEntity> billeteraEntities = billeteraRepository.findAll();
        log.info("Finalizacion de la obtencion de las billeteras, total obtenidas: {}", billeteraEntities.size());

        List<Billetera> billeteras = new ArrayList<>();

        for (BilleteraEntity billeteraEntity: billeteraEntities) {
            billeteras.add(BilleteraPersistanceMapper.billeteraEntityABilletera(billeteraEntity));
        }
        return billeteras;
    }

    @Override
    public Billetera crear(Billetera billetera) throws ObjetoNuloException {
        log.info("Iniciando creacion de billetera");
        BilleteraEntity billeteraEntity = BilleteraPersistanceMapper.billeteraABilleteraEntity(billetera);
        log.info("Finalizacion de la creacion de billetera");
        return BilleteraPersistanceMapper.billeteraEntityABilletera(billeteraRepository.save(billeteraEntity));
    }

    @Override
    public void eliminarPorId(long id) throws BilleteraNoEncontradoException {
        log.info("Iniciando eliminacion de billetera con id: {}", id);
        if (billeteraRepository.findById(id).isEmpty()) {
            throw new BilleteraNoEncontradoException();
        }
        billeteraRepository.deleteById(id);
        log.info("Finalizacion de la eliminacion de billertera con id: {}", id);
    }

    @Override
    public Billetera actualizar(Billetera billetera) throws ObjetoNuloException {
        log.info("Iniciando actualizacion de billetera");
        ValidacionPersistance.validarParametrosNull(billetera);
        BilleteraEntity billeteraEntity = BilleteraPersistanceMapper.billeteraABilleteraEntity(billetera);
        log.info("Finalizacion de la actualizacion de la billetera");
        return BilleteraPersistanceMapper.billeteraEntityABilletera(billeteraRepository.save(billeteraEntity));
    }
}
