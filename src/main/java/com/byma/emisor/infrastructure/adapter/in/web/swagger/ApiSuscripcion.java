package com.byma.emisor.infrastructure.adapter.in.web.swagger;

import com.byma.emisor.application.exception.IdNuloException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.SuscripcionRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.SuscripcionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ApiSuscripcion {
    @Operation(summary = "crear suscripcion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "suscripcion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error: Objeto nulo"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<SuscripcionResponseDTO> crear(@RequestBody @Valid SuscripcionRequestDTO suscripcionRequestDTO) throws ObjetoNuloException;
    @Operation(summary = "actualizar suscripcion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "suscripcion actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error: Objeto nulo"),
            @ApiResponse(responseCode = "404", description = "Error: Objeto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<SuscripcionResponseDTO> actualizar(@RequestBody @Valid SuscripcionRequestDTO suscripcionRequestDTO, @PathVariable Long id) throws ObjetoNuloException, SuscripcionNoEncontradaException, IdNuloException;
    @Operation(summary = "eliminar suscripcion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "suscripcion eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Objeto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<Void> eliminar(@PathVariable Long id) throws ObjetoNuloException, SuscripcionNoEncontradaException, IdNuloException;
    @Operation(summary = "obtener suscripcion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "suscripcion encontrada"),
            @ApiResponse(responseCode = "404", description = "Error: Objeto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<SuscripcionResponseDTO> obtener(@PathVariable Long id) throws ObjetoNuloException, SuscripcionNoEncontradaException, IdNuloException;
    @Operation(summary = "obtener todas las suscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "suscripciones encontradas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<SuscripcionResponseDTO>> listarSuscripciones();
}
