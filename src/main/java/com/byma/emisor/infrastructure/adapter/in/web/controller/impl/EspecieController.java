package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;


import com.byma.emisor.application.exception.especie.AtributosNulosException;
import com.byma.emisor.application.exception.especie.EspecieConIdExistenteException;
import com.byma.emisor.application.exception.especie.EspecieNoEncontradaException;
import com.byma.emisor.application.exception.especie.ObjetoEnviadoNuloException;
import com.byma.emisor.application.port.in.EspecieInPort;
import com.byma.emisor.domain.model.Especie;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EspecieRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.EspecieResponseDTO;
import com.byma.emisor.infrastructure.adapter.in.web.mapper.EspecieControllerMapper;
import com.byma.emisor.infrastructure.adapter.in.web.swagger.ApiEspecie;
import com.byma.emisor.util.validation.Validador;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especies")
@Slf4j
public class EspecieController implements ApiEspecie {

    private final EspecieInPort especieInPort;

    public EspecieController(EspecieInPort especieInPort) {
        this.especieInPort = especieInPort;
    }

    @PostMapping("")
    public ResponseEntity<EspecieResponseDTO> crear(@RequestBody @Valid EspecieRequestDTO especieRequestDTO) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException {
        log.info("Solicitud para crear un especie: {}", especieRequestDTO);
        Validador.validarObjetoNotNull(especieRequestDTO);
        Especie especieCreada = especieInPort.crear(EspecieControllerMapper.especieRequestDtoAEspecie(especieRequestDTO));
        log.info("Creacion de especie finalizada: {}", especieCreada);

        return ResponseEntity.ok().body(EspecieControllerMapper.especieAEspecieResponseDTO(especieCreada));
    }

    @GetMapping("")
    public ResponseEntity<List<EspecieResponseDTO>> listarEspecies() throws ObjetoEnviadoNuloException {

        log.info("Solicitud para obtener todas las especies");
        List<Especie> especies = especieInPort.listarEspecies();
        List<EspecieResponseDTO> especiesResponses = EspecieControllerMapper.especiesAEspeciesResponseDTO(especies);
        log.info("Finalizacion de la solicitud para obtener todas las especies");

        return ResponseEntity.ok().body(especiesResponses);
    }

    @GetMapping("/{idEspecie}")
    public ResponseEntity<EspecieResponseDTO> obtenerPorIdEspecie(@PathVariable Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException {

        log.info("Solicitud para obtener especie por id: {}", idEspecie);
        Validador.validarIdNull(idEspecie);
        Especie especie = especieInPort.obtenerPorId(idEspecie);
        log.info("Finalizacion de obtener especie por id: {}", especie);

        return ResponseEntity.ok().body(EspecieControllerMapper.especieAEspecieResponseDTO(especie));
    }

    @PutMapping("/{idEspecie}")
    public ResponseEntity<EspecieResponseDTO> actualizar(@PathVariable Long idEspecie, @RequestBody @Valid EspecieRequestDTO especieRequestDTO) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException, EspecieNoEncontradaException {

        log.info("Solicitud para actualizar especie por id: {}, datos a actualizar: {}", idEspecie, especieRequestDTO);
        Validador.validarIdNull(idEspecie);
        Validador.validarObjetoNotNull(especieRequestDTO);
        Especie especieActualizado = especieInPort.actualizar(idEspecie, EspecieControllerMapper.especieRequestDtoAEspecie(especieRequestDTO));
        log.info("Finalizacion de actualizacion de especie, especie actualizado: {}", especieActualizado);

        return ResponseEntity.ok().body(EspecieControllerMapper.especieAEspecieResponseDTO(especieActualizado));
    }

    @DeleteMapping("/{idEspecie}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException {

        log.info("Solicitud para eliminar especie con id: {}", idEspecie);
        Validador.validarIdNull(idEspecie);
        especieInPort.eliminar(idEspecie);
        log.info("Finalizacion de eliminacion de especie con id: {}", idEspecie);

        return ResponseEntity.ok().build();
    }
}
