package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

abstract class BaseDAO {
    private final String nomeTabela;
    private final String nomeColunaPK;

    BaseDAO(String nomeTabela, String nomeColunaPK){
        this.nomeTabela = nomeTabela;
        this.nomeColunaPK = nomeColunaPK;
    }

    public Optional<ResultSet> getAll() {
        String sql = "SELECT * FROM " + nomeTabela;

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            return Optional.of(result);

        } catch (SQLException e){
            System.out.println("Erro ao carregar todos os registros da tabela " + nomeTabela + "\n" + e);
        }

        return Optional.empty();
    }

    public Optional<ResultSet> getById(int id){
        String sql = "SELECT * FROM " + nomeTabela + " WHERE " + nomeColunaPK + " = " + id;

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            return  Optional.of(result);

        } catch (SQLException e) {
            System.out.println("Erro ao buscar registro na tabela " + nomeTabela + "\n" + e);
        }

        return Optional.empty();
    };

    public void delete(int id) {
        String sql = "DELETE FROM " + nomeTabela + " WHERE " + nomeColunaPK + "= " + id;

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar registro na tabela " + nomeTabela + "\n" + e);
        }
    };

    abstract void create();

    abstract void update();

    abstract void mapperJson(ResultSet r);
}