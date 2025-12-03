package com.fidelitas.plataforma.controllers;

import com.fidelitas.plataforma.domain.Rol;
import com.fidelitas.plataforma.domain.Usuario;
import com.fidelitas.plataforma.service.RolService;
import com.fidelitas.plataforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RolService rolService;
    
    @GetMapping
    public String consultas(Model model) {
        // Consulta 1: Usuarios por rol
        List<Rol> roles = rolService.listarTodos();
        model.addAttribute("roles", roles);
        
        // Consulta 2: Contar usuarios activos vs inactivos
        Long usuariosActivos = userService.contarPorActivo(true);
        Long usuariosInactivos = userService.contarPorActivo(false);
        model.addAttribute("usuariosActivos", usuariosActivos);
        model.addAttribute("usuariosInactivos", usuariosInactivos);
        
        // Consulta 3: Usuarios ordenados por fecha de creación
        List<Usuario> usuariosOrdenados = userService.listarOrdenadosPorFechaCreacion();
        model.addAttribute("usuariosOrdenados", usuariosOrdenados);
        
        return "consultas/index";
    }
    
    @GetMapping("/por-rol")
    public String consultarPorRol(@RequestParam Long rolId, Model model) {
        Rol rol = rolService.buscarPorId(rolId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        List<Usuario> usuarios = userService.buscarPorRol(rol);
        model.addAttribute("rol", rol);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", rolService.listarTodos());
        return "consultas/por-rol";
    }
    
    @GetMapping("/por-fechas")
    public String consultarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            Model model) {
        List<Usuario> usuarios = userService.buscarPorRangoFechas(fechaInicio, fechaFin);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);
        return "consultas/por-fechas";
    }
    
    @GetMapping("/buscar")
    public String buscar(@RequestParam String busqueda, Model model) {
        List<Usuario> usuarios = userService.buscarPorCoincidencia(busqueda);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("busqueda", busqueda);
        return "consultas/buscar";
    }
}

