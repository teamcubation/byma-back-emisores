package com.byma.billetera.service;

import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.application.port.out.BilleteraOutPort;
import com.byma.emisor.application.service.BilleteraService;
import com.byma.emisor.domain.model.Billetera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BilleteraServiceTest {
    @InjectMocks
    private BilleteraService billeteraService;

    @Mock
    private BilleteraOutPort billeteraOutPort;

    private List<Billetera> billeteras = new ArrayList<>();

    private Billetera billetera;

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long ID_3 = 3L;
    private static final String DENOMINACION_1 = "nombre_prueba_1";
    private static final String DENOMINACION_2 = "nombre_prueba_2";
    private static final String DENOMINACION_3 = "nombre_prueba_3";
    private static final String EMAIL_1 = "email_prueba_1";
    private static final String EMAIL_2 = "email_prueba_1";
    private static final String EMAIL_3 = "email_prueba_1";
    private static final String CUENTA_1 = "cuenta_1";
    private static final String CUENTA_2 = "cuenta_2";
    private static final String CUENTA_3 = "cuenta_3";
    private static final long ID_INEXISTENTE = 8L;
    private static final String ACDI_1 = "acdi_1";
    private static final String ACDI_2 = "acdi_2";
    private static final String ACDI_3 = "acdi_3";
    private static final String OBSERVACIONES_1 = "observaciones_1";
    private static final String OBSERVACIONES_2 = "observaciones_2";
    private static final String OBSERVACIONES_3 = "observaciones_3";

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

        billeteras.add(Billetera.builder()
                .id(ID_1)
                .mail(EMAIL_1)
                .idCuenta(CUENTA_1)
                .denominacion(DENOMINACION_1)
                .liquidaEnByma(true)
                .habilitado(true)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_1)
                .idAcdi(ACDI_1)
                .build());

        billeteras.add(Billetera.builder()
                .id(ID_2)
                .mail(EMAIL_2)
                .idCuenta(CUENTA_2)
                .denominacion(DENOMINACION_2)
                .liquidaEnByma(true)
                .habilitado(true)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_2)
                .idAcdi(ACDI_2)
                .build());

        billeteras.add(Billetera.builder()
                .id(ID_3)
                .mail(EMAIL_3)
                .idCuenta(CUENTA_3)
                .denominacion(DENOMINACION_3)
                .liquidaEnByma(true)
                .habilitado(true)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_3)
                .idAcdi(ACDI_3)
                .build());
    }

    @Test
    void debeRetornarUnaBilletera_cuandoSeCreaUnaBilletera() throws Exception {
        when(billeteraOutPort.crear(billetera)).thenReturn(billetera);

        Billetera resultado = billeteraService.crear(billetera);
        assertEquals(billetera.getId(), resultado.getId());
        assertEquals(billetera.getMail(), resultado.getMail());
        assertEquals(billetera.getIdCuenta(), resultado.getIdCuenta());
        assertEquals(billetera.getDenominacion(), resultado.getDenominacion());
        assertEquals(billetera.isLiquidaEnByma(), resultado.isLiquidaEnByma());
        assertEquals(billetera.isHabilitado(), resultado.isHabilitado());
        assertEquals(billetera.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(billetera.getObservaciones(), resultado.getObservaciones());
        assertEquals(billetera.getIdAcdi(), resultado.getIdAcdi());
    }

    @Test
    void debeListarTodosLasBilleteras_cuandoListarBilleteras() throws Exception {
        when(billeteraOutPort.listarBilleteras()).thenReturn(billeteras);

        List<Billetera> resultado = billeteraService.listarBilleteras();

        assertEquals(billeteras.size(), resultado.size());
        for (int i = 0; i < resultado.size(); i++){
            assertEquals(billeteras.get(i), resultado.get(i));
        }
    }

    @Test
    void debeRetornarUnaListaVacia_cuandoListarBilleterasConListaVacia() throws Exception {
        when(billeteraOutPort.listarBilleteras()).thenReturn(Collections.emptyList());

        List<Billetera> resultado = billeteraService.listarBilleteras();
        assertEquals(resultado.size(), Collections.emptyList().size());
    }

    @Test
    void debeRetornarUnaBilletera_cuandoSeObtienePorId() throws Exception {
        when(billeteraOutPort.obtenerPorId(ID_1)).thenReturn(billetera);

        Billetera resultado = billeteraService.obtenerPorId(ID_1);

        assertEquals(billetera.getId(), resultado.getId());
        assertEquals(billetera.getMail(), resultado.getMail());
        assertEquals(billetera.getIdCuenta(), resultado.getIdCuenta());
        assertEquals(billetera.getDenominacion(), resultado.getDenominacion());
        assertEquals(billetera.isLiquidaEnByma(), resultado.isLiquidaEnByma());
        assertEquals(billetera.isHabilitado(), resultado.isHabilitado());
        assertEquals(billetera.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(billetera.getObservaciones(), resultado.getObservaciones());
        assertEquals(billetera.getIdAcdi(), resultado.getIdAcdi());
    }

    @Test
    void debeRetornarBilleteraNoEncontradoException_cuandoSeObtieneUnaBilleteraConIdInexistente() throws Exception {
        when(billeteraOutPort.obtenerPorId(ID_INEXISTENTE)).thenThrow(BilleteraNoEncontradoException.class);

        assertThrows(BilleteraNoEncontradoException.class, () -> billeteraService.obtenerPorId(ID_INEXISTENTE));
    }

    @Test
    void debeActualizarBilletera_cuandoActualizar() throws Exception {
        Billetera billeteraActualizada = Billetera.builder()
                .mail(EMAIL_2)
                .idCuenta(CUENTA_2)
                .denominacion(DENOMINACION_2)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_2)
                .idAcdi(ACDI_2)
                .build();

        when(billeteraOutPort.obtenerPorId(ID_1)).thenReturn(billetera);
        billetera.setDenominacion(DENOMINACION_2);
        billetera.setMail(EMAIL_2);
        billetera.setIdCuenta(CUENTA_2);
        billetera.setIdAcdi(ACDI_2);
        billetera.setObservaciones(OBSERVACIONES_2);
        when(billeteraOutPort.actualizar(billetera)).thenReturn(billetera);

        Billetera resultado = billeteraService.actualizar(billetera, ID_1);
        assertEquals(billetera.getId(), resultado.getId());
        assertEquals(billetera.getMail(), resultado.getMail());
        assertEquals(billetera.getIdCuenta(), resultado.getIdCuenta());
        assertEquals(billetera.getDenominacion(), resultado.getDenominacion());
        assertEquals(billetera.isLiquidaEnByma(), resultado.isLiquidaEnByma());
        assertEquals(billetera.isHabilitado(), resultado.isHabilitado());
        assertEquals(billetera.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(billetera.getObservaciones(), resultado.getObservaciones());
        assertEquals(billetera.getIdAcdi(), resultado.getIdAcdi());
    }

    @Test
    void debeRetornarBilleteraNoEncontradoException_cuandoActualizarUnaBilleteraInexistente() throws Exception {
        Billetera billeteraActualizada = Billetera.builder()
                .id(ID_INEXISTENTE)
                .mail(EMAIL_2)
                .idCuenta(CUENTA_2)
                .denominacion(DENOMINACION_2)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_2)
                .idAcdi(ACDI_2)
                .build();

        when(billeteraOutPort.actualizar(billeteraActualizada)).thenThrow(BilleteraNoEncontradoException.class);

        assertThrows(BilleteraNoEncontradoException.class, () -> billeteraService.actualizar(billeteraActualizada, ID_INEXISTENTE));
    }

    @Test
    void debeEliminarBilletera_cuandoEliminarBilletera() throws Exception {
        doNothing().when(billeteraOutPort).eliminarPorId(ID_1);
        billeteraService.eliminar(ID_1);

        verify(billeteraOutPort,times(1)).eliminarPorId(ID_1);
    }
}