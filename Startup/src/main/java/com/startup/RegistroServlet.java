package package com.startup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        
        if(nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            response.sendRedirect("error.jsp");
        } else {
            try {
                Connection conn = DatabaseConnection.getConnection();
                String queryCheck = "SELECT * FROM usuarios WHERE correo = ?";
                PreparedStatement psCheck = conn.prepareStatement(queryCheck);
                psCheck.setString(1, correo);
                ResultSet rs = psCheck.executeQuery();
                if(rs.next()) {
                    response.sendRedirect("error.jsp?message=Usuario ya existe");
                } else {
                    String query = "INSERT INTO usuarios (nombre, correo, password) VALUES (?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, nombre);
                    ps.setString(2, correo);
                    ps.setString(3, password);
                    ps.executeUpdate();
                    response.sendRedirect("login.jsp");
                }
            } catch(Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        }
    }
}

