# Hobbyte

## Montar el proyecto

Para montar el proyecto, hay que importar la `hobbyte_base.sql` en phpmyadmin.

Por otro lado configurarse el fichero Constantes en back para el usuario y la conraseña para conectarse a la base de datos.

Realizar el build del backend y ejecutar el proyecto.

Para el front hay que ejecutar `npm run dev`.

## Endpoints

### 1. Ver Pruebas de una Partida

- **Descripción**: Este endpoint permite ver las pruebas de una partida específica.
- **URL**: `http://localhost:8080/mis-partidas`
- **Método HTTP**: GET
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6MTJ9.W3E0NEH2fGAdEl3xWJYkaG5sEJ0qWX8AOUyD8RFXubc
- **Creado**: 2024-02-18T16:27:45.519Z
- **Modificado**: 2024-02-19T15:54:20.531Z

### 2. Ver Partidas

- **Descripción**: Este endpoint permite ver todas las partidas.
- **URL**: `localhost:8080/mis-partidas`
- **Método HTTP**: GET
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6MX0.eIAZvUB73TfornW3Ohlv-KxwXpyZK_tB16mre39Fxrg
- **Creado**: 2024-02-18T22:48:46.696Z
- **Modificado**: 2024-02-18T22:51:10.722Z

### 3. Crear Partida

- **Descripción**: Este endpoint permite crear una nueva partida.
- **URL**: `localhost:8080/partida`
- **Método HTTP**: POST
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6M30.Ovx-UPEKwVErEShr5pf_hVUuDQPZhY6pv93J2Q7UQi4
- **Creado**: 2024-02-18T16:27:45.520Z
- **Modificado**: 2024-02-18T16:29:10.209Z

### 4. Destapar

- **Descripción**: Este endpoint permite destapar una partida específica.
- **URL**: `localhost:8080/partida/510/destapar`
- **Método HTTP**: GET
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6M30.Ovx-UPEKwVErEShr5pf_hVUuDQPZhY6pv93J2Q7UQi4
- **Creado**: 2024-02-18T16:30:29.601Z
- **Modificado**: 2024-02-18T19:27:22.685Z

### 5. Login

- **Descripción**: Este endpoint permite iniciar sesión.
- **URL**: `localhost:8080/login`
- **Método HTTP**: POST
- **Body**: 
    ```json
    {
      "email": "badr@badr.com",
      "password": "1234"
    }
    ```
- **Creado**: 2024-02-18T16:27:45.522Z
- **Modificado**: 2024-02-18T22:51:04.562Z

### 6. Signup

- **Descripción**: Este endpoint permite registrar un nuevo usuario.
- **URL**: `localhost:8080/signup`
- **Método HTTP**: POST
- **Body**: 
    ```json
    {
        "nombre":"badr",
        "email": "badr2hamidou2@gmail.com",
        "password": "1234"
    }
    ```
- **Creado**: 2024-02-18T16:27:45.523Z
- **Modificado**: 2024-02-19T09:02:23.971Z

### 7. Confirmación de Nuevo Código

- **Descripción**: Este endpoint permite confirmar un nuevo código.
- **URL**: `http://localhost:8080/check-code`
- **Método HTTP**: POST
- **Body**: 
    ```json
    {
        "confirmation":" 9BvBVrXNLi",
        "email": "badr2hamidou2@gmail.com"
    }
    ```
- **Creado**: 2024-02-18T16:27:45.524Z
- **Modificado**: 2024-02-18T16:27:45.524Z

### 8. Mostrar Usuarios

- **Descripción**: Este endpoint permite mostrar todos los usuarios.
- **URL**: `http://localhost:8080/users`
- **Método HTTP**: GET
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6MX0.eIAZvUB73TfornW3Ohlv-KxwXpyZK_tB16mre39Fxrg
- **Creado**: 2024-02-18T16:27:45.525Z
- **Modificado**: 2024-02-19T17:02:32.779Z

### 9. Eliminar Usuario

- **Descripción**: Este endpoint permite eliminar un usuario.
- **URL**: `http://localhost:8080/user/11`
- **Método

 HTTP**: DELETE
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6MX0.eIAZvUB73TfornW3Ohlv-KxwXpyZK_tB16mre39Fxrg
- **Creado**: 2024-02-19T17:52:16.460Z
- **Modificado**: 2024-02-19T17:52:28.987Z

### 10. Ver Héroes

- **Descripción**: Este endpoint permite ver los héroes de una partida.
- **URL**: `localhost:8080/mis-heroes/50`
- **Método HTTP**: GET
- **Autenticación**: Bearer Token
- **Token**: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwLyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJpZCI6MX0.eIAZvUB73TfornW3Ohlv-KxwXpyZK_tB16mre39Fxrg
- **Creado**: 2024-02-19T12:07:55.840Z
- **Modificado**: 2024-02-19T12:08:47.424Z

---