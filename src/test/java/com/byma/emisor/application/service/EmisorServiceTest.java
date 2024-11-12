package com.byma.emisor.application.service;

import com.byma.emisor.application.exception.EmisorDuplicadoException;
import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.port.out.EmisorOutPort;
import com.byma.emisor.domain.model.Emisor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmisorServiceTest {
    @InjectMocks
    private EmisorService emisorService;

    @Mock
    private EmisorOutPort emisorOutPort;

    private List<Emisor> emisores = new ArrayList<>();

    private Emisor emisor;

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

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        emisor = Emisor.builder()
                .id(ID_1)
                .denominacion(DENOMINACION_1)
                .email(EMAIL_1)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_1)
                .idOrganizacion(ID_1)
                .idEntidadLegal(ID_1)
                .build();

        emisores.add(Emisor.builder()
                .id(ID_1)
                .denominacion(DENOMINACION_1)
                .email(EMAIL_1)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_1)
                .idOrganizacion(ID_1)
                .idEntidadLegal(ID_1)
                .build());

        emisores.add(Emisor.builder()
                .id(ID_2)
                .denominacion(DENOMINACION_2)
                .email(EMAIL_2)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_2)
                .idOrganizacion(ID_2)
                .idEntidadLegal(ID_2)
                .build());

        emisores.add(Emisor.builder()
                .id(ID_3)
                .denominacion(DENOMINACION_3)
                .email(EMAIL_3)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_3)
                .idOrganizacion(ID_3)
                .idEntidadLegal(ID_3)
                .build());
    }

    @Test
    void debeRetornarUnEmisor_cuandoSeCreaUnEmisor() throws Exception {
        when(emisorOutPort.crear(emisor)).thenReturn(emisor);

        Emisor resultado = emisorService.crear(emisor);
        assertEquals(emisor.getId(), resultado.getId());
        assertEquals(emisor.getDenominacion(), resultado.getDenominacion());
        assertEquals(emisor.getEmail(), resultado.getEmail());
        assertEquals(emisor.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(emisor.getCuentaEmisor(), resultado.getCuentaEmisor());
        assertEquals(emisor.getIdOrganizacion(), resultado.getIdOrganizacion());
        assertEquals(emisor.getIdEntidadLegal(), resultado.getIdEntidadLegal());
    }

    @Test
    void debeRetornarEmisorDuplicadoException_cuandoSeCreaUnEmisorConUnEmailYaExistente() throws Exception {
        when(emisorOutPort.existeEmisorPorEmailIgnorarMayusculas(emisor.getEmail())).thenReturn(true);

        assertThrows(EmisorDuplicadoException.class, () -> emisorService.crear(emisor));
    }

    @Test
    void debeListarTodosLosEmisores_cuandoListarEmisores() throws Exception {
        when(emisorOutPort.listarEmisores()).thenReturn(emisores);

        List<Emisor> resultado = emisorService.listarEmisores();

        assertEquals(emisores.size(), resultado.size());
        for (int i = 0; i < resultado.size(); i++){
            assertEquals(emisores.get(i), resultado.get(i));
        }
    }

    @Test
    void debeRetornarUnaListaVacia_cuandoListarEmisoresConListaVacia() {
        when(emisorOutPort.listarEmisores()).thenReturn(Collections.emptyList());

        List<Emisor> resultado = emisorService.listarEmisores();
        assertEquals(resultado.size(), Collections.emptyList().size());
    }

    @Test
    void debeRetornarUnEmisor_cuandoSeObtienePorId() throws Exception {
        when(emisorOutPort.obtenerPorId(ID_1)).thenReturn(Optional.ofNullable(emisor));

        Emisor resultado = emisorService.obtenerPorId(ID_1);

        assertEquals(emisor.getId(), resultado.getId());
        assertEquals(emisor.getDenominacion(), resultado.getDenominacion());
        assertEquals(emisor.getEmail(), resultado.getEmail());
        assertEquals(emisor.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(emisor.getCuentaEmisor(), resultado.getCuentaEmisor());
        assertEquals(emisor.getIdOrganizacion(), resultado.getIdOrganizacion());
        assertEquals(emisor.getIdEntidadLegal(), resultado.getIdEntidadLegal());
    }

    @Test
    void debeRetornarEmisorNoEncotradoException_cuandoSeObtieneUnEmisorConIdInexistente() throws Exception {
        when(emisorOutPort.obtenerPorId(ID_INEXISTENTE)).thenReturn(Optional.empty());

        assertThrows(EmisorNoEncontradoException.class, () -> emisorService.obtenerPorId(ID_INEXISTENTE));
    }

    @Test
    void debeActualizarEmisor_cuandoActualizar() throws Exception {
        Emisor emisorActualizado = Emisor.builder()
                .denominacion(DENOMINACION_2)
                .email(EMAIL_2)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_2)
                .idOrganizacion(ID_2)
                .idEntidadLegal(ID_2)
                .build();

        when(emisorOutPort.obtenerPorId(ID_1)).thenReturn(Optional.ofNullable(emisor));
        emisor.setDenominacion(DENOMINACION_2);
        emisor.setEmail(EMAIL_2);
        emisor.setCuentaEmisor(CUENTA_2);
        emisor.setIdOrganizacion(ID_2);
        emisor.setIdEntidadLegal(ID_2);
        when(emisorOutPort.actualizar(emisor)).thenReturn(emisor);

        Emisor resultado = emisorService.actualizar(emisorActualizado, ID_1);
        assertEquals(emisor.getId(), resultado.getId());
        assertEquals(emisor.getDenominacion(), resultado.getDenominacion());
        assertEquals(emisor.getEmail(), resultado.getEmail());
        assertEquals(emisor.getFechaAlta(), resultado.getFechaAlta());
        assertEquals(emisor.getCuentaEmisor(), resultado.getCuentaEmisor());
        assertEquals(emisor.getIdOrganizacion(), resultado.getIdOrganizacion());
        assertEquals(emisor.getIdEntidadLegal(), resultado.getIdEntidadLegal());
    }

    @Test
    void debeRetornarEmisorDuplicadoException_cuandoActualizarConEmailYaExistente() throws Exception {
        Emisor emisorActualizado = Emisor.builder()
                .denominacion(DENOMINACION_2)
                .email(EMAIL_1)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_2)
                .idOrganizacion(ID_2)
                .idEntidadLegal(ID_2)
                .build();

        when(emisorOutPort.obtenerPorId(ID_1)).thenReturn(Optional.ofNullable(emisor));
        when(emisorOutPort.existeEmisorPorEmailIgnorarMayusculas(emisorActualizado.getEmail())).thenReturn(true);

        assertThrows(EmisorDuplicadoException.class, () -> emisorService.actualizar(emisorActualizado, ID_1));
    }

    @Test
    void debeRetornarEmisorNoEncontradoException_cuandoActualizarUnEmisorInexistente() throws Exception {
        Emisor emisorActualizado = Emisor.builder()
                .denominacion(DENOMINACION_2)
                .email(EMAIL_2)
                .fechaAlta(LocalDateTime.now())
                .cuentaEmisor(CUENTA_2)
                .idOrganizacion(ID_2)
                .idEntidadLegal(ID_2)
                .build();

        when(emisorOutPort.obtenerPorId(ID_1)).thenReturn(Optional.empty());

        assertThrows(EmisorNoEncontradoException.class, () -> emisorService.actualizar(emisorActualizado, ID_INEXISTENTE));
    }

    @Test
    void debeEliminarEmisor_cuandoEliminarEmisor() throws Exception {
        when(emisorOutPort.obtenerPorId(ID_1)).thenReturn(Optional.ofNullable(emisor));
        doNothing().when(emisorOutPort).eliminarPorId(ID_1);
        emisorService.eliminar(ID_1);

        verify(emisorOutPort,times(1)).eliminarPorId(ID_1);
    }

    @Test
    void debeRetornarEmisorNoEncontradoException_cuandoEliminarUnEmisorInexistente() throws Exception {
        when(emisorOutPort.obtenerPorId(ID_1)).thenReturn(Optional.empty());

        assertThrows(EmisorNoEncontradoException.class, () -> emisorService.eliminar(ID_INEXISTENTE));
    }
}