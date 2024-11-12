package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.exception.ParametroNuloException;
import com.byma.emisor.application.port.in.EmisorInPort;
import com.byma.emisor.domain.model.Emisor;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EmisorRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.mapper.EmisorControllerMapper;
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
@RequestMapping("/api/emisores")
public class EmisorController {

    private final EmisorInPort emisorInPort;

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody @Valid EmisorRequestDTO emisorRequestDTO) throws ParametroNuloException, EmisorDuplicadoException, EmisorNoEncontradoException {
        log.info("Solicitud para crear un emisor: {}", emisorRequestDTO);
        ValidacionController.validarParametrosNull(emisorRequestDTO);
        Emisor emisorCreado = emisorInPort.crear(EmisorControllerMapper.emisorRequestDtoAEmisor(emisorRequestDTO));
        log.info("Creacion de emisor finalizada: {}", emisorCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmisorControllerMapper.emisorAEmisorResponseDto(emisorCreado));
    }

    @GetMapping()
    public ResponseEntity<?> listarEmisores() {
        log.info("Solicitud para obtener todos los emisores");
        List<Emisor> emisores = emisorInPort.listarEmisores();
        log.info("Finalizacion de la solicitud para obtener todos los emisores");
        return ResponseEntity.status(HttpStatus.OK).body(emisores.stream().map(EmisorControllerMapper::emisorAEmisorResponseDto).toList());
    }

    @GetMapping("/{idEmisor}")
    public ResponseEntity<?> obtener(@PathVariable Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException {
        log.info("Solicitud para obtener emisor por id: {}", idEmisor);
        ValidacionController.validarParametrosNull(idEmisor);
        Emisor emisor = emisorInPort.obtenerPorId(idEmisor);
        log.info("Finalizacion de obtener emisor por id de emisor: {}", emisor);
        return ResponseEntity.status(HttpStatus.OK).body(EmisorControllerMapper.emisorAEmisorResponseDto(emisor));
    }

    @PutMapping("/{idEmisor}")
    public ResponseEntity<?> actualizar(@RequestBody EmisorRequestDTO emisorRequestDTO, @PathVariable Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException, EmisorDuplicadoException {
        log.info("Solicitud para actualizar un emisor por id de emisor: {}, datos a actualizar {}", idEmisor, emisorRequestDTO);
        ValidacionController.validarParametrosNull(emisorRequestDTO);
        ValidacionController.validarParametrosNull(idEmisor);
        Emisor emisorActualizado = emisorInPort.actualizar(EmisorControllerMapper.emisorRequestDtoAEmisor(emisorRequestDTO), idEmisor);
        log.info("Finalizacion de actualizacion de emisor, emisor actualizado: {}", emisorActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(emisorActualizado);
    }

    @DeleteMapping("/{idEmisor}")
    public ResponseEntity<?> eliminar(@PathVariable Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException {
        log.info("Solicitud para eliminar un emisor por id de emisor: {}", idEmisor);
        ValidacionController.validarParametrosNull(idEmisor);
        emisorInPort.eliminar(idEmisor);
        log.info("Finalizacion de eliminacion de emisor por id de emisor: {}", idEmisor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
