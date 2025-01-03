package com.byma.emisor.infrastructure.adapter.in.web.mapper;


import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.domain.model.Especie;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EspecieRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.EspecieResponseDTO;
import com.byma.emisor.util.validation.Validador;

import java.util.ArrayList;
import java.util.List;

public class EspecieControllerMapper {

    public static Especie especieRequestDtoAEspecie(EspecieRequestDTO especieRequestDTO) throws ObjetoEnviadoNuloException {
        Validador.validarObjetoNotNull(especieRequestDTO);
        return Especie.builder()
                .codigoCVSA(especieRequestDTO.getCodigoCVSA())
                .denominacion(especieRequestDTO.getDenominacion())
                .laminaMinima(especieRequestDTO.getLaminaMinima())
                .precio(especieRequestDTO.getPrecio())
                .cafci(especieRequestDTO.getCafci())
                .cuentaDeEmision(especieRequestDTO.getCuentaDeEmision())
                .estado(especieRequestDTO.getEstado())
                .idEmisor(especieRequestDTO.getIdEmisor())
                .idGerente(especieRequestDTO.getIdGerente())
                .vigencia(especieRequestDTO.getVigencia())
                .plazoDeLiquidacion(especieRequestDTO.getPlazoDeLiquidacion())
                .codigoCNV(especieRequestDTO.getCodigoCNV())
                .isin(especieRequestDTO.getIsin())
                .familiaDeFondos(especieRequestDTO.getFamiliaDeFondos())
                .observaciones(especieRequestDTO.getObservaciones())
                .idMoneda(especieRequestDTO.getIdMoneda())
                .fechaAlta(especieRequestDTO.getFechaAlta())
                .build();
    }

    public static EspecieResponseDTO especieAEspecieResponseDTO(Especie especie) throws ObjetoEnviadoNuloException {
        Validador.validarObjetoNotNull(especie);
        return EspecieResponseDTO.builder()
                .idEspecie(especie.getIdEspecie())
                .codigoCVSA(especie.getCodigoCVSA())
                .denominacion(especie.getDenominacion())
                .laminaMinima(especie.getLaminaMinima())
                .precio(especie.getPrecio())
                .cafci(especie.getCafci())
                .cuentaDeEmision(especie.getCuentaDeEmision())
                .estado(especie.getEstado())
                .idEmisor(especie.getIdEmisor())
                .idGerente(especie.getIdGerente())
                .vigencia(especie.getVigencia())
                .plazoDeLiquidacion(especie.getPlazoDeLiquidacion())
                .codigoCNV(especie.getCodigoCNV())
                .isin(especie.getIsin())
                .familiaDeFondos(especie.getFamiliaDeFondos())
                .observaciones(especie.getObservaciones())
                .idMoneda(especie.getIdMoneda())
                .fechaAlta(especie.getFechaAlta())
                .build();
    }

    public static List<EspecieResponseDTO> especiesAEspeciesResponseDTO(List<Especie> especies) throws ObjetoEnviadoNuloException {
        List<EspecieResponseDTO> especiesResponses = new ArrayList<>();

        for (Especie especie : especies) {
            especiesResponses.add(EspecieControllerMapper.especieAEspecieResponseDTO(especie));
        }

        return especiesResponses;
    }
}
