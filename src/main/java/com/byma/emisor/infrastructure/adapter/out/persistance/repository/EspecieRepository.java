package com.byma.emisor.infrastructure.adapter.out.persistance.repository;

import com.byma.emisor.infrastructure.adapter.out.persistance.entity.EspecieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<EspecieEntity, Long> {
}
