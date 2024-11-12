package com.byma.emisor.infrastructure.adapter.in.web.controller.impl;

import com.byma.emisor.application.exception.EmisorNoEncontradoException;
import com.byma.emisor.application.port.in.EmisorInPort;
import com.byma.emisor.domain.model.Emisor;
import com.byma.emisor.exception_handler.GlobalExceptionHandler;
import com.byma.emisor.infrastructure.adapter.in.web.dto.request.EmisorRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmisorController.class)
class EmisorControllerTest {

    private final List<Emisor> emisores = new ArrayList<>();
    @Autowired
    private EmisorController emisorController;
    @MockBean
    private EmisorInPort emisorInPort;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(emisorController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        emisores.clear();
        emisores.add(new Emisor(1L, "Empresa A", "empresaA@example.com", LocalDateTime.now(), "CTA-12345", 100L, 200L));
        emisores.add(new Emisor(2L, "Empresa B", "empresaB@example.com", LocalDateTime.now(), "CTA-67890", 101L, 201L));
    }

    @Test
    void shouldFetchAllEmisores() throws Exception {
        when(emisorInPort.listarEmisores()).thenReturn(emisores);

        mockMvc.perform(get("/api/emisores/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(emisorInPort, times(1)).listarEmisores();
    }

    @Test
    void shouldCreateEmisor() throws Exception {
        Emisor emisor = new Emisor(3L, "Empresa C", "empresaC@example.com", LocalDateTime.now(), "CTA-99999", 102L, 202L);
        EmisorRequestDTO emisorRequestDTO = new EmisorRequestDTO("Empresa C", "empresaC@example.com", LocalDateTime.now(), "CTA-99999", 102L, 202L);


        when(emisorInPort.crear(any(Emisor.class))).thenReturn(emisor);

        mockMvc.perform(post("/api/emisores/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"denominacion\": \"Empresa C\", \"email\": \"empresaC@example.com\", \"cuentaEmisor\": \"CTA-99999\", \"idOrganizacion\": 102, \"idEntidadLegal\": 202}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.denominacion").value("Empresa C"));

        verify(emisorInPort, times(1)).crear(ArgumentMatchers.any(Emisor.class));
    }

    @Test
    void shouldFetchEmisorById() throws Exception {
        Emisor emisor = emisores.get(0);
        when(emisorInPort.obtenerPorId(1L)).thenReturn(emisor);

        mockMvc.perform(get("/api/emisores/{idEmisor}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.denominacion").value("Empresa A"));

        verify(emisorInPort, times(1)).obtenerPorId(1L);
    }

    @Test
    void shouldUpdateEmisor() throws Exception {
        Emisor updatedEmisor = new Emisor(1L, "Empresa A Actualizada", "empresaA@example.com", LocalDateTime.now(), "CTA-12345", 100L, 200L);

        when(emisorInPort.actualizar(any(Emisor.class), eq(1L))).thenReturn(updatedEmisor);

        mockMvc.perform(put("/api/emisores/{idEmisor}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"denominacion\": \"Empresa A Actualizada\", \"email\": \"empresaA@example.com\", \"cuentaEmisor\": \"CTA-12345\", \"idOrganizacion\": 100, \"idEntidadLegal\": 200}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.denominacion").value("Empresa A Actualizada"));

        verify(emisorInPort, times(1)).actualizar(any(Emisor.class), eq(1L));
    }

    @Test
    void shouldDeleteEmisor() throws Exception {
        doNothing().when(emisorInPort).eliminar(1L);

        mockMvc.perform(delete("/api/emisores/{idEmisor}", 1L))
                .andExpect(status().isNoContent());

        verify(emisorInPort, times(1)).eliminar(1L);
    }

    @Test
    void shouldReturnNotFound_whenEmisorToDeleteDoesNotExist() throws Exception {
        doThrow(new EmisorNoEncontradoException()).when(emisorInPort).eliminar(99L);

        mockMvc.perform(delete("/api/emisores/{idEmisor}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Emisor no encontrado."));

        verify(emisorInPort, times(1)).eliminar(99L);
    }

}
