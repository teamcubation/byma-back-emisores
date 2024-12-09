package com.byma.emisor.application.service;


import com.byma.emisor.application.exception.gerente.GerenteNoEncontradoException;
import com.byma.emisor.application.port.out.GerenteOutPort;
import com.byma.emisor.domain.model.Gerente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenteServiceTest {

    public static final long ID_REGISTRO_INVALIDO = 999L;
    public static final long ID_REGISTRO = 1L;
    private final LocalDateTime fechaActual = LocalDateTime.now();
    @Mock
    private GerenteOutPort gerenteOutPort;
    @InjectMocks
    private GerenteService gerenteService;
    private Gerente gerenteMock;

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
    }

    @Test
    void crear_DeberiaCrearGerenteExitosamente() {
        when(gerenteOutPort.crear(any(Gerente.class))).thenReturn(gerenteMock);

        Gerente resultado = gerenteService.crear(gerenteMock);

        assertNotNull(resultado);
        assertEquals(gerenteMock.getId(), resultado.getId());
        assertEquals(gerenteMock.getEmailGerente(), resultado.getEmailGerente());
        verify(gerenteOutPort, times(1)).crear(any(Gerente.class));
    }

    @Test
    void crear_ConParametroNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.crear(null);
        });
        verify(gerenteOutPort, never()).crear(any());
    }

    @Test
    void listarGerentes_DeberiaRetornarListaDeGerentes() {
        List<Gerente> gerentes = Arrays.asList(gerenteMock);
        when(gerenteOutPort.listarGerentes()).thenReturn(gerentes);

        List<Gerente> resultado = gerenteService.listarGerentes();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(gerenteOutPort, times(1)).listarGerentes();
    }

    @Test
    void obtenerPorIdOrganizacionGerente_DeberiaRetornarGerente() throws GerenteNoEncontradoException {
        Long idRegistro = 1L;
        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);

        Gerente resultado = gerenteService.obtenerPorIdOrganizacionGerente(idRegistro);

        assertNotNull(resultado);
        assertEquals(gerenteMock.getId(), resultado.getId());
        verify(gerenteOutPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
    }

    @Test
    void obtenerPorIdOrganizacionGerente_ConIdNoExistente_DebeLanzarExcepcion() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO_INVALIDO;
        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro))
                .thenThrow(new GerenteNoEncontradoException("Gerente no encontrado"));

        assertThrows(GerenteNoEncontradoException.class, () -> {
            gerenteService.obtenerPorIdOrganizacionGerente(idRegistro);
        });
    }

    @Test
    void actualizar_DeberiaActualizarGerenteExitosamente() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO;
        Gerente gerenteActualizado = Gerente.builder()
                .id(idRegistro)
                .emailGerente("nuevo@test.com")
                .liquidaEnByma(false)
                .build();

        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);
        when(gerenteOutPort.actualizar(any(Gerente.class))).thenReturn(gerenteActualizado);

        Gerente resultado = gerenteService.actualizar(idRegistro, gerenteActualizado);

        assertNotNull(resultado);
        assertEquals("nuevo@test.com", resultado.getEmailGerente());
        assertFalse(resultado.liquidaEnByma());
        verify(gerenteOutPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
        verify(gerenteOutPort, times(1)).actualizar(any(Gerente.class));
    }

    @Test
    void actualizar_ConParametrosNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.actualizar(null, gerenteMock);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.actualizar(1L, null);
        });
    }

    @Test
    void toggleHabilitar_DeberiaAlternarEstadoHabilitado() throws GerenteNoEncontradoException {

        Long idRegistro = ID_REGISTRO;
        boolean estadoInicial = gerenteMock.isHabilitado();
        Gerente gerenteDeshabilitado = Gerente.builder()
                .id(idRegistro)
                .habilitado(!estadoInicial)
                .build();

        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro)).thenReturn(gerenteMock);
        when(gerenteOutPort.actualizar(any(Gerente.class))).thenReturn(gerenteDeshabilitado);

        Gerente resultado = gerenteService.toggleHabilitar(idRegistro);

        assertNotNull(resultado);
        assertNotEquals(estadoInicial, resultado.isHabilitado());
        verify(gerenteOutPort, times(1)).obtenerPorIdOrganizacionGerente(idRegistro);
        verify(gerenteOutPort, times(1)).actualizar(any(Gerente.class));
    }

    @Test
    void toggleHabilitar_ConIdNull_DebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenteService.toggleHabilitar(null);
        });
    }

    @Test
    void toggleHabilitar_ConGerenteNoEncontrado_DebeLanzarExcepcion() throws GerenteNoEncontradoException {
        Long idRegistro = ID_REGISTRO_INVALIDO;
        when(gerenteOutPort.obtenerPorIdOrganizacionGerente(idRegistro))
                .thenThrow(new GerenteNoEncontradoException("Gerente no encontrado"));

        assertThrows(GerenteNoEncontradoException.class, () -> {
            gerenteService.toggleHabilitar(idRegistro);
        });
    }
}