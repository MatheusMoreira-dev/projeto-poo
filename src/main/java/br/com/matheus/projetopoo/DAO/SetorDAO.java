package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Setor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SetorDAO extends DAO<Setor> {
    public SetorDAO() {
        super("setor", "id");
    }

    @Override
    public Optional<Integer> insert(Setor c) {
        List<String> colunas = List.of("nome");
        List<Object> valores = List.of(c.getNome());

        return insert(colunas, valores);
    }

    @Override
    public boolean update(Setor c) {
        List<String> colunas = List.of("nome");
        List<Object> valores = List.of(c.getNome());

        return super.update(c.getId(), colunas, valores);
    }

    @Override
    public Setor deserializer(ResultSet r) {
        try {
            Setor model = new Setor();
            model.setNome(r.getString("nome"));
            model.setId(r.getInt("id"));

            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
