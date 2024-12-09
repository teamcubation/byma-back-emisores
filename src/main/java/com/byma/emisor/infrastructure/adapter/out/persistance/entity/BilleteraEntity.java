package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
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
    private Long id;
    private String mail;
    private String idCuenta;
    private String denominacion;
    private boolean liquidaEnByma;
    private boolean habilitado;
    private LocalDateTime fechaAlta;
    private String observaciones;
    private String idAcdi;

}
