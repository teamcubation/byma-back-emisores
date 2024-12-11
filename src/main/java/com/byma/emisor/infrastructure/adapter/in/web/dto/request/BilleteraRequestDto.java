package com.byma.emisor.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BilleteraRequestDto {

    @NotBlank(message = "El mail es obligatorio")
    private String mail;

    @NotNull(message = "El id de la cuenta es obligatorio")
    private Long idCuenta;

    @NotBlank(message = "La denominacion es obligatoria")
    private String denominacion;

    @NotNull(message = "El campo liquida en Byma es obligatorio")
    private Boolean liquidaEnByma;

    @NotNull(message = "El campo habilitado es obligatorio")
    private Boolean habilitado;

    private String observaciones;

    @NotNull(message = "El id acdi es obligatorio")
    private Long idAcdi;

}
