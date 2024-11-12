package com.byma.emisor.infrastructure.adapter.out;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.port.out.EmisorOutPort;
import com.byma.emisor.domain.model.Emisor;
import com.byma.emisor.infrastructure.adapter.out.persistance.mapper.EmisorPersistenceMapper;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.EmisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmisorPersistenceAdapter implements EmisorOutPort {

    private final EmisorRepository emisorRepository;

    @Override
    public Optional<Emisor> obtenerPorId(long id) {
        return emisorRepository.findById(id).map(EmisorPersistenceMapper::emisorEntityToEmisorModel);
    }

    @Override
    public List<Emisor> listarEmisores() {
        return EmisorPersistenceMapper.emisorEntitiesToEmisorModels(emisorRepository.findAll());
    }

    @Override
    public Emisor crear(Emisor emisor) throws EmisorNoEncontradoException, EmisorDuplicadoException {
        if (emisor == null) {
            throw new EmisorNoEncontradoException();
        }
        if (emisorRepository.existsByEmailIgnoreCase(emisor.getEmail())) {
            throw new EmisorDuplicadoException();
        }
        return EmisorPersistenceMapper.emisorEntityToEmisorModel(emisorRepository.save(EmisorPersistenceMapper.emisorModelToEmisorEntity(emisor)));
    }

    @Override
    public void eliminarPorId(long id) throws EmisorNoEncontradoException {
        if (emisorRepository.findById(id).isEmpty()) {
            throw new EmisorNoEncontradoException();
        }
        emisorRepository.deleteById(id);
    }

    @Override
    public boolean existeEmisorPorEmailIgnorarMayusculas(String email) {
        return emisorRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Emisor actualizar(Emisor emisor) throws EmisorNoEncontradoException, EmisorDuplicadoException {
        if (emisor == null) {
            throw new EmisorNoEncontradoException();
        }
        if (emisorRepository.existsByEmailIgnoreCase(emisor.getEmail())) {
            throw new EmisorDuplicadoException();
        }
        return EmisorPersistenceMapper.emisorEntityToEmisorModel(emisorRepository.save(EmisorPersistenceMapper.emisorModelToEmisorEntity(emisor)));
    }
}
