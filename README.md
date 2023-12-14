# endpoints

## Login
POST /login
BODY:

```json
{
  "username": "badr",
  "password": "1234"
}
```
RESPUSTAS:

status 200:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMTkyLjE2OC4wLjEyMDo4MDkwL3J1dGFzVmFyaWFzIiwiaXNzIjoiaHR0cDovLzE5Mi4xNjguMC4xMjA6ODA5MCIsInVzZXJuYW1lIjoiYmFkciJ9.BWmxg9oZaSfExmhZp1KnT5EJYrYvrYMGNwr9XTujD8Y",
  "username": "badr"
}
```

status 404:

```json
{
    "message": "Unauthorized",
    "status": 404
}
```