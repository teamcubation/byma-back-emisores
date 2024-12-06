package com.byma.suscripcion.service;

import com.byma.emisor.application.exception.IdNuloException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.application.port.out.SuscripcionOutPort;
import com.byma.emisor.application.service.SuscripcionService;
import com.byma.emisor.domain.model.SuscripcionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SuscripcionServiceTest {
    @Mock
    private SuscripcionOutPort suscripcionOutPort;
    @InjectMocks
    private SuscripcionService suscripcionService;
    private SuscripcionModel suscripcion;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        suscripcion = SuscripcionModel.builder()
                .idSuscripcion(1L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build();
    }

    @Test
    public void shouldCreateSuscripcion() {
        when(suscripcionOutPort.crear(any(SuscripcionModel.class))).thenReturn(suscripcion);
        SuscripcionModel result = suscripcionService.crear(suscripcion);
        assertNotNull(result);
        assertEquals(suscripcion.getIdSuscripcion(), result.getIdSuscripcion());
        verify(suscripcionOutPort, times(1)).crear(any(SuscripcionModel.class));
    }
    @Test
    void whenCreatedSuscripcionIsNull_thenShouldThrowException() {
        assertThrows(ObjetoNuloException.class, () -> suscripcionService.crear(null));
    }

    @Test
    public void shouldUpdateSuscripcion() throws SuscripcionNoEncontradaException, IdNuloException {
        when(suscripcionOutPort.actualizar(any(SuscripcionModel.class))).thenReturn(suscripcion);
        when(suscripcionOutPort.obtenerPorId(1L)).thenReturn(Optional.ofNullable(suscripcion));
        SuscripcionModel result = suscripcionService.actualizar(suscripcion, 1L);
        assertNotNull(result);
        assertEquals(suscripcion.getIdSuscripcion(), result.getIdSuscripcion());
        verify(suscripcionOutPort, times(1)).actualizar(any(SuscripcionModel.class));
    }
    @Test
    void whenUpdatedSuscripcionIsNull_thenShouldThrowException() {
        assertThrows(ObjetoNuloException.class, () -> suscripcionService.actualizar(null, 1L));
    }
    @Test
    void whenUpdatedIdIsNull_thenShouldThrowException() {
        assertThrows(IdNuloException.class, () -> suscripcionService.actualizar(suscripcion, 0));
    }
    @Test
    public void shouldDeleteSuscripcion() throws SuscripcionNoEncontradaException {
        when(suscripcionOutPort.obtenerPorId(1L)).thenReturn(Optional.ofNullable(suscripcion));
        suscripcionService.eliminar(1L);
        verify(suscripcionOutPort, times(1)).eliminarPorId(1L);
    }
    @Test
    void whenDeletedIdNotfound_thenShouldThrowException() {
        assertThrows(SuscripcionNoEncontradaException.class, () -> suscripcionService.eliminar(4));
    }
    @Test
    public void shouldFetchSuscripcionById() throws SuscripcionNoEncontradaException, IdNuloException {
        when(suscripcionOutPort.obtenerPorId(1L)).thenReturn(Optional.of(suscripcion));
        SuscripcionModel result = suscripcionService.obtenerPorId(1L);
        assertNotNull(result);
        assertEquals(suscripcion.getIdSuscripcion(), result.getIdSuscripcion());
        verify(suscripcionOutPort, times(2)).obtenerPorId(1L);
    }
    @Test
    void whenFetchedIdNotfound_thenShouldThrowException() {
        assertThrows(SuscripcionNoEncontradaException.class, () -> suscripcionService.obtenerPorId(4));
    }
    @Test
    void whenFetchedIdIsNull_thenShouldThrowException() {
        assertThrows(IdNuloException.class, () -> suscripcionService.obtenerPorId(0));
    }
    @Test
    public void shouldFetchAllSuscripciones() {
        when(suscripcionOutPort.listarSuscripciones()).thenReturn(List.of(suscripcion));
        List<SuscripcionModel> result = suscripcionService.listarSuscripciones();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(suscripcionOutPort, times(1)).listarSuscripciones();
    }
}
