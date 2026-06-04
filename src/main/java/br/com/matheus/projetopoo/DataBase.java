package br.com.matheus.projetopoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataBase {
    private static String url = "jdbc:mariadb://localhost:3306/projeto_poo";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar! ", e);
        }
    }
}
