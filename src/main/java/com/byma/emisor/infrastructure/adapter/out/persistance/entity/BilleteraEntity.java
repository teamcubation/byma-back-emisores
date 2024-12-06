package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private long id;
    private String mail;
    private String idCuenta;
    private String denominacion;
    private Boolean liquidaEnByma;
    private Boolean habilitado;
    private LocalDateTime fechaAlta;
    private String observaciones;
    private String idAcdi;

}
