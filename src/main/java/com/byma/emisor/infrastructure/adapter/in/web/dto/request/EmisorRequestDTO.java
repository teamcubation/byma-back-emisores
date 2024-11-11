package com.byma.emisor.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmisorRequestDTO {
    @NotNull
    @NotBlank
    private String denominacion;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    private LocalDate fechaAlta;
    @NotNull
    @NotBlank
    private String cuentaEmisor;
    @NotNull
    private long idOrganizacion;
    @NotNull
    private long idEntidadLegal;
}
