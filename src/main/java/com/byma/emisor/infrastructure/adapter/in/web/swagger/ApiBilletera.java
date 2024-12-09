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

    String OK = "200";
    String CREATE = "201";
    String BAD_REQUEST = "400";
    String ERROR = "500";
    String NOT_FOUND = "404";
    String NO_CONTENT = "204";

    String MENSAJE_OK_FOUND_ALL = "Billeteras encontradas exitosamente";
    String MENSAJE_OK_FOUND = "Billetera encontrada exitosamente";
    String MENSJAE_OK_UPDATE = "Billetera actualizado exitosamente";

    String MENSAJE_CREATE = "Billetera creada exitosamente";
    String MENSAJE_NOT_FOUND = "Error: Billetera no encontrada";
    String MENSAJE_BAD_REQUEST = "Error: Parametros nulos";
    String MENSAJE_ERROR = "Error interno del servidor";
    String MENSAJE_NO_CONTENT = "Billetera eliminado exitosamente";

    @Operation(summary = "Crear billetera")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATE, description = MENSAJE_CREATE),
            @ApiResponse(responseCode = BAD_REQUEST, description = MENSAJE_BAD_REQUEST),
            @ApiResponse(responseCode = ERROR, description = MENSAJE_ERROR)
    })
    ResponseEntity<BilleteraResponseDto> crear(@RequestBody @Valid BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException, BilleteraNoEncontradoException;

    @Operation(summary = "Obtener todas las billeteras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = MENSAJE_OK_FOUND_ALL),
            @ApiResponse(responseCode = ERROR, description = MENSAJE_ERROR)
    })
    ResponseEntity<List<BilleteraResponseDto>> listarBilleteras() throws ObjetoNuloException;

    @Operation(summary = "Obtener una billetera por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = MENSAJE_OK_FOUND),
            @ApiResponse(responseCode = NOT_FOUND, description = MENSAJE_NOT_FOUND),
            @ApiResponse(responseCode = BAD_REQUEST, description = MENSAJE_BAD_REQUEST),
            @ApiResponse(responseCode = ERROR, description = MENSAJE_ERROR)
    })
    ResponseEntity<BilleteraResponseDto> obtenerPorId(@PathVariable Long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException;

    @Operation(summary = "Actualizar una billetera por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = MENSJAE_OK_UPDATE),
            @ApiResponse(responseCode = NOT_FOUND, description = MENSAJE_NOT_FOUND),
            @ApiResponse(responseCode = BAD_REQUEST, description = MENSAJE_BAD_REQUEST),
            @ApiResponse(responseCode = ERROR, description = MENSAJE_ERROR)
    })
    ResponseEntity<BilleteraResponseDto> actualizar(@PathVariable Long idBilletera, @RequestBody BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException, BilleteraNoEncontradoException;

    @Operation(summary = "Eliminar una billetera")
    @ApiResponses(value = {
            @ApiResponse(responseCode = NO_CONTENT, description = MENSAJE_NO_CONTENT),
            @ApiResponse(responseCode = NOT_FOUND, description = MENSAJE_NOT_FOUND),
            @ApiResponse(responseCode = BAD_REQUEST, description = MENSAJE_BAD_REQUEST),
            @ApiResponse(responseCode = ERROR, description = MENSAJE_ERROR)
    })
    ResponseEntity<Void> eliminar(@PathVariable Long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException;
}
