package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;

import java.util.List;
import java.util.Optional;

public class SetorController{
    private final SetorDAO dao = new SetorDAO();

    public int create(String nome){
        return dao.insert(List.of("nome"), List.of(nome));
    }

    public int delete(int id){
        Optional<Setor> find = dao.getById(id);

        if (find.isPresent()) {
            return dao.delete(id);
        }

        return 0;
    }

    public int edit(int id, String nome){
        Optional<Setor> find = dao.getById(id);

        if (find.isPresent()){
            return dao.update(id, List.of("nome"), List.of(nome));
        }

        return 0;
    }

    public List<Setor> getAll(){
        return dao.getAll();
    }

    public Optional<Setor> get(int id){
        return dao.getById(id);
    }
}
