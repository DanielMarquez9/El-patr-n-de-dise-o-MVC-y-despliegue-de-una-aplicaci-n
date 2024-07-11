package com.startup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        
        if(correo.isEmpty() || password.isEmpty()) {
            response.sendRedirect("error.jsp");
        } else {
            try {
                Connection conn = DatabaseConnection.getConnection();
                String query = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, correo);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    request.setAttribute("nombre", rs.getString("nombre"));
                    request.setAttribute("correo", rs.getString("correo"));
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error.jsp?message=Usuario no encontrado");
                }
            } catch(Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        }
    }
}
