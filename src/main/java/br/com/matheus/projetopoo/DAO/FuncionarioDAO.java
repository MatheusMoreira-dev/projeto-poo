package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FuncionarioDAO extends DAO<Funcionario> {

    FuncionarioDAO() {
        super("funcionario", "id");
    }

    @Override
    public Optional<Integer> insert(Funcionario c) {
        List<String> colunas = List.of("nome", "setor_id");
        List<Object> valores = List.of(c.getNome(), c.getSetorId());

        return super.insert(colunas, valores);
    }

    @Override
    public boolean update(Funcionario c) {
        List<String> colunas = List.of("nome", "setor_id");
        List<Object> valores = List.of(c.getNome(), c.getSetorId());

        return super.update(c.getId(), colunas, valores);
    }

    @Override
    protected Funcionario deserializer(ResultSet r) {
        try {
            Funcionario model = new Funcionario();
            model.setNome(r.getString("nome"));
            model.setId(r.getInt("id"));

            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}