package com.byma.emisor.infrastructure.adapter.out.persistance.entity;

import com.byma.emisor.domain.model.EstadoAcdi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "acdi")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcdiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcdi;
    private Integer codigoDeAcdi;
    private String denominacion;
    private Boolean liquidaEnByma = true;
    private Boolean habilitado;
    private Boolean billeteras;
    private String observaciones;
    private LocalDateTime fechaAlta;
    private String mail;
    private EstadoAcdi estado = EstadoAcdi.CREADA;

}
