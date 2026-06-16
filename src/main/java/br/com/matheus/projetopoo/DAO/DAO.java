package br.com.matheus.projetopoo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DAO<ClassModel> {
    private final String nomeTabela;
    private final String nomeColunaPK;

    DAO(String nomeTabela, String nomeColunaPK){
        this.nomeTabela = nomeTabela;
        this.nomeColunaPK = nomeColunaPK;
    }

    public String getNomeTabela() {return nomeTabela;}

    public String getNomeColunaPK() {return nomeColunaPK;}

    public List<ClassModel> getAll() {
        String sql = "SELECT * FROM %s".formatted(nomeTabela);
        List<ClassModel> list = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){

            ResultSet result = stmt.executeQuery();

            while(result.next()) {
                list.add(deserializer(result));
            }

            result.close();

        } catch (SQLException e){
            System.out.println("Erro ao carregar todos os registros da tabela " + nomeTabela + "\n" + e);
        }

        return list;
    }

    public Optional<ClassModel> getById(int id){
        String sql = "SELECT * FROM %s WHERE %s = ?".formatted(nomeTabela, nomeColunaPK);

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){

            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) return Optional.of(deserializer(result));
            result.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar registro na tabela " + nomeTabela + "\n" + e);
        }

        return Optional.empty();
    };

    public boolean confirmExistence(int id){
        String sql = "SELECT 1 from %s WHERE %s = ?".formatted(nomeTabela, nomeColunaPK);

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) return true;
            result.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar registro na tabela " + nomeTabela + "\n" + e);
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM %s WHERE %s = ?".formatted(nomeTabela, nomeColunaPK);
        boolean successful = false;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){

                stmt.setInt(1, id);
                successful = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar registro na tabela " + nomeTabela + "\n" + e);
        }

        return successful;
    };

    protected Optional<Integer> insert(List<String> nomeColunas, List<Object> valorColunas){
        List<String> qtdParametros = new ArrayList<>(nomeColunas.size());

        for (int i = 0; i < nomeColunas.size(); i++){
            qtdParametros.add("?");
        }

        String strCol = String.join(",", nomeColunas);
        String strParams = String.join(",", qtdParametros);

        String sql = "INSERT INTO %s (%s) VALUES (%s)".formatted(nomeTabela, strCol, strParams);

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            for (int i = 0; i < nomeColunas.size(); i++){
                stmt.setObject(i + 1, valorColunas.get(i));
            }

            stmt.executeUpdate();
            ResultSet key = stmt.getGeneratedKeys();

            if (key.next()){
                return Optional.of(key.getInt(1));
            }

            key.close();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela + "\n" + e);
        }

        return Optional.empty();
    };

    public abstract Optional<Integer> insert(ClassModel c);

    protected boolean update(int id, List<String> nomeColunas, List<Object> valorColunas){
        List<String> params = new ArrayList<>(nomeColunas.size());

        for (int i = 0; i < nomeColunas.size(); i++){
            params.add(nomeColunas.get(i) + " = ?");
        }

        String strParams = String.join(",", params);
        String sql = "UPDATE %s SET %s WHERE %s = ?".formatted(nomeTabela, strParams, nomeColunaPK);
        boolean successful = false;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            for (int i = 0; i < nomeColunas.size(); i++){
                stmt.setObject(i + 1, valorColunas.get(i));
            }

            stmt.setInt(nomeColunas.size() + 1, id);
            successful = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao editar registro na tabela " + nomeTabela + "\n" + e);
        }

        return successful;
    };

    public abstract boolean update(ClassModel c);

    protected abstract ClassModel deserializer (ResultSet r);
}