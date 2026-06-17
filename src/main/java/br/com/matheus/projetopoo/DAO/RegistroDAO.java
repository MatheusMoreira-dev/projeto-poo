package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Registro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegistroDAO extends DAO<Registro> {
    public RegistroDAO() {
        super("registros", "id");
    }

    @Override
    public Integer insert(Registro c) throws SQLException {
        List<String> colunas = List.of("item_id", "funcionario_id", "deposito_id", "data", "quantidade", "descricao");
        List<Object> valores = List.of(
                c.getItemId(),
                c.getFuncionarioId(),
                c.getDepositoId(),
                java.sql.Date.valueOf(c.getData()),
                c.getQuantidade(),
                c.getDescricao()
        );

        return super.insert(colunas, valores);
    }

    @Override
    public boolean update(Registro c) throws SQLException {
        List<String> colunas = List.of("item_id", "funcionario_id", "deposito_id", "data", "quantidade", "descricao");
        List<Object> valores = List.of(
                c.getItemId(),
                c.getFuncionarioId(),
                c.getDepositoId(),
                c.getData(),
                c.getQuantidade(),
                c.getDescricao()
        );

        return super.update(c.getItemId(), colunas, valores);
    }

    @Override
    protected Registro deserializer(ResultSet r) {
        try {
            Registro model = new Registro();
            model.setItemId(r.getInt("item_id"));
            model.setFuncionarioId(r.getInt("funcionario_id"));
            model.setDepositoId(r.getInt("deposito_id"));
            model.setData(r.getDate("data").toLocalDate());
            model.setQuantidade(r.getInt("quantidade"));
            model.setDescricao(r.getString("descricao"));

            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
