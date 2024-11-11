package com.byma.emisor.infrastructure.adapter.out;

import com.byma.emisor.application.port.out.EmisorOutPort;
import com.byma.emisor.domain.model.Emisor;
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
    public Optional<Emisor> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Emisor> getAll() {
        return List.of();
    }

    @Override
    public Emisor save(Emisor emisor) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return false;
    }
}
