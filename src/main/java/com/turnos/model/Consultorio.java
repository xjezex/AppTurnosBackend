package com.turnos.model;

import jakarta.persistence.OneToMany;

import java.util.List;

public class Consultorio {
    private Long id;

    private String nombre; // Ej: "Consultorio 1"
    private String descripcion;

    private boolean activo;

    @OneToMany(mappedBy = "consultorio")
    private List<Turno> turnos;

    // Disponibilidad horaria semanal (embebida)
    private List<DisponibilidadHoraria> disponibilidadHoraria;

}
