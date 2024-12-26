package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Billetera")
@Table(name = "billeteras")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BilleteraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "ID_CUENTA")
    private long idCuenta;

    @Column(name = "DENOMINACION")
    private String denominacion;

    @Column(name = "LIQUIDA_EN_BYMA")
    private boolean liquidaEnByma;

    @Column(name = "HABILITADO")
    private boolean habilitado;

    @Column(name = "FECHA_ALTA")
    private LocalDateTime fechaAlta;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "ID_ACDI")
    private long idAcdi;

}
