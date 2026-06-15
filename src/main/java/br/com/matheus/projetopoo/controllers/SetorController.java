package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;

import java.util.List;
import java.util.Optional;

public class SetorController{
    private final SetorDAO dao = new SetorDAO();

    public boolean create(String nome){
        return dao.insert(List.of("nome"), List.of(nome));
    }

    public boolean delete(int id){
        return dao.delete(id);
    }

    public boolean edit(int id, String nome){
        return dao.update(id, List.of("nome"), List.of(nome));
    }

    public List<Setor> getAll(){
        return dao.getAll();
    }

    public Optional<Setor> get(int id){
        return dao.getById(id);
    }
}