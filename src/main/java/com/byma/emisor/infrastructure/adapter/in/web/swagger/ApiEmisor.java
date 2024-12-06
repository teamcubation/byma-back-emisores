package com.byma.emisor.infrastructure.adapter.in.web.swagger;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EmisorRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.EmisorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Emisor", description = "Api para gestionar emisores")
public interface ApiEmisor {
    @Operation(summary = "Crear emisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Emisor creado exitosamente"),
            @ApiResponse(responseCode = "409", description = "Error: Emisor duplicado"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<EmisorResponseDTO> crear(@RequestBody @Valid EmisorRequestDTO gerenteRequestDTO) throws ObjetoNuloException, EmisorDuplicadoException, EmisorNoEncontradoException;

    @Operation(summary = "Obtener todos los emisores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emisores encontrados exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<EmisorResponseDTO>> listarEmisores();

    @Operation(summary = "Obtener un emisor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emisor encontrado"),
            @ApiResponse(responseCode = "404", description = "Error: Emisor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<EmisorResponseDTO> obtener(@PathVariable Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException;

    @Operation(summary = "Actualizar un emisor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emisor actualizado exitosamente"),
            @ApiResponse(responseCode = "409", description = "Error: Emisor duplicado"),
            @ApiResponse(responseCode = "404", description = "Error: Emisor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<EmisorResponseDTO> actualizar(@RequestBody EmisorRequestDTO emisorRequestDTO, @PathVariable Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException, EmisorDuplicadoException;

    @Operation(summary = "Eliminar un emisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Emisor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Emisor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<Void> eliminar(@PathVariable Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException;
}
