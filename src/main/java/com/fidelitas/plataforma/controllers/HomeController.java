package com.fidelitas.plataforma.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        
        // Redirigir según el rol del usuario
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/usuarios";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROFESOR"))) {
            return "redirect:/reportes";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"))) {
            return "redirect:/perfil";
        }
        
        return "redirect:/login";
    }
    
    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}

