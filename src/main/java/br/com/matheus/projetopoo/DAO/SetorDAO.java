package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Setor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SetorDAO extends DAO<Setor> {

    public SetorDAO() {
        super("setor", "id");
    }

    @Override
    public Setor deserializer(ResultSet r) {
        try {
            Setor model = new Setor(r.getInt("id"));
            model.setNome(r.getString("nome"));

            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
