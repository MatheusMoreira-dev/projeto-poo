package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;

public class SetorController {
    private final SetorDAO setorDAO = new SetorDAO();

    public Setor create(String nome){
        Setor s = new Setor();
        s.setNome(nome);

        setorDAO.create(s);
        return s;
    }
}
