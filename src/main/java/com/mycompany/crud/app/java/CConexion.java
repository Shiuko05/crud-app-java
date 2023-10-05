package com.mycompany.crud.app.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "";
    String contrasenia = "";
    String bd = "";
    String ip = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public CConexion() {
        conectar = estableceConexion();
    }
    
    public Connection estableceConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error: " + e.toString());
        }
        return conectar;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (conectar == null) {
            throw new SQLException("La conexión no está establecida.");
        }
        return conectar.prepareStatement(sql);
    }

    public Statement createStatement() throws SQLException {
    if (conectar == null) {
        throw new SQLException("La conexión no está establecida.");
    }
        return conectar.createStatement();
    }
}
