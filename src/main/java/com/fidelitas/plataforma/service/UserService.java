package com.fidelitas.plataforma.service;

import com.fidelitas.plataforma.domain.Rol;
import com.fidelitas.plataforma.domain.Usuario;
import com.fidelitas.plataforma.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Usuario guardar(Usuario usuario) {
        // Encriptar contraseña si es nueva o se está modificando
        if (usuario.getId() == null || !usuario.getPassword().startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        
        // Enviar correo solo si es un usuario nuevo
        if (usuario.getId() == null) {
            emailService.enviarCorreoConfirmacion(usuarioGuardado);
        }
        
        return usuarioGuardado;
    }
    
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        
        // Solo actualizar contraseña si se proporciona una nueva
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }
        
        usuarioExistente.setRol(usuarioActualizado.getRol());
        usuarioExistente.setActivo(usuarioActualizado.getActivo());
        
        return usuarioRepository.save(usuarioExistente);
    }
    
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    // Métodos para consultas avanzadas
    public List<Usuario> buscarPorRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }
    
    public List<Usuario> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return usuarioRepository.findByFechaCreacionBetween(fechaInicio, fechaFin);
    }
    
    public List<Usuario> buscarPorCoincidencia(String busqueda) {
        return usuarioRepository.findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
                busqueda, busqueda, busqueda);
    }
    
    public Long contarPorActivo(Boolean activo) {
        return usuarioRepository.countByActivo(activo);
    }
    
    public List<Usuario> listarOrdenadosPorFechaCreacion() {
        return usuarioRepository.findByOrderByFechaCreacionDesc();
    }
}

