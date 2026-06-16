package br.com.matheus.projetopoo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {
    private static final String url = "jdbc:mariadb://localhost:3306/controle_estoque";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
