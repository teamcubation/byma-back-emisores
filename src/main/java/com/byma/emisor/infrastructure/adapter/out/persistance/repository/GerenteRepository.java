package com.byma.emisor.infrastructure.adapter.out.persistance.repository;


import com.byma.emisor.infrastructure.adapter.out.persistance.entity.GerenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<GerenteEntity, Long> {
    boolean existsByIdGerente(Long idOrganizacionGerente);
}
