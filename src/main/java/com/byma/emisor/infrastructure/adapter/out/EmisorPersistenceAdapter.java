package com.byma.emisor.infrastructure.adapter.out;

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
    public Emisor crear(Emisor emisor) {
        //agregar validacion de nulo y si no se encuentra lanzar excepcion cutomizada
        return EmisorPersistenceMapper.emisorEntityToEmisorModel(emisorRepository.save(EmisorPersistenceMapper.emisorModelToEmisorEntity(emisor)));
    }

    @Override
    public void eliminarPorId(long id) {
        //buscarlo por id y si no se encuentra lanzar excepcion customizada
        emisorRepository.deleteById(id);
    }

    @Override
    public boolean existeEmisorPorEmailIgnorarMayusculas(String email) {
        return emisorRepository.existsByEmailIgnoreCase(email);
    }
}
