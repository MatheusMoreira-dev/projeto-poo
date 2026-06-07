package br.com.matheus.projetopoo.DAO;

import java.sql.ResultSet;

public class SetorDAO extends BaseDAO{
    public SetorDAO() {
        super("setor", "id");
    }

    @Override
    void create() {}

    @Override
    void update() {}

    @Override
    void mapperJson(ResultSet r) {}
}
