package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.port.in.EmisorInPort;
import com.byma.emisor.domain.model.Emisor;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EmisorRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.EmisorResponseDTO;
import com.byma.emisor.infrastructure.adapter.in.web.mapper.EmisorControllerMapper;
import com.byma.emisor.infrastructure.adapter.in.web.swagger.ApiEmisor;
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
public class EmisorController implements ApiEmisor {

    private final EmisorInPort emisorInPort;

    @PostMapping()
    public ResponseEntity<EmisorResponseDTO> crear(@RequestBody @Valid EmisorRequestDTO emisorRequestDTO) throws ObjetoNuloException, EmisorDuplicadoException, EmisorNoEncontradoException {
        log.info("Solicitud para crear un emisor: {}", emisorRequestDTO);

        ValidacionController.validarParametrosNull(emisorRequestDTO);
        Emisor emisorCreado = emisorInPort.crear(EmisorControllerMapper.emisorRequestDtoAEmisor(emisorRequestDTO));

        log.info("Creacion de emisor finalizada: {}", emisorCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmisorControllerMapper.emisorAEmisorResponseDto(emisorCreado));
    }

    @GetMapping()
    public ResponseEntity<List<EmisorResponseDTO>> listarEmisores() {
        log.info("Solicitud para obtener todos los emisores");

        List<Emisor> emisores = emisorInPort.listarEmisores();

        log.info("Finalizacion de la solicitud para obtener todos los emisores");
        return ResponseEntity.status(HttpStatus.OK).body(emisores.stream().map(EmisorControllerMapper::emisorAEmisorResponseDto).toList());
    }

    @GetMapping("/{idEmisor}")
    public ResponseEntity<EmisorResponseDTO> obtener(@PathVariable Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException {
        log.info("Solicitud para obtener emisor por id: {}", idEmisor);

        ValidacionController.validarParametrosNull(idEmisor);
        Emisor emisor = emisorInPort.obtenerPorId(idEmisor);

        log.info("Finalizacion de obtener emisor por id de emisor: {}", emisor);
        return ResponseEntity.status(HttpStatus.OK).body(EmisorControllerMapper.emisorAEmisorResponseDto(emisor));
    }

    @PutMapping("/{idEmisor}")
    public ResponseEntity<EmisorResponseDTO> actualizar(@RequestBody EmisorRequestDTO emisorRequestDTO, @PathVariable Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException, EmisorDuplicadoException {
        log.info("Solicitud para actualizar un emisor por id de emisor: {}, datos a actualizar {}", idEmisor, emisorRequestDTO);

        ValidacionController.validarParametrosNull(emisorRequestDTO, idEmisor);
        Emisor emisorActualizado = emisorInPort.actualizar(EmisorControllerMapper.emisorRequestDtoAEmisor(emisorRequestDTO), idEmisor);
        EmisorResponseDTO emisorResponseDTO = EmisorControllerMapper.emisorAEmisorResponseDto(emisorActualizado);

        log.info("Finalizacion de actualizacion de emisor, emisor actualizado: {}", emisorActualizado);
        return ResponseEntity.status(HttpStatus.OK).body(emisorResponseDTO);
    }

    @DeleteMapping("/{idEmisor}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEmisor) throws ObjetoNuloException, EmisorNoEncontradoException {
        log.info("Solicitud para eliminar un emisor por id de emisor: {}", idEmisor);

        ValidacionController.validarParametrosNull(idEmisor);
        emisorInPort.eliminar(idEmisor);

        log.info("Finalizacion de eliminacion de emisor por id de emisor: {}", idEmisor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
