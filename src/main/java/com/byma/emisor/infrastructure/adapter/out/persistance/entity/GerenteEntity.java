package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "gerentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GerenteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGerente;
    @Column(nullable = false)
    private String denominacion;
    @Column(nullable = false)
    private boolean liquidaEnByma = true;
    @Column(nullable = false)
    private boolean habilitado;
    @Column(nullable = false)
    private String observaciones;
    private String mailGerente;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime fechaDeAlta;
}
