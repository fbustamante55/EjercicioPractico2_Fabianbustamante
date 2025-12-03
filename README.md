# Ejercicio Práctico 2: Gestión de Usuarios y Roles para una Plataforma Académica

## Descripción
Aplicación web desarrollada con Spring Boot para la gestión de usuarios y roles en una plataforma académica.

## Tecnologías Utilizadas
- **Spring Boot 3.2.0**
- **Spring Security** - Control de acceso basado en roles
- **Spring Data JPA** - Persistencia de datos
- **MySQL** - Base de datos
- **Thymeleaf** - Motor de plantillas
- **Spring Mail** - Envío de correos electrónicos
- **Bootstrap 5** - Framework CSS
- **Font Awesome** - Iconos

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/com/fidelitas/plataforma/
│   │   ├── domain/          # Entidades (Usuario, Rol)
│   │   ├── repository/       # Repositorios JPA
│   │   ├── service/          # Lógica de negocio
│   │   ├── controllers/      # Controladores web
│   │   ├── config/           # Configuraciones (Security, Mail)
│   │   └── EjercicioPractico2Application.java
│   └── resources/
│       ├── templates/        # Plantillas Thymeleaf
│       ├── static/css/       # Estilos CSS
│       ├── sql/              # Scripts SQL
│       └── application.properties
```

## Configuración

### 1. Base de Datos MySQL

**Opción A: Usar el script SQL proporcionado (Recomendado)**
```bash
# Ejecutar el script SQL en MySQL
mysql -u root -p < src/main/resources/sql/plataforma.sql
```

El script `plataforma.sql` crea:
- La base de datos `plataforma`
- Las tablas `rol` y `usuario` con sus relaciones
- Datos de ejemplo (3 roles y 3 usuarios de prueba)

**Nota importante sobre las contraseñas:**
Los usuarios de ejemplo en el script tienen contraseñas en texto plano (`12345`). Para que funcionen con Spring Security, necesitas:
1. Ejecutar el script SQL
2. Iniciar la aplicación
3. Las contraseñas se encriptarán automáticamente al iniciar sesión, o puedes actualizarlas manualmente desde la interfaz web

**Opción B: Crear la base de datos manualmente**
```sql
CREATE DATABASE plataforma;
```

### 2. Configurar application.properties
Editar `src/main/resources/application.properties` con tus credenciales:
```properties
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 3. Configurar Spring Mail
Editar las propiedades de correo en `application.properties`:
```properties
spring.mail.username=tu-email@gmail.com
spring.mail.password=tu-password
```

## Funcionalidades

### CRUD de Usuarios
- Listar usuarios
- Crear usuarios (con asignación de roles)
- Editar usuarios
- Eliminar usuarios
- Ver detalle de usuario
- Envío de correo de confirmación al crear usuario

### CRUD de Roles
- Listar roles
- Crear roles
- Editar roles
- Eliminar roles

### Seguridad
- Login con sesión
- Logout
- Control de acceso basado en roles:
  - **ADMIN**: Puede gestionar usuarios y roles
  - **PROFESOR**: Puede acceder a reportes y consultas avanzadas
  - **ESTUDIANTE**: Solo puede acceder a su perfil

### Consultas Avanzadas JPA
1. Buscar usuarios por rol
2. Buscar usuarios creados en un rango de fechas
3. Buscar usuarios por coincidencia parcial en correo, nombre o apellido
4. Contar usuarios activos vs inactivos
5. Obtener usuarios ordenados por fecha de creación

## Ejecución

1. Asegúrate de tener MySQL ejecutándose
2. Configura las credenciales en `application.properties`
3. Ejecuta la aplicación:
```bash
mvn spring-boot:run
```
4. Accede a la aplicación en: `http://localhost:78`

## Usuarios de Prueba

### Si usaste el script SQL (plataforma.sql)

El script incluye 3 usuarios de ejemplo:
- **Carlos Ramírez** (c.ramirez@correo.com) - Rol: ADMIN - Contraseña: 12345
- **Ana Soto** (ana.soto@correo.com) - Rol: ESTUDIANTE - Contraseña: 12345
- **Luisa Fernández** (l.fernandez@correo.com) - Rol: PROFESOR - Contraseña: 12345

**Importante:** Las contraseñas en el script están en texto plano. Para que funcionen:
1. Ejecuta el script SQL
2. Inicia la aplicación
3. Intenta iniciar sesión con alguno de estos usuarios
4. Si no funciona, actualiza las contraseñas desde la interfaz web (como ADMIN)

### Si prefieres crear usuarios manualmente

1. Inicia la aplicación
2. Crea los roles: ADMIN, PROFESOR, ESTUDIANTE
3. Crea usuarios asignándoles los roles correspondientes

## Notas
- El puerto de la aplicación es **78** según los requisitos
- Las contraseñas se encriptan automáticamente con BCrypt
- Los correos se envían al crear un nuevo usuario

