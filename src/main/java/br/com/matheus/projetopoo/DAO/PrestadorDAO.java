package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.ConnectionFactory;
import br.com.matheus.projetopoo.models.Prestador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrestadorDAO {
    private static final String nomeTabela = "prestador";

    public void criar(Prestador prestador){
        try {
            Connection conn = ConnectionFactory.getConnection();

            String sql = "INSERT INTO " + nomeTabela + "(razao_social, nome_fantasia, cnpj, is_ativo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, prestador.getRazaoSocial());
            stmt.setString(2, prestador.getNomeFantasia());
            stmt.setString(3, prestador.getCnpj());
            stmt.setBoolean(4, prestador.isAtivo());
            stmt.executeUpdate();

        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
