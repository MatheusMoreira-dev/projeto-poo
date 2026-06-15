package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.SetorViewTerminal;
import br.com.matheus.projetopoo.views.terminal.ViewTerminal;

import java.util.List;
import java.util.Optional;

public class SetorController{
    private static final SetorDAO dao = new SetorDAO();
    private static final SetorViewTerminal view = new SetorViewTerminal();

    public static void create(){
        Setor s = view.create();
        Optional<Integer> id = dao.insert(s);

        if (id.isPresent()){
            view.execCompleted("Recurso criado com sucesso");

        } else {
            view.failedExec("Falha em criar recurso!");
        }
    }

    public static void delete(){
        Integer id = view.delete();
        boolean exists = dao.confirmExistence(id);

        if (exists) {
            Optional<Setor> s = dao.getById(id);

            boolean delete = view.confirmDelete(s.get());
        }


    }

    public static void edit(){

    }

    public static void getAll(){

    }

    public static void get(){

    }

    static void main() {


    }
}