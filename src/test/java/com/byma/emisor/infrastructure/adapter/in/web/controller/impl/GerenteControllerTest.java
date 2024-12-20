package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;


import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.application.port.in.GerenteInPort;
import com.byma.emisor.domain.model.Gerente;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.GerenteRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.GerenteUpdateRequestDTO;
import com.byma.emisor.infrastructure.adapter.in.web.dto.response.GerenteResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenteControllerTest {
    public static final long ID_REGISTRO_INVALIDO = 999L;
    public static final long ID_REGISTRO = 1L;
    private final LocalDateTime fechaActual = LocalDateTime.now();
    @Mock
    private GerenteInPort gerenteInPort;
    @InjectMocks
    private GerenteController gerenteController;
    private Gerente gerenteMock;
    private GerenteRequestDTO requestDTOMock;
    private GerenteUpdateRequestDTO updateRequestDTOMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        gerenteMock = Gerente.builder()
                .id(1L)
                .denominacion("Gerente Test")
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones("Observaciones test")
                .emailGerente("test@test.com")
                .fechaDeAlta(fechaActual)
                .build();

        requestDTOMock = GerenteRequestDTO.builder()
                .denominacion("Gerente Test")
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones("Observaciones test")
                .mailGerente("test@test.com")
                .fechaDeAlta(fechaActual)
                .build();

        updateRequestDTOMock = GerenteUpdateRequestDTO.builder()
                .liquidaEnByma(true)
                .mailGerente("update@test.com")
                .build();
    }

    @Test
    void crear_DeberiaCrearGerenteYRetornarResponseDTO() {
        when(gerenteInPort.crear(any(Gerente.class))).thenReturn(gerenteMock);

        ResponseEntity<GerenteResponseDTO> response = gerenteController.crear(requestDTOMock);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gerenteMock.getId(), response.getBody().getId());
        assertEquals(gerenteMock.getEmailGerente(), response.getBody().getMailGerente());
        verify(gerenteInPort, times(1)).crear(any(Gerente.class));
    }

    @Test
    void crear_ConParametrosNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteController.crear(null);
        });
    }

    @Test
    void listarGerentes_DeberiaRetornarListaDeGerentes() {
        List<Gerente> gerentes = Arrays.asList(gerenteMock);
        when(gerenteInPort.listarGerentes()).thenReturn(gerentes);

        ResponseEntity<List<GerenteResponseDTO>> response = gerenteController.listarGerentes();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(gerenteInPort, times(1)).listarGerentes();
    }

    @Test
    void obtenerPorIdOrganizacionGerente_DeberiaRetornarGerente() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO;
        when(gerenteInPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);

        ResponseEntity<GerenteResponseDTO> response = gerenteController.obtenerGerentePorId(idRegistro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gerenteMock.getId(), response.getBody().getId());
        verify(gerenteInPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
    }

    @Test
    void obtenerPorIdOrganizacionGerente_ConIdNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteController.obtenerGerentePorId(null);
        });
    }

    @Test
    void actualizar_DeberiaActualizarGerenteYRetornarResponseDTO() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO;
        when(gerenteInPort.actualizar(eq(idRegistro), any(Gerente.class))).thenReturn(gerenteMock);

        ResponseEntity<GerenteResponseDTO> response = gerenteController.actualizar(idRegistro, updateRequestDTOMock);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gerenteMock.getId(), response.getBody().getId());
        verify(gerenteInPort, times(1)).actualizar(eq(idRegistro), any(Gerente.class));
    }

    @Test
    void actualizar_ConParametrosNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteController.actualizar(null, updateRequestDTOMock);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteController.actualizar(1L, null);
        });
    }

    @Test
    void toggleHabilitar_DeberiaActualizarEstadoYRetornarResponseDTO() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO;
        when(gerenteInPort.toggleHabilitar(idRegistro)).thenReturn(gerenteMock);

        ResponseEntity<?> response = gerenteController.toggleHabilitar(idRegistro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(gerenteInPort, times(1)).toggleHabilitar(idRegistro);
    }

    @Test
    void toggleHabilitar_ConIdNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteController.toggleHabilitar(null);
        });
    }
}