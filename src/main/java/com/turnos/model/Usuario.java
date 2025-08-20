package com.turnos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

//flujo de desarrollo: Modelo → Repositorio → DTOs → Servicio → Configuración de seguridad → Controlador → JWT → Tests

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // nombre de usuario

    @Column(nullable = false)
    private String password; // contraseña (guardada encriptada)

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean enabled = true; // si está activo

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "rol")
    private Set<String> role = new HashSet<>(); // ejemplo: ROLE_USER, ROLE_ADMIN
}
