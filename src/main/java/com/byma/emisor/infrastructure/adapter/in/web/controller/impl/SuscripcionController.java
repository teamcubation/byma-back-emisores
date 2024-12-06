package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;

import com.byma.emisor.application.exception.IdNuloException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.application.port.in.SuscripcionInPort;
import com.byma.emisor.domain.model.SuscripcionModel;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.SuscripcionRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.SuscripcionResponseDTO;
import com.byma.emisor.infrastructure.adapter.in.web.mapper.SuscripcionControllerMapper;
import com.byma.emisor.infrastructure.adapter.in.web.swagger.ApiSuscripcion;
import com.byma.emisor.infrastructure.adapter.in.web.validation.ValidacionController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/suscripcion")
public class SuscripcionController implements ApiSuscripcion {
    private final SuscripcionInPort suscripcionInPort;

    @PostMapping
    public ResponseEntity<SuscripcionResponseDTO> crear(@RequestBody @Valid SuscripcionRequestDTO suscripcionRequestDTO) {
        log.info("Solicitud para crear una suscripcion");
        ValidacionController.validarObjetoNotNull(suscripcionRequestDTO);
        SuscripcionModel suscripcionCreada = suscripcionInPort.crear(SuscripcionControllerMapper.suscripcionRequestDTOASuscripcionModel(suscripcionRequestDTO));
        log.info("Creacion de suscripcion finalizada: {}", suscripcionCreada);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuscripcionControllerMapper.suscripcionASuscripcionResponseDto(suscripcionCreada));
    }
    @GetMapping
    public ResponseEntity<List<SuscripcionResponseDTO>> listarSuscripciones() {
        log.info("Solicitud para obtener todas las suscripciones");
        List<SuscripcionModel> suscripciones = suscripcionInPort.listarSuscripciones();
        log.info("Finalizacion de la solicitud para obtener todas las suscripciones");
        return ResponseEntity.status(HttpStatus.OK).body(suscripciones.stream().map(SuscripcionControllerMapper::suscripcionASuscripcionResponseDto).toList());
    }
    @GetMapping("/{idSuscripcion}")
    public ResponseEntity<SuscripcionResponseDTO> obtener(@PathVariable Long idSuscripcion) throws SuscripcionNoEncontradaException, IdNuloException {
        log.info("Solicitud para obtener suscripcion por id: {}", idSuscripcion);
        ValidacionController.validarParametrosNull(idSuscripcion);
        SuscripcionModel suscripcion = suscripcionInPort.obtenerPorId(idSuscripcion);
        log.info("Finalizacion de obtener suscripcion por id de suscripcion: {}", suscripcion);
        return ResponseEntity.status(HttpStatus.OK).body(SuscripcionControllerMapper.suscripcionASuscripcionResponseDto(suscripcion));
    }
    @PutMapping("/{idSuscripcion}")
    public ResponseEntity<SuscripcionResponseDTO> actualizar(@RequestBody @Valid SuscripcionRequestDTO suscripcionRequestDTO, @PathVariable Long idSuscripcion) throws ObjetoNuloException, SuscripcionNoEncontradaException, IdNuloException {
        log.info("Solicitud para actualizar una suscripcion por id de suscripcion: {}, datos a actualizar {}", idSuscripcion, suscripcionRequestDTO);
        ValidacionController.validarParametrosNull(suscripcionRequestDTO, idSuscripcion);
        SuscripcionModel suscripcionActualizado = suscripcionInPort.actualizar(SuscripcionControllerMapper.suscripcionRequestDTOASuscripcionModel(suscripcionRequestDTO), idSuscripcion);
        SuscripcionResponseDTO suscripcionResponseDTO = SuscripcionControllerMapper.suscripcionASuscripcionResponseDto(suscripcionActualizado);
        log.info("Finalizacion de actualizacion de suscripcion, suscripcion actualizado: {}", suscripcionActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(suscripcionResponseDTO);
    }
    @DeleteMapping("/{idSuscripcion}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idSuscripcion) throws ObjetoNuloException, SuscripcionNoEncontradaException {
        log.info("Solicitud para eliminar una suscripcion por id de suscripcion: {}", idSuscripcion);
        ValidacionController.validarParametrosNull(idSuscripcion);
        suscripcionInPort.eliminar(idSuscripcion);
        log.info("Finalizacion de eliminacion de suscripcion por id de suscripcion: {}", idSuscripcion);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
