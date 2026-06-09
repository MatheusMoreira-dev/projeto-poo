package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseDAO <ClassModel> {
    private final String nomeTabela;
    private final String nomeColunaPK;

    BaseDAO(String nomeTabela, String nomeColunaPK){
        this.nomeTabela = nomeTabela;
        this.nomeColunaPK = nomeColunaPK;
    }

    public String getNomeTabela() {return nomeTabela;}

    public String getNomeColunaPK() {return nomeColunaPK;}

    public List<ClassModel> getAll() {
        String sql = "SELECT * FROM ?";
        List<ClassModel> list = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet result = stmt.executeQuery();
                ){

                stmt.setString(1, nomeTabela);

            while(result.next()) {
                list.add(deserializer(result));
            }

        } catch (SQLException e){
            System.out.println("Erro ao carregar todos os registros da tabela " + nomeTabela + "\n" + e);
        }

        return list;
    }

    public Optional<ClassModel> getById(int id){
        String sql = "SELECT * FROM ? WHERE ? = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet result = stmt.executeQuery();
                ){

            stmt.setString(1, nomeTabela);
            stmt.setString(2, nomeColunaPK);
            stmt.setInt(3, id);

            if (result.next()) return Optional.of(deserializer(result));

        } catch (SQLException e) {
            System.out.println("Erro ao buscar registro na tabela " + nomeTabela + "\n" + e);
        }

        return Optional.empty();
    };

    public void delete(int id) {
        String sql = "DELETE FROM ? WHERE ? = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){

                stmt.setString(1, nomeTabela);
                stmt.setString(2, nomeColunaPK);
                stmt.setInt(3, id);

                stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar registro na tabela " + nomeTabela + "\n" + e);
        }
    };

    public abstract ClassModel create(ClassModel c);

    public abstract ClassModel update(ClassModel c);

    abstract ClassModel deserializer (ResultSet r);
}