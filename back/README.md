# endpoints

## Login
POST /login
BODY:

```json
{
  "email": "badr@gmail.com",
  "password": "1234"
}
```
RESPUSTAS:

status 200:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMTkyLjE2OC4wLjEyMDo4MDkwL3J1dGFzVmFyaWFzIiwiaXNzIjoiaHR0cDovLzE5Mi4xNjguMC4xMjA6ODA5MCIsImVtYWlsIjoiYmFkckBiYWRyLmNvbSJ9.kwgLa0qzP4T5marWNQkEgpoHKhXB8ZKulbNMKJOsPc0",
  "email": "badr@badr.com",
  "nombre": "badr"
}
```

status 404:

```json
{
    "message": "Unauthorized",
    "status": 404
}
```

## Signup

POST /signup
BODY:

```json
{
    "email": "chubaca@gmail.com",
    "password": "1234"
}
```

RESPUESTAS:

status 201:
```json
{
  "message": "Usuario creado",
  "status": 201
}
```

status 417:
```json
{
  "message": "Error al crear el usuario",
  "status": 417
}
```
