package com.byma.emisor.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmisorRequestDTO {
    public static final String CAMPO_OBLIGATORIO = "Es un campo obligatorio";

    @NotBlank(message = CAMPO_OBLIGATORIO)
    private String denominacion;

    @NotBlank(message = CAMPO_OBLIGATORIO)
    private String email;

    private LocalDateTime fechaAlta;

    @NotBlank(message = CAMPO_OBLIGATORIO)
    private String cuentaEmisor;

    @NotNull(message = CAMPO_OBLIGATORIO)
    private Long idOrganizacion;

    @NotNull(message = CAMPO_OBLIGATORIO)
    private Long idEntidadLegal;
}
