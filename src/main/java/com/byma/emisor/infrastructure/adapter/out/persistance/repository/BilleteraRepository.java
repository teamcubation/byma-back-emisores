package com.byma.emisor.infrastructure.adapter.out.persistance.repository;

import com.byma.emisor.infrastructure.adapter.out.persistance.entity.BilleteraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilleteraRepository extends JpaRepository<BilleteraEntity, Long> {
}
