# Ejercicio Práctico 2: Gestión de Usuarios y Roles para una Plataforma Académica

Sistema web desarrollado con Spring Boot para la gestión de usuarios y roles en una plataforma académica. Incluye autenticación, autorización basada en roles, consultas avanzadas y reportes.

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **MySQL** - Base de datos
- **Thymeleaf** - Motor de plantillas
- **Spring Mail** - Envío de correos electrónicos
- **Maven** - Gestión de dependencias

## ✨ Características Principales

### Gestión de Usuarios
- ✅ Crear, editar, eliminar y listar usuarios
- ✅ Visualización de detalles de usuario
- ✅ Validación de datos (email único, campos obligatorios)
- ✅ Asignación de roles a usuarios
- ✅ Control de estado activo/inactivo
- ✅ Registro automático de fecha de creación

### Gestión de Roles
- ✅ Crear, editar y listar roles
- ✅ Asignación de descripción a roles
- ✅ Validación de nombres únicos

### Autenticación y Seguridad
- ✅ Sistema de login seguro
- ✅ Control de acceso basado en roles
- ✅ Páginas de acceso denegado
- ✅ Protección de rutas sensibles

### Consultas Avanzadas
- ✅ Búsqueda de usuarios por rol
- ✅ Consulta de usuarios por rango de fechas
- ✅ Búsqueda por coincidencia (nombre, apellido, email)
- ✅ Listado de usuarios ordenados por fecha de creación
- ✅ Estadísticas de usuarios activos vs inactivos

### Reportes
- ✅ Módulo de reportes para análisis de datos

### Perfil de Usuario
- ✅ Visualización del perfil del usuario autenticado

## 📋 Requisitos Previos

- Java JDK 17 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 🔧 Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd EjercicioPractico2_Fabianbustamante
   ```

2. **Configurar la base de datos**
   - Crear una base de datos MySQL llamada `plataforma`
   - Ejecutar el script SQL ubicado en `src/main/resources/sql/plataforma.sql` (si existe)
   - O dejar que Hibernate cree las tablas automáticamente con `spring.jpa.hibernate.ddl-auto=update`

3. **Configurar las propiedades de la aplicación**
   
   Editar el archivo `src/main/resources/application.properties`:
   ```properties
   # Configuración de MySQL
   spring.datasource.url=jdbc:mysql://localhost:3306/plataforma?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=tu-usuario-mysql
   spring.datasource.password=tu-contraseña-mysql
   
   # Configuración de Spring Mail (opcional)
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=tu-email@gmail.com
   spring.mail.password=tu-password
   ```

4. **Compilar el proyecto**
   ```bash
   mvn clean install
   ```

5. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```
   
   O ejecutar la clase principal `EjercicioPractico2Application.java` desde tu IDE.

6. **Acceder a la aplicación**
   - La aplicación estará disponible en: `http://localhost:78`
   - (El puerto puede variar según tu configuración)

## 📁 Estructura del Proyecto

```
EjercicioPractico2_Fabianbustamante/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/fidelitas/plataforma/
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java          # Configuración de seguridad
│   │   │       ├── controllers/
│   │   │       │   ├── AuthController.java          # Controlador de autenticación
│   │   │       │   ├── UsuarioController.java       # CRUD de usuarios
│   │   │       │   ├── RolController.java           # CRUD de roles
│   │   │       │   ├── ConsultasController.java     # Consultas avanzadas
│   │   │       │   ├── ReportesController.java      # Reportes
│   │   │       │   ├── PerfilController.java        # Perfil de usuario
│   │   │       │   └── HomeController.java          # Página principal
│   │   │       ├── domain/
│   │   │       │   ├── Usuario.java                 # Entidad Usuario
│   │   │       │   └── Rol.java                     # Entidad Rol
│   │   │       ├── repository/
│   │   │       │   ├── UsuarioRepository.java       # Repositorio de usuarios
│   │   │       │   └── RolRepository.java           # Repositorio de roles
│   │   │       ├── service/
│   │   │       │   ├── UserService.java             # Lógica de negocio usuarios
│   │   │       │   ├── RolService.java              # Lógica de negocio roles
│   │   │       │   ├── EmailService.java            # Servicio de correo
│   │   │       │   └── UserDetailsServiceImpl.java  # Implementación de UserDetails
│   │   │       └── EjercicioPractico2Application.java
│   │   └── resources/
│   │       ├── application.properties               # Configuración de la aplicación
│   │       ├── data.sql                             # Datos iniciales (opcional)
│   │       ├── sql/
│   │       │   └── plataforma.sql                   # Script SQL
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── style.css                    # Estilos CSS
│   │       └── templates/
│   │           ├── login.html                       # Página de login
│   │           ├── acceso-denegado.html            # Página de acceso denegado
│   │           ├── fragments/                      # Fragmentos reutilizables
│   │           ├── layout/                         # Layouts base
│   │           ├── usuarios/                       # Vistas de usuarios
│   │           ├── roles/                          # Vistas de roles
│   │           ├── consultas/                      # Vistas de consultas
│   │           ├── reportes/                       # Vistas de reportes
│   │           └── perfil/                         # Vistas de perfil
├── pom.xml                                          # Configuración de Maven
└── README.md                                        # Este archivo
```

## 🔐 Seguridad

La aplicación utiliza Spring Security para:
- Autenticación de usuarios mediante formulario de login
- Autorización basada en roles
- Protección de rutas según permisos
- Encriptación de contraseñas (BCrypt)

## 📝 Notas Importantes

- Las contraseñas deben tener al menos 6 caracteres
- El email debe ser único en el sistema
- Los usuarios pueden estar activos o inactivos
- La fecha de creación se asigna automáticamente al crear un usuario

## 👤 Autor

**Fabian Bustamante**

## 📄 Licencia

Este proyecto es parte de un ejercicio práctico académico.

---

Para más información o soporte, contactar al desarrollador.
