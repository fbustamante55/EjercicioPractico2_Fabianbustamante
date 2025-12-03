package com.fidelitas.plataforma.controllers;

import com.fidelitas.plataforma.domain.Usuario;
import com.fidelitas.plataforma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String verPerfil(Authentication authentication, Model model) {
        String email = authentication.getName();
        Usuario usuario = userService.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "perfil/ver";
    }
}

