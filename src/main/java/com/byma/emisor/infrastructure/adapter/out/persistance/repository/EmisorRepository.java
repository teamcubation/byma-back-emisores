package com.byma.emisor.infrastructure.adapter.out.persistance.repository;

import com.byma.emisor.infrastructure.adapter.out.persistance.entity.EmisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmisorRepository extends JpaRepository<EmisorEntity, Long> {
}
