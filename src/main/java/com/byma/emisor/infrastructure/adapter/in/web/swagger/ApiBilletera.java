package com.byma.emisor.infrastructure.adapter.in.web.swagger;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.BilleteraRequestDto;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.BilleteraResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ApiBilletera {
    @Operation(summary = "Crear billetera")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Billetera creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<BilleteraResponseDto> crear(@RequestBody @Valid BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException, BilleteraNoEncontradoException;

    @Operation(summary = "Obtener todas las billeteras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billeteras encontradas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<BilleteraResponseDto>> listarBilleteras() throws ObjetoNuloException;

    @Operation(summary = "Obtener una billetera por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billetera encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Billetera no encontrada"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<BilleteraResponseDto> obtenerPorId(@PathVariable Long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException;

    @Operation(summary = "Actualizar un billetera por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billetera actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Billetera no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<BilleteraResponseDto> actualizar(@PathVariable Long idBilletera, @RequestBody BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException, BilleteraNoEncontradoException;

    @Operation(summary = "Eliminar un billetera")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Billetera eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Billetera no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error: Parametros nulos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<Void> eliminar(@PathVariable Long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException;
}
