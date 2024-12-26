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
    @Column(name = "ID_GERENTE")
    private Long idGerente;

    @Column(name = "DENOMINACION", nullable = false)
    private String denominacion;

    @Column(name = "LIQUIDA_EN_BYMA", nullable = false)
    private Boolean liquidaEnByma = true;

    @Column(name = "HABILITADO", nullable = false)
    private Boolean habilitado;

    @Column(name = "OBSERVACIONES", nullable = false)
    private String observaciones;

    @Column(name = "MAIL_GERENTE")
    private String mailGerente;

    @CreationTimestamp
    @Column(name = "FECHA_DE_ALTA", nullable = false)
    private LocalDateTime fechaDeAlta;

}
