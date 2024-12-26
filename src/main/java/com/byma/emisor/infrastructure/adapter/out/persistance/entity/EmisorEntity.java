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
    @Column(name = "ID")
    private long id;

    @Column(name = "DENOMINACION")
    private String denominacion;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FECHA_ALTA")
    private LocalDateTime fechaAlta;

    @Column(name = "CUENTA_EMISOR")
    private String cuentaEmisor;

    @Column(name = "ID_ORGANIZACION")
    private long idOrganizacion;

    @Column(name = "ID_ENTIDAD_LEGAL")
    private long idEntidadLegal;

}
