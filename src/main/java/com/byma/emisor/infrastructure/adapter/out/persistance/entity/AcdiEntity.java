package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import com.byma.emisor.domain.model.EstadoAcdi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "acdis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcdiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACDI") // Nombre de la columna en mayúsculas con _
    private Long idAcdi;

    @Column(name = "CODIGO_DE_ACDI")
    private Integer codigoDeAcdi;

    @Column(name = "DENOMINACION")
    private String denominacion;

    @Column(name = "LIQUIDA_EN_BYMA")
    private Boolean liquidaEnByma = true;

    @Column(name = "HABILITADO")
    private Boolean habilitado;

    @Column(name = "BILLETERAS")
    private Boolean billeteras;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "FECHA_ALTA")
    private LocalDateTime fechaAlta;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "ESTADO")
    private EstadoAcdi estado = EstadoAcdi.CREADA;

}
