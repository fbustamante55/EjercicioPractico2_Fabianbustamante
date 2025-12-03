package com.fidelitas.plataforma.service;

import com.fidelitas.plataforma.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void enviarCorreoConfirmacion(Usuario usuario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(usuario.getEmail());
            mensaje.setSubject("Bienvenido a la Plataforma Académica");
            mensaje.setText("Hola " + usuario.getNombre() + " " + usuario.getApellido() + ",\n\n" +
                    "Tu cuenta ha sido creada exitosamente en la Plataforma Académica.\n\n" +
                    "Detalles de tu cuenta:\n" +
                    "Email: " + usuario.getEmail() + "\n" +
                    "Rol: " + usuario.getRol().getNombre() + "\n\n" +
                    "¡Bienvenido!");
            
            mailSender.send(mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
        }
    }
}

