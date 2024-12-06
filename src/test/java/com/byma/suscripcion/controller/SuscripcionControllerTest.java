package com.byma.suscripcion.controller;

import com.byma.emisor.application.port.in.SuscripcionInPort;
import com.byma.emisor.domain.model.SuscripcionModel;
import com.byma.emisor.exception_handler.GlobalExceptionHandler;
import com.byma.emisor.infrastructure.adapter.in.web.controller.impl.SuscripcionController;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.SuscripcionRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = SuscripcionController.class)
public class SuscripcionControllerTest {
    private final List<SuscripcionModel>suscripciones = new ArrayList<>();
    @Autowired
    private SuscripcionController suscripcionController;
    @MockBean
    private SuscripcionInPort suscripcionInPort;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(suscripcionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        suscripciones.clear();

        suscripciones.add(SuscripcionModel.builder()
                .idSuscripcion(1L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build());
        suscripciones.add(SuscripcionModel.builder()
                .idSuscripcion(2L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build());
    }

    @Test
    void shouldFetchAllSuscripciones() throws Exception {
        when(suscripcionInPort.listarSuscripciones()).thenReturn(suscripciones);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/suscripcion/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(suscripcionInPort, times(1)).listarSuscripciones();
    }
    @Test
    void shouldCreateSuscripcion() throws Exception {
        SuscripcionModel suscripcion = SuscripcionModel.builder()
                .idSuscripcion(3L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build();
        SuscripcionRequestDTO suscripcionRequestDTO = SuscripcionRequestDTO.builder()
                .cantCuotapartes(1)
                .command("command")
                .build();
        when(suscripcionInPort.crear(any(SuscripcionModel.class))).thenReturn(suscripcion);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/suscripcion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cantCuotapartes\": 1, \"command\": \"command\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.command").value("command"));

        verify(suscripcionInPort, times(1)).crear(any(SuscripcionModel.class));
    }
    @Test
    void shouldDeleteSuscripcion() throws Exception {
        doNothing().when(suscripcionInPort).eliminar(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/suscripcion/{id}", 1L))
                .andExpect(status().isNoContent());
        verify(suscripcionInPort, times(1)).eliminar(1L);
    }
    @Test
    void shouldUpdateSuscripcion() throws Exception {
        SuscripcionModel suscripcion = SuscripcionModel.builder()
                .idSuscripcion(1L)
                .fechaAlta(LocalDateTime.now())
                .cantCuotapartes(1)
                .command("command")
                .build();
        SuscripcionRequestDTO suscripcionRequestDTO = SuscripcionRequestDTO.builder()
                .cantCuotapartes(1)
                .command("command")
                .build();
        when(suscripcionInPort.actualizar(any(SuscripcionModel.class), eq(1L))).thenReturn(suscripcion);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/suscripcion/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cantCuotapartes\": 1, \"command\": \"command\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.command").value("command"));
        verify(suscripcionInPort, times(1)).actualizar(any(SuscripcionModel.class), eq(1L));
    }
    @Test
    void shouldFetchSuscripcionById() throws Exception {
        SuscripcionModel suscripcion = suscripciones.get(0);
        when(suscripcionInPort.obtenerPorId(1L)).thenReturn(suscripcion);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/suscripcion/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.command").value("command"));
        verify(suscripcionInPort, times(1)).obtenerPorId(1L);
    }

}
