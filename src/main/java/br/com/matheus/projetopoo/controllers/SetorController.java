package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.SetorViewTerminal;
import br.com.matheus.projetopoo.views.terminal.ViewTerminal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SetorController implements Controller{
    private final SetorDAO dao = new SetorDAO();
    private final SetorViewTerminal view = new SetorViewTerminal();

    public void create(){
        Setor s = view.create();
        Optional<Integer> id = dao.insert(s);

        if (id.isPresent()){
            view.execCompleted("Recurso criado com sucesso");

        } else {
            view.failedExec("Falha em criar recurso!");
        }
    }

    public void delete(){
        Integer id = view.delete();
        Optional<Setor> s = dao.getById(id);

        if (s.isPresent()) {
            boolean confirmed = view.confirmDelete(s.get());

            if (confirmed) {
               boolean deleted = dao.delete(s.get().getId());

               if (deleted) {
                   view.execCompleted("Recurso deletado com sucesso !");
               } else{
                   view.failedExec("Falha ao deletar recurso !");
               }
            }
        } else {
            view.failedExec("Não foi possível localizar recurso!");
        }
    }

    public void edit(){

    }

    public void getAll(){

    }

    public  void get(){

    }

    static void main() {


    }
}