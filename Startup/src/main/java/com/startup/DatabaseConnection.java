package com.startup;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection;
    
    private DatabaseConnection() {
        // Constructor privado para evitar la instanciación
    }
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tu_base_de_datos", "tu_usuario", "tu_contraseña");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
