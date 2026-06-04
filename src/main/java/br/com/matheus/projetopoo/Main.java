package br.com.matheus.projetopoo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static void main() throws SQLException {
        Connection conexao = DataBase.getConnection();

        Statement stmt = conexao.createStatement();
        ResultSet result = stmt.executeQuery("select * from prestador");

        result.first();

        System.out.println(result.getString("razao_social"));
    }
}
