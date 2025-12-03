package com.fidelitas.plataforma.controllers;

import com.fidelitas.plataforma.domain.Rol;
import com.fidelitas.plataforma.domain.Usuario;
import com.fidelitas.plataforma.service.RolService;
import com.fidelitas.plataforma.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RolService rolService;
    
    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = userService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listar";
    }
    
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        Usuario usuario = new Usuario();
        List<Rol> roles = rolService.listarTodos();
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);
        return "usuarios/crear";
    }
    
    @PostMapping("/crear")
    public String crear(@Valid @ModelAttribute Usuario usuario, 
                       BindingResult result, 
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Rol> roles = rolService.listarTodos();
            model.addAttribute("roles", roles);
            return "usuarios/crear";
        }
        
        if (userService.existePorEmail(usuario.getEmail())) {
            result.rejectValue("email", "error.email", "Este correo electrónico ya está registrado");
            List<Rol> roles = rolService.listarTodos();
            model.addAttribute("roles", roles);
            return "usuarios/crear";
        }
        
        userService.guardar(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente");
        return "redirect:/usuarios";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = userService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Rol> roles = rolService.listarTodos();
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);
        return "usuarios/editar";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                        @Valid @ModelAttribute Usuario usuario,
                        BindingResult result,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<Rol> roles = rolService.listarTodos();
            model.addAttribute("roles", roles);
            return "usuarios/editar";
        }
        
        // Verificar si el email ya existe en otro usuario
        Usuario usuarioExistente = userService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!usuarioExistente.getEmail().equals(usuario.getEmail()) && 
            userService.existePorEmail(usuario.getEmail())) {
            result.rejectValue("email", "error.email", "Este correo electrónico ya está registrado");
            List<Rol> roles = rolService.listarTodos();
            model.addAttribute("roles", roles);
            return "usuarios/editar";
        }
        
        userService.actualizar(id, usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
        return "redirect:/usuarios";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Usuario usuario = userService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "usuarios/detalle";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        return "redirect:/usuarios";
    }
}

