package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Deposito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepositoDAO extends DAO<Deposito> {
    public DepositoDAO() {
        super("deposito", "id");
    }

    @Override
    public Integer insert(Deposito c) throws SQLException {
        List<String> colunas = List.of("nome");
        List<Object> valores = List.of(c.getNome());

        return super.insert(colunas, valores);
    }

    @Override
    public boolean update(Deposito c) throws SQLException {
        List<String> colunas = List.of("nome");
        List<Object> valores = List.of(c.getNome());

        return super.update(c.getId(), colunas, valores);
    }

    @Override
    protected Deposito deserializer(ResultSet r) {
        try {
            Deposito model = new Deposito();
            model.setNome(r.getString("nome"));

            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
