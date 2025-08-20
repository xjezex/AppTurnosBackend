package com.turnos.service;

import com.turnos.dto.TurnoDTO;
import com.turnos.model.Turno;
import com.turnos.repository.TurnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito en JUnit 5
class TurnoServiceTest {

    @Mock
    private TurnoRepository turnoRepository; // Simulación

    @InjectMocks
    private TurnoService turnoService; ///Clase bajo prueba

    private Turno turno;

    @BeforeEach
    void setUp(){
        turno = new Turno();
        turno.setId(123L);
        turno.setFecha(LocalDate.parse("2025-08-25"));
        turno.setHora(LocalTime.parse("10:00"));
    }

   /* @Test
    void crearTurno_valido_guardaTurno() {
        when(turnoRepository.save(turno)).thenReturn(turno);

        TurnoDTO resultado = turnoService.agregarTurno(turno);

        assertNotNull(resultado);
        assertEquals(1L,resultado.getId());
        verify(turnoRepository, times(1)).save(turno);
    }*/
}