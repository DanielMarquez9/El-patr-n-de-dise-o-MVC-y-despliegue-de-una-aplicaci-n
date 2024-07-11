<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Error en la Operación</h1>
    <p><%= request.getParameter("message") != null ? request.getParameter("message") : "Ocurrió un error, por favor intente de nuevo." %></p>
    <a href="index.jsp">Volver</a>
</body>
</html>
