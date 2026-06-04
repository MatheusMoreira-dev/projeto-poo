package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.BaseModel;

public abstract class BaseDAO {
    private BaseModel model;
    private String tableName;

    BaseDAO(BaseModel model){
        this.model = model;
    }

    public void create(){}

    public void delete(){}

    public void update(){}

    public void getById(){}

    public void getAll(){}
}
