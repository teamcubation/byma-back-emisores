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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emisores")
public class EmisorController {

    private final EmisorInPort emisorInPort;

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody @Valid EmisorRequestDTO emisorRequestDTO) throws ParametroNuloException, EmisorDuplicadoException {
        ValidacionController.validarParametrosNull(emisorRequestDTO);
        Emisor emisorCreado = emisorInPort.crear(EmisorControllerMapper.emisorRequestDtoAEmisor(emisorRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(EmisorControllerMapper.emisorAEmisorResponseDto(emisorCreado));
    }

    @GetMapping()
    public ResponseEntity<?> listarEmisores() {
        List<Emisor> emisores = emisorInPort.listarEmisores();
        return ResponseEntity.status(HttpStatus.OK).body(emisores.stream().map(EmisorControllerMapper::emisorAEmisorResponseDto).toList());
    }

    @GetMapping("/{idEmisor}")
    public ResponseEntity<?> obtener(@PathVariable Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException {
        ValidacionController.validarParametrosNull(idEmisor);
        Emisor emisor = emisorInPort.obtenerPorId(idEmisor);
        return ResponseEntity.status(HttpStatus.OK).body(EmisorControllerMapper.emisorAEmisorResponseDto(emisor));
    }

    @PutMapping("/{idEmisor}")
    public ResponseEntity<?> actualizar(@RequestBody EmisorRequestDTO emisorRequestDTO, @PathVariable Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException, EmisorDuplicadoException {
        ValidacionController.validarParametrosNull(emisorRequestDTO);
        ValidacionController.validarParametrosNull(idEmisor);
        Emisor emisorActualizado = emisorInPort.actualizar(EmisorControllerMapper.emisorRequestDtoAEmisor(emisorRequestDTO), idEmisor);
        return ResponseEntity.status(HttpStatus.OK).body(emisorActualizado);
    }

    @DeleteMapping("/{idEmisor}")
    public ResponseEntity<?> eliminar(@PathVariable Long idEmisor) throws ParametroNuloException, EmisorNoEncontradoException {
        ValidacionController.validarParametrosNull(idEmisor);
        emisorInPort.eliminar(idEmisor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
