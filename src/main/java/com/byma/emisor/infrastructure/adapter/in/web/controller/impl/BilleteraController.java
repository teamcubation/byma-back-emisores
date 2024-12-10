package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;

import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.application.port.in.BilleteraInPort;
import com.byma.emisor.domain.model.Billetera;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.BilleteraRequestDto;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.BilleteraResponseDto;
import com.byma.emisor.infrastructure.adapter.in.web.mapper.BilleteraControllerMapper;
import com.byma.emisor.infrastructure.adapter.in.web.swagger.ApiBilletera;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/billeteras")
public class BilleteraController implements ApiBilletera {

    private final BilleteraInPort billeteraInPort;

    @PostMapping()
    public ResponseEntity<BilleteraResponseDto> crear(@RequestBody @Valid BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException {
        log.info("Solicitud para crear una billetera: {}", billeteraRequestDto);
        Billetera billetera = billeteraInPort.crear(BilleteraControllerMapper.billeteraRequestDtoABilletera(billeteraRequestDto));
        log.info("Finalizacion de la creacion de la billetera: {}", billetera);

        return ResponseEntity.status(HttpStatus.CREATED).body(BilleteraControllerMapper.billeteraABilleteraResponseDto(billetera));
    }

    @GetMapping("/{idBilletera}")
    public ResponseEntity<BilleteraResponseDto> obtenerPorId(@PathVariable Long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException {
        log.info("Solicitud de obtener billetera por id: {}", idBilletera);
        Billetera billetera = billeteraInPort.obtenerPorId(idBilletera);
        log.info("Finalizacion de obtener billetera por id: {}", idBilletera);

        return ResponseEntity.status(HttpStatus.OK).body(BilleteraControllerMapper.billeteraABilleteraResponseDto(billetera));
    }

    @PutMapping("/{idBilletera}")
    public ResponseEntity<BilleteraResponseDto> actualizar(@PathVariable Long idBilletera, @RequestBody @Valid BilleteraRequestDto billeteraRequestDto) throws ObjetoNuloException, BilleteraNoEncontradoException {
        log.info("Solicitud de actualizar billetera con id: {}", idBilletera);
        Billetera billetera = billeteraInPort.actualizar(BilleteraControllerMapper.billeteraRequestDtoABilletera(billeteraRequestDto), idBilletera);
        log.info("Finalizacion de la actualizacion de la billetera con id: {}", idBilletera);

        return ResponseEntity.status(HttpStatus.OK).body(BilleteraControllerMapper.billeteraABilleteraResponseDto(billetera));
    }

    @GetMapping()
    public ResponseEntity<List<BilleteraResponseDto>> listarBilleteras() throws ObjetoNuloException {
        log.info("Solicitud para obtener todas las billeteras");
        List<Billetera> billeteras = billeteraInPort.listarBilleteras();
        log.info("Finalizacion de la solicitud para obtener todas las billeteras");

        List<BilleteraResponseDto> billeterasResponseDto = new ArrayList<>();
        for (Billetera billetera : billeteras) {
            billeterasResponseDto.add(BilleteraControllerMapper.billeteraABilleteraResponseDto(billetera));
        }

        return ResponseEntity.status(HttpStatus.OK).body(billeterasResponseDto);
    }


    @DeleteMapping("/{idBilletera}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idBilletera) throws ObjetoNuloException, BilleteraNoEncontradoException {
        log.info("Solicitud para eliminar una billetera por id: {}", idBilletera);
        billeteraInPort.eliminar(idBilletera);
        log.info("Finalizacion de eliminacion de billetera por id: {}", idBilletera);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
