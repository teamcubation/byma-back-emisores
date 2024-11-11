package com.byma.emisor.application.port.out;

import com.byma.emisor.domain.model.Emisor;

import java.util.List;
import java.util.Optional;

public interface EmisorOutPort {

    Optional<Emisor> findById(long id);

    List<Emisor> getAll();

    Emisor save(Emisor emisor);

    void deleteById(long id);

    boolean existByEmailIgnoreCase(String email);

}
