-- Script de inicialización de datos
-- Este script se ejecuta automáticamente al iniciar la aplicación si spring.jpa.hibernate.ddl-auto=create o create-drop
-- NOTA: Si usas el script SQL manual (plataforma.sql), este archivo no es necesario

-- Insertar roles iniciales (solo si no existen)
INSERT IGNORE INTO rol (nombre, descripcion) VALUES ('ADMIN', 'Administrador del sistema');
INSERT IGNORE INTO rol (nombre, descripcion) VALUES ('PROFESOR', 'Usuario con permisos de consulta');
INSERT IGNORE INTO rol (nombre, descripcion) VALUES ('ESTUDIANTE', 'Acceso limitado a información personal');

-- Nota: Las contraseñas deben estar encriptadas con BCrypt para funcionar con Spring Security
-- El script plataforma.sql incluye usuarios de ejemplo con contraseñas en texto plano (12345)
-- Para que funcionen, necesitarás encriptarlas o usar el script SQL manual y luego actualizar las contraseñas

