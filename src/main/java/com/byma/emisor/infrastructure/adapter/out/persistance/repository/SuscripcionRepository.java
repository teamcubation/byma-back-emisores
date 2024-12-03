package com.byma.emisor.infrastructure.adapter.out.persistance.repository;

import com.byma.emisor.infrastructure.adapter.out.persistance.entity.SuscripcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscripcionRepository extends JpaRepository<SuscripcionEntity, Long> {
}
