package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Setor;

import java.sql.ResultSet;

public class SetorDAO extends BaseDAO<Setor>{

    public SetorDAO() {
        super("setor", "id");
    }

    @Override
    Setor deserializer(ResultSet r) {
        return null;
    }
}
