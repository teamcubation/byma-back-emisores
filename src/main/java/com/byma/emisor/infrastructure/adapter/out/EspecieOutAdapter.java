package com.byma.emisor.infrastructure.adapter.out;


import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.EspecieNoEncontradaException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.application.port.out.EspecieOutPort;
import com.byma.emisor.domain.model.Especie;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.EspecieEntity;
import com.byma.emisor.infrastructure.adapter.out.persistance.mapper.EspecieMapper;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.EspecieRepository;
import com.byma.emisor.util.validation.Validador;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EspecieOutAdapter implements EspecieOutPort {

    @Autowired
    private EspecieRepository especieRepository;

    @Override
    public Especie crear(Especie especie) throws ObjetoEnviadoNuloException {
        Validador.validarObjetoNotNull(especie);
        return EspecieMapper.especieEntityAEspecie(especieRepository.save(EspecieMapper.especieAEspecieEntity(especie)));
    }

    @Override
    public List<Especie> listarEspecies() throws ObjetoEnviadoNuloException {
        return EspecieMapper.especieEntitiesAEspecies(especieRepository.findAll());
    }

    @Override
    public Especie obtenerPorId(Long idEspecie) throws AtributosNulosException, ObjetoEnviadoNuloException, EspecieNoEncontradaException {
        Validador.validarIdNull(idEspecie);

        EspecieEntity especieEntity = especieRepository.findById(idEspecie).orElse(null);
        if (especieEntity == null) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }

        return EspecieMapper.especieEntityAEspecie(especieEntity);
    }

    @Override
    public Especie actualizar(Especie especie) throws ObjetoEnviadoNuloException {
        Validador.validarObjetoNotNull(especie);
        return EspecieMapper.especieEntityAEspecie(especieRepository.save(EspecieMapper.especieAEspecieEntity(especie)));
    }

    @Override
    public void eliminar(Long idEspecie) throws AtributosNulosException {
        Validador.validarIdNull(idEspecie);
        especieRepository.deleteById(idEspecie);
    }

    @Override
    public boolean existeElID(Long idEspecie) {
        return especieRepository.existsById(idEspecie);
    }
}
