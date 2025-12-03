package com.fidelitas.plataforma.repository;

import com.fidelitas.plataforma.domain.Rol;
import com.fidelitas.plataforma.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    // Consulta 1: Buscar usuarios por rol
    List<Usuario> findByRol(Rol rol);
    
    // Consulta 2: Buscar usuarios creados en un rango de fechas
    List<Usuario> findByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Consulta 3: Buscar usuarios por coincidencia parcial en correo o nombre
    List<Usuario> findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
            String email, String nombre, String apellido);
    
    // Consulta 4: Contar usuarios activos vs inactivos
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.activo = :activo")
    Long countByActivo(@Param("activo") Boolean activo);
    
    // Consulta 5: Obtener usuarios ordenados por fecha de creación
    List<Usuario> findByOrderByFechaCreacionDesc();
    
    // Consulta adicional: Buscar usuarios por rol y estado activo
    List<Usuario> findByRolAndActivo(Rol rol, Boolean activo);
}

