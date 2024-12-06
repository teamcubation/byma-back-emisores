package com.byma.emisor.infrastructure.adapter.out;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.application.port.out.SuscripcionOutPort;
import com.byma.emisor.domain.model.SuscripcionModel;
import com.byma.emisor.infrastructure.adapter.out.persistance.mapper.SuscripcionPersistenceMapper;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.SuscripcionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SuscripcionPersistenceAdapter implements SuscripcionOutPort {

    private final SuscripcionRepository suscripcionRepository;

    @Override
    public Optional<SuscripcionModel> obtenerPorId(long id) {
        log.info("Obteniendo suscripcion por id: {}", id);
        return suscripcionRepository.findById(id).map(SuscripcionPersistenceMapper::SuscripcionEntityToModel);
    }

    @Override
    public List<SuscripcionModel> listarSuscripciones() {
        log.info("Obteniendo todas las suscripciones");
        return SuscripcionPersistenceMapper.suscripcionEntitiesToSuscripcionModels(suscripcionRepository.findAll());
    }

    @Override
    public SuscripcionModel crear(SuscripcionModel suscripcion) {
        log.info("Creando suscripcion: {}", suscripcion);
        validarSuscripcionNula(suscripcion);
        return SuscripcionPersistenceMapper.SuscripcionEntityToModel(suscripcionRepository.save(SuscripcionPersistenceMapper.SuscripcionModelToEntity(suscripcion)));
    }

    @Override
    public void eliminarPorId(long id) throws SuscripcionNoEncontradaException {
        log.info("Eliminando suscripcion por id: {}", id);
        validarSuscripcionEncontrada(id);
        suscripcionRepository.deleteById(id);
    }



    @Override
    public SuscripcionModel actualizar(SuscripcionModel suscripcion) {
        log.info("Actualizando suscripcion: {}", suscripcion);
        validarSuscripcionNula(suscripcion);
        return SuscripcionPersistenceMapper.SuscripcionEntityToModel(suscripcionRepository.save(SuscripcionPersistenceMapper.SuscripcionModelToEntity(suscripcion)));
    }
    private void validarSuscripcionEncontrada(long id) throws SuscripcionNoEncontradaException {
        if (suscripcionRepository.findById(id).isEmpty()) {
            throw new SuscripcionNoEncontradaException("La suscripcion no ha sido encontrada.");
        }
    }

    private static void validarSuscripcionNula(SuscripcionModel suscripcion) {
        if (suscripcion == null) {
            throw new ObjetoNuloException("La suscripcion no puede ser nula");
        }
    }
}
