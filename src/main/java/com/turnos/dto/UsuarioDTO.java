package com.turnos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El usuario no puede estar vacío")
    private String username;

    @NotBlank(message = "El correo no puede estar vací0")
    private String email;

    private boolean enabled;

    @NotBlank(message = "El rol no puede estar vacío")
    private String rol; // Por ejemplo: "ADMIN", "USER", etc.
}
