package com.fidelitas.plataforma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReportesController {
    
    @GetMapping
    public String reportes(Model model) {
        // Aquí se pueden agregar datos para los reportes
        return "reportes/index";
    }
}

