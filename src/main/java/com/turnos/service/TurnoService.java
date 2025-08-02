package com.turnos.service;

import com.turnos.dto.TurnoDTO;
import com.turnos.mapper.TurnoMapper;
import com.turnos.model.Paciente;
import com.turnos.model.Profesional;
import com.turnos.model.Turno;
import com.turnos.repository.PacienteRepository;
import com.turnos.repository.ProfesionalRepository;
import com.turnos.repository.TurnoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private TurnoMapper turnoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TurnoService.class);

    public TurnoDTO agregarTurno(TurnoDTO turnoDTO) {


        Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + turnoDTO.getPacienteId()));

        Profesional profesional = profesionalRepository.findById(turnoDTO.getProfesionalId())
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado con id: " + turnoDTO.getProfesionalId()));

        Turno turno = turnoMapper.toEntity(turnoDTO);
        turno.setPaciente(paciente);
        turno.setProfesional(profesional);

        return turnoMapper.toDTO(turnoRepository.save(turno));
    }


    public Turno modificarTurno(Long id, TurnoDTO nuevoTurno) {
        Optional<Turno> turnoExistente = turnoRepository.findById(id);
        if (turnoExistente.isPresent()) {
            Turno turno = turnoExistente.get();
            // Actualiza los campos necesarios
            turno.setFecha(nuevoTurno.getFecha());
            //turno.setPaciente(nuevoTurno.getPaciente());
            // Otras actualizaciones según sea necesario
            return turnoRepository.save(turno);
        } else {
            return null; // Si no se encuentra el turno
        }
    }

    public void eliminarTurno(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById((long) id);
        if (turnoOptional.isPresent()) {
            turnoRepository.deleteById((long) id);
        } else {
            throw new RuntimeException("Turno no encontrado con id: " + id);
        }
    }

    public List<Turno> obtenerTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        for (Turno turno : turnos) {
               System.out.println(turno);
        }
        return turnos;
    }

    public Turno getTurnoById(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()){
            return turnoOptional.get();
        } else {
            throw new RuntimeException("Turno no encontrado con id: " + id);
        }
    }
}
