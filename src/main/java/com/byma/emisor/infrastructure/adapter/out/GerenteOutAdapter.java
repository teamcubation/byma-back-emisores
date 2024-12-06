package com.byma.emisor.infrastructure.adapter.out;


import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.application.port.out.GerenteOutPort;
import com.byma.emisor.domain.model.Gerente;
import com.byma.emisor.infrastructure.adapter.out.persistance.mapper.GerenteMapper;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.GerenteRepository;
import com.byma.emisor.util.validation.Validador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GerenteOutAdapter implements GerenteOutPort {

    private final GerenteRepository gerenteRepository;

    @Override
    public Gerente crear(Gerente gerente) {
        Validador.validadorParametrosNull(gerente);
        return GerenteMapper.aGerente(gerenteRepository.save(GerenteMapper.aGerenteEntity(gerente)));
    }

    @Override
    public List<Gerente> listarGerentes() {
        return GerenteMapper.aGerentes(gerenteRepository.findAll());
    }

    @Override
    public Gerente obtenerPorIdOrganizacionGerente(Long idRegistro) throws GerenteNoEncontradoException {
        Validador.validadorParametrosNull(idRegistro);
        return GerenteMapper.aGerente(gerenteRepository.findById(idRegistro).orElseThrow(() -> new GerenteNoEncontradoException("Gerente no encontrado")));
    }

    @Override
    public Gerente actualizar(Gerente gerente) {
        Validador.validadorParametrosNull(gerente);
        return GerenteMapper.aGerente(gerenteRepository.save(GerenteMapper.aGerenteEntity(gerente)));
    }

    @Override
    public void eliminar(Long idOrganizacionGerente) {
        Validador.validadorParametrosNull(idOrganizacionGerente);
        gerenteRepository.deleteById(idOrganizacionGerente);
    }

    @Override
    public boolean existePorIdRegistro(Long idRegistro) {
        return this.gerenteRepository.existsByIdGerente(idRegistro);
    }


}
