package com.turnos.repository;

import com.turnos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
