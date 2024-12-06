package com.byma.billetera.controller;

import com.byma.emisor.application.exception.billetera.BilleteraNoEncontradoException;
import com.byma.emisor.application.port.in.BilleteraInPort;
import com.byma.emisor.domain.model.Billetera;
import com.byma.emisor.exception_handler.GlobalExceptionHandler;
import com.byma.emisor.infrastructure.adapter.in.web.controller.impl.BilleteraController;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.BilleteraRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BilleteraControllerTest {
    private final List<Billetera> billeteras = new ArrayList<>();
    private Billetera billetera;
    private BilleteraRequestDto billeteraRequestDto;

    @InjectMocks
    private BilleteraController billeteraController;

    @Mock
    private BilleteraInPort billeteraInPort;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    public static final String ERROR_VALIDACION = "Los datos enviados no cumplen con los criterios de validacion.";

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

    public static final String API_URL = "/api/v1/billeteras";
    public static final String API_URL_CON_ID = "/api/v1/billeteras/{id}";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(billeteraController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();

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

        billeteraRequestDto = BilleteraRequestDto.builder()
                .mail(EMAIL_1)
                .idCuenta(CUENTA_1)
                .denominacion(DENOMINACION_1)
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones(OBSERVACIONES_1)
                .idAcdi(ACDI_1)
                .build();

        billeteras.clear();
        billeteras.add(new Billetera(
                ID_1,
                EMAIL_1,
                CUENTA_1,
                DENOMINACION_1,
                true,
                true,
                LocalDateTime.now(),
                OBSERVACIONES_1,
                ACDI_1));
        billeteras.add(new Billetera(
                ID_2,
                EMAIL_2,
                CUENTA_2,
                DENOMINACION_2,
                true,
                true,
                LocalDateTime.now(),
                OBSERVACIONES_2,
                ACDI_2));
        billeteras.add(new Billetera(
                ID_3,
                EMAIL_3,
                CUENTA_3,
                DENOMINACION_3,
                true,
                true,
                LocalDateTime.now(),
                OBSERVACIONES_3,
                ACDI_3));
    }

    @Test
    void deberiaCrearBilletera() throws Exception {
        when(billeteraInPort.crear(any(Billetera.class))).thenReturn(billetera);

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billeteraRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID_1))
                .andExpect(jsonPath("$.idCuenta").value(CUENTA_1))
                .andExpect(jsonPath("$.idAcdi").value(ACDI_1));
    }

    @Test
    void deberiaDevolverError400CuandoCrearBilleteraEsInvalido() throws Exception {
        billeteraRequestDto.setMail("");

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billeteraRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deberiaActualizarBilleteraExitosamenteCuandoBilleteraEsValido() throws Exception {
        BilleteraRequestDto billeteraRequestDto = BilleteraRequestDto.builder()
                .mail(EMAIL_2)
                .idCuenta(CUENTA_2)
                .denominacion(DENOMINACION_2)
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones(OBSERVACIONES_2)
                .idAcdi(ACDI_2)
                .build();

        Billetera billeteraActualizada = Billetera.builder()
                .id(ID_1)
                .mail(EMAIL_2)
                .idCuenta(CUENTA_2)
                .denominacion(DENOMINACION_2)
                .liquidaEnByma(true)
                .habilitado(true)
                .fechaAlta(LocalDateTime.now())
                .observaciones(OBSERVACIONES_2)
                .idAcdi(ACDI_2)
                .build();

        when(billeteraInPort.actualizar(any(), eq(ID_1))).thenReturn(billeteraActualizada);

        mockMvc.perform(put(API_URL_CON_ID, ID_1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billeteraRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_1))
                .andExpect(jsonPath("$.mail").value(EMAIL_2));
    }

    @Test
    void deberiaDevolverStatus404CuandoBilleteraNoExisteParaActualizar() throws Exception {
        BilleteraRequestDto billeteraRequestDto = BilleteraRequestDto.builder()
                .mail(EMAIL_2)
                .idCuenta(CUENTA_2)
                .denominacion(DENOMINACION_2)
                .liquidaEnByma(true)
                .habilitado(true)
                .observaciones(OBSERVACIONES_2)
                .idAcdi(ACDI_2)
                .build();

        when(billeteraInPort.actualizar(any(), eq(ID_INEXISTENTE))).thenThrow(new BilleteraNoEncontradoException());

        mockMvc.perform(put(API_URL_CON_ID, ID_INEXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billeteraRequestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deberiaObtenerTodasLasBilleterasExitosamente() throws Exception {
        when(billeteraInPort.listarBilleteras()).thenReturn(billeteras);

        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].mail").value(EMAIL_1));
    }

    @Test
    void deberiaDevolverListaVaciaCuandoNoExistenBilleteras() throws Exception {
        when(billeteraInPort.listarBilleteras()).thenReturn(Arrays.asList());

        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void deberiaObtenerBilleteraPorIdCuandoExiste() throws Exception {
        when(billeteraInPort.obtenerPorId(ID_1)).thenReturn(billetera);

        mockMvc.perform(get(API_URL_CON_ID, ID_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_1))
                .andExpect(jsonPath("$.mail").value(EMAIL_1));
    }

    @Test
    void deberiaDevolverStatus404CuandoNoSeEncuentraBilleteraPorId() throws Exception {
        when(billeteraInPort.obtenerPorId(ID_1)).thenThrow(new BilleteraNoEncontradoException());

        mockMvc.perform(get(API_URL_CON_ID, ID_1))
                .andExpect(status().isNotFound());
    }

    @Test
    void deberiaEliminarBilleteraExitosamente() throws Exception {
        doNothing().when(billeteraInPort).eliminar(ID_1);

        mockMvc.perform(delete(API_URL_CON_ID, ID_1))
                .andExpect(status().isNoContent());
    }

    @Test
    void deberiaDevolverStatus404CuandoBilleteraNoExisteParaEliminar() throws Exception {
        doThrow(new BilleteraNoEncontradoException()).when(billeteraInPort).eliminar(ID_1);

        mockMvc.perform(delete(API_URL_CON_ID, ID_1))
                .andExpect(status().isNotFound());
    }
}
