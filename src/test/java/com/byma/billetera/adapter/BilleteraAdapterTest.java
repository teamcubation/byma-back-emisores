package com.byma.billetera.adapter;

import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.domain.model.Billetera;
import com.byma.emisor.infrastructure.adapter.out.BilleteraPersistanceAdapter;
import com.byma.emisor.infrastructure.adapter.out.persistance.entity.BilleteraEntity;
import com.byma.emisor.infrastructure.adapter.out.persistance.repository.BilleteraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BilleteraAdapterTest {
    @Mock
    private BilleteraRepository billeteraRepository;

    @InjectMocks
    private BilleteraPersistanceAdapter billeteraPersistanceAdapter;

    private Billetera billetera;
    private BilleteraEntity billeteraEntity;

    private static final Long ID_1 = 1L;
    private static final String DENOMINACION_1 = "nombre_prueba_1";
    private static final String EMAIL_1 = "email_prueba_1";
    private static final String CUENTA_1 = "cuenta_1";
    private static final String ACDI_1 = "acdi_1";
    private static final String OBSERVACIONES_1 = "observaciones_1";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        billetera = Billetera.builder()
                .id(ID_1)
                .mail(EMAIL_1)
                .idCuenta(CUENTA_1)
                .denominacion(DENOMINACION_1)
                .liquidaEnByma(true)
                .habilitado(true)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_1)
                .idAcdi(ACDI_1)
                .build();

        billeteraEntity = BilleteraEntity.builder()
                .id(ID_1)
                .mail(EMAIL_1)
                .idCuenta(CUENTA_1)
                .denominacion(DENOMINACION_1)
                .liquidaEnByma(true)
                .habilitado(true)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_1)
                .idAcdi(ACDI_1)
                .build();

    }

    @Test
    void deberiaCrearBilleteraExitosamente() throws Exception {
        when(billeteraRepository.save(any(BilleteraEntity.class))).thenReturn(billeteraEntity);

        Billetera resultado = billeteraPersistanceAdapter.crear(billetera);

        assertNotNull(resultado);
        assertEquals(billetera.getId(), resultado.getId());
        assertEquals(billetera.getMail(), resultado.getMail());
        assertEquals(billetera.getIdCuenta(), resultado.getIdCuenta());
        assertEquals(billetera.getDenominacion(), resultado.getDenominacion());
        assertEquals(billetera.isLiquidaEnByma(), resultado.isLiquidaEnByma());
        assertEquals(billetera.isHabilitado(), resultado.isHabilitado());
        assertEquals(billetera.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(billetera.getObservaciones(), resultado.getObservaciones());
        assertEquals(billetera.getIdAcdi(), resultado.getIdAcdi());

        verify(billeteraRepository, times(1)).save(any(BilleteraEntity.class));
    }

    @Test
    void deberiaObtenerBilleteraPorIdExitosamente() throws Exception {
        when(billeteraRepository.findById(ID_1)).thenReturn(Optional.of(billeteraEntity));

        Billetera resultado = billeteraPersistanceAdapter.obtenerPorId(ID_1);

        assertEquals(ID_1, resultado.getId());

        verify(billeteraRepository, times(1)).findById(ID_1);
    }

    @Test
    void deberiaLanzarExcepcionCuandoNoSeEncuentraBilleteraPorId() {
        when(billeteraRepository.findById(ID_1)).thenReturn(Optional.empty());

        assertThrows(BilleteraNoEncontradoException.class, () -> billeteraPersistanceAdapter.obtenerPorId(ID_1));
    }

    @Test
    void deberiaObtenerTodosLasBilleterasExitosamente() throws Exception {
        when(billeteraRepository.findAll()).thenReturn(List.of(billeteraEntity));

        List<Billetera> resultado = billeteraPersistanceAdapter.listarBilleteras();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        verify(billeteraRepository, times(1)).findAll();
    }

    @Test
    void deberiaLanzarExcepcionCuandoNoHayBilleteras() throws Exception {
        when(billeteraRepository.findAll()).thenReturn(List.of());

        List<Billetera> resultado = billeteraPersistanceAdapter.listarBilleteras();

        assertNotNull(resultado);
        assertEquals(0, resultado.size());

        verify(billeteraRepository, times(1)).findAll();
    }

    @Test
    void deberiaEliminarBilleteraExitosamente() throws Exception {
        when(billeteraRepository.findById(ID_1)).thenReturn(Optional.ofNullable(billeteraEntity));

        billeteraPersistanceAdapter.eliminarPorId(ID_1);

        verify(billeteraRepository, times(1)).deleteById(ID_1);

    }

    @Test
    void deberiaLanzarExcepcionCuandoNoSeEncuentraBilleteraParaEliminar() {
        when(billeteraRepository.findById(ID_1)).thenReturn(Optional.empty());

        assertThrows(BilleteraNoEncontradoException.class, () -> billeteraPersistanceAdapter.eliminarPorId(ID_1));
    }
}
