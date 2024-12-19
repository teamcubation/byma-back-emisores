package com.byma.emisor.infrastructure.adapter.out.persistance.repository;

import com.byma.emisor.infrastructure.adapter.out.persistance.entity.AcdiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcdiRepository extends JpaRepository<AcdiEntity, Long> {

}
