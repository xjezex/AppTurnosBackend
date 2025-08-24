package com.turnos.service;

import com.turnos.model.Paciente;
import com.turnos.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PacienteServiceTest {

    @Mock
    PacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService;

    private Paciente paciente;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this); // inicializa mocks
        paciente = new Paciente();
        paciente.setId(123L);
        paciente.setNombre("Juan");
        paciente.setApellido("Topo");
        paciente.setTelefono("221-1234567");
        paciente.setCorreo("juantopo@gmail.com");
        paciente.setObservaciones("Sin Observaciones");
    }

    @Test
    void testAgregarPaciente_ok(){
        when(pacienteRepository.save(paciente)).thenReturn(paciente);;

       Paciente result = pacienteService.agregarPaciente(paciente);

       assertNotNull(result);
       assertEquals(paciente.getId(), result.getId());
       assertEquals(paciente.getNombre(), result.getNombre());
       assertEquals(paciente.getApellido(), result.getApellido());
       assertEquals(paciente.getTelefono(), result.getTelefono());
       assertEquals(paciente.getCorreo(), result.getCorreo());
       assertEquals(paciente.getObservaciones(), result.getObservaciones());
       verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testEliminarPaciente_ok(){
        // Simulamos que el paciente existe
        when(pacienteRepository.findById(paciente.getId())).thenReturn(Optional.of(paciente));

        pacienteService.eliminarPaciente(paciente.getId());

        // Verificamos que realmente se llamó a deleteById
        verify(pacienteRepository, times(1)).deleteById(paciente.getId());
    }

    @Test
    void testEliminarPaciente_noExiste() {
        Long idInexistente = 999L;
        when(pacienteRepository.findById(idInexistente)).thenReturn(Optional.empty());

        try {
            pacienteService.eliminarPaciente(idInexistente);
        } catch (RuntimeException e) {
            assertEquals("Paciente no encontrado con id: " + idInexistente, e.getMessage());
        }

        verify(pacienteRepository, never()).deleteById(idInexistente);
    }

    @Test
    void testModificarPaciente_ok() {
        when(pacienteRepository.findById(paciente.getId())).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setNombre("Juan Modificado");
        nuevoPaciente.setApellido("Topo Modificado");
        nuevoPaciente.setTelefono("221-7654321");
        nuevoPaciente.setCorreo("juan@correo.com");
        nuevoPaciente.setObservaciones("Paciente actualizado");

        // Act (ejecutar método)
        Paciente resultado = pacienteService.modificarPaciente(paciente.getId(), nuevoPaciente);

        // Assert (verificar resultados)
        assertNotNull(resultado);
        assertEquals("Juan Modificado", resultado.getNombre());
        assertEquals("Topo Modificado", resultado.getApellido());
        assertEquals("221-7654321", resultado.getTelefono());
        assertEquals("juan@correo.com", resultado.getCorreo());
        assertEquals("Paciente actualizado", resultado.getObservaciones());

        // Verifica que se haya llamado al repository
        verify(pacienteRepository, times(1)).findById(paciente.getId());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testObtenerPacientes_ok(){
        pacienteService.obtenerPacientes();
        verify(pacienteRepository, times(1)).findAll();
    }





}
