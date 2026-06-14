package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.DAO;

public abstract class Controller <ClassModel> {
    protected final DAO<ClassModel> dao;

    Controller(DAO<ClassModel> dao){this.dao = dao;}
}
