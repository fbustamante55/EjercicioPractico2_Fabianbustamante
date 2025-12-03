package com.fidelitas.plataforma.controllers;

import com.fidelitas.plataforma.domain.Rol;
import com.fidelitas.plataforma.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RolController {
    
    @Autowired
    private RolService rolService;
    
    @GetMapping
    public String listar(Model model) {
        List<Rol> roles = rolService.listarTodos();
        model.addAttribute("roles", roles);
        return "roles/listar";
    }
    
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        Rol rol = new Rol();
        model.addAttribute("rol", rol);
        return "roles/crear";
    }
    
    @PostMapping("/crear")
    public String crear(@Valid @ModelAttribute Rol rol,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "roles/crear";
        }
        
        if (rolService.existePorNombre(rol.getNombre())) {
            result.rejectValue("nombre", "error.nombre", "Este rol ya existe");
            return "roles/crear";
        }
        
        rolService.guardar(rol);
        redirectAttributes.addFlashAttribute("mensaje", "Rol creado exitosamente");
        return "redirect:/roles";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Rol rol = rolService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        model.addAttribute("rol", rol);
        return "roles/editar";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                        @Valid @ModelAttribute Rol rol,
                        BindingResult result,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "roles/editar";
        }
        
        // Verificar si el nombre ya existe en otro rol
        Rol rolExistente = rolService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        
        if (!rolExistente.getNombre().equals(rol.getNombre()) && 
            rolService.existePorNombre(rol.getNombre())) {
            result.rejectValue("nombre", "error.nombre", "Este rol ya existe");
            return "roles/editar";
        }
        
        rolService.guardar(rol);
        redirectAttributes.addFlashAttribute("mensaje", "Rol actualizado exitosamente");
        return "redirect:/roles";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        rolService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Rol eliminado exitosamente");
        return "redirect:/roles";
    }
}

