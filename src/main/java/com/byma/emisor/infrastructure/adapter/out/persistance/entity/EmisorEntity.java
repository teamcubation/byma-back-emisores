package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Emisor")
@Table(name = "emisores")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String denominacion;

    private String email;

    private LocalDateTime fechaAlta;

    private String cuentaEmisor;

    private long idOrganizacion;

    private long idEntidadLegal;


}
