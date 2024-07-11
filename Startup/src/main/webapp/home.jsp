<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Bienvenido al Sistema</h1>
    <p>Nombre: <%= request.getAttribute("nombre") %></p>
    <p>Correo: <%= request.getAttribute("correo") %></p>
</body>
</html>
