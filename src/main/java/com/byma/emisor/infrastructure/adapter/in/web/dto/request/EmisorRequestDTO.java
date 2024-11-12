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
    public static final String CAMPO_VACIO = "No puede ser un campo vacio";

    @NotBlank(message = CAMPO_VACIO)
    private String denominacion;

    @NotBlank(message = CAMPO_VACIO)
    private String email;

    private LocalDateTime fechaAlta;

    @NotBlank(message = CAMPO_VACIO)
    private String cuentaEmisor;

    @NotNull(message = CAMPO_OBLIGATORIO)
    private Long idOrganizacion;

    @NotNull(message = CAMPO_OBLIGATORIO)
    private Long idEntidadLegal;
}
