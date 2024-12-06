package com.byma.suscripcion.adapter;

import com.byma.emisor.application.exception.IdNuloException;
import com.byma.emisor.application.exception.ObjetoNuloException;
import com.byma.emisor.application.exception.SuscripcionNoEncontradaException;
import com.byma.emisor.domain.model.SuscripcionModel;
import com.byma.emisor.infrastructure.adapter.out.SuscripcionPersistenceAdapter;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.SuscripcionEntity;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.SuscripcionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SuscripcionAdapterTest {
    @Mock
    private SuscripcionRepository suscripcionRepository;
    @InjectMocks
    private SuscripcionPersistenceAdapter suscripcionAdapter;

    private SuscripcionModel suscripcion;
    private SuscripcionEntity suscripcionEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        suscripcion = SuscripcionModel.builder()
                .idSuscripcion(1L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build();
        suscripcionEntity = SuscripcionEntity.builder()
                .idSuscripcion(1L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build();
    }

    @Test
    void shouldSaveSuscripcion_WhenSuscriptionIsOk() {
        when(suscripcionRepository.save(any(SuscripcionEntity.class))).thenReturn(suscripcionEntity);
        SuscripcionModel result = suscripcionAdapter.crear(suscripcion);
        assertNotNull(result);
        assertEquals(suscripcion.getIdSuscripcion(), result.getIdSuscripcion());
        verify(suscripcionRepository, times(1)).save(any(SuscripcionEntity.class));

    }

    @Test
    void shouldThrowException_WhenSuscripcionIsNull() {
        when(suscripcionRepository.save(any(SuscripcionEntity.class))).thenReturn(suscripcionEntity);
        assertThrows(ObjetoNuloException.class, () -> suscripcionAdapter.crear(null));
    }

    @Test
    void shouldFetchSuscripcionById_WhenIdIsOk() {
        when(suscripcionRepository.findById(1L)).thenReturn(Optional.of(suscripcionEntity));
        Optional<SuscripcionModel> result = suscripcionAdapter.obtenerPorId(1L);
        assertTrue(result.isPresent());
        assertNotNull(result);
        assertEquals(suscripcion.getIdSuscripcion(), result.get().getIdSuscripcion());
        verify(suscripcionRepository, times(1)).findById(1L);
    }
    @Test
    void DoNothing_whenEliminarPorId() throws SuscripcionNoEncontradaException {
        when(suscripcionRepository.findById(1L)).thenReturn(Optional.of(suscripcionEntity));
        suscripcionAdapter.eliminarPorId(1L);
        verify(suscripcionRepository, times(1)).deleteById(1L);
    }
    @Test
    void shouldThrowException_WhenIdIsNotFound() {
        assertThrows(SuscripcionNoEncontradaException.class, () -> suscripcionAdapter.eliminarPorId(4L));
    }
    @Test
    void shouldFetchAllSuscripciones(){
        when(suscripcionRepository.findAll()).thenReturn(List.of(suscripcionEntity));
        List<SuscripcionModel> result = suscripcionAdapter.listarSuscripciones();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(suscripcionRepository, times(1)).findAll();
    }
    @Test
    void ShouldUpdateSuscripcion_WhenIsOk(){
        when(suscripcionRepository.save(any(SuscripcionEntity.class))).thenReturn(suscripcionEntity);
        SuscripcionModel result = suscripcionAdapter.crear(suscripcion);
        assertNotNull(result);
        assertEquals(suscripcion.getIdSuscripcion(), result.getIdSuscripcion());
        verify(suscripcionRepository, times(1)).save(any(SuscripcionEntity.class));
    }
    @Test
    void shouldThrowException_WhenUpdatedSuscripcionIsNull() {
        when(suscripcionRepository.save(any(SuscripcionEntity.class))).thenReturn(suscripcionEntity);
        assertThrows(ObjetoNuloException.class, () -> suscripcionAdapter.actualizar(null));
    }
    @Test
    void shouldThrowException_WhenCreatedSuscripcionIsNull() {
        when(suscripcionRepository.save(any(SuscripcionEntity.class))).thenReturn(suscripcionEntity);
        assertThrows(ObjetoNuloException.class, () -> suscripcionAdapter.crear(null));
    }

}
