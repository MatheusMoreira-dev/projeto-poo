package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.SetorViewTerminal;

import java.util.List;
import java.util.Optional;

public class SetorController implements TerminalController {
    private final SetorDAO dao = new SetorDAO();
    private final SetorViewTerminal view = new SetorViewTerminal();

    public void create(){
        Setor s = view.create();
        Optional<Integer> id = dao.insert(s);

        if (id.isPresent()){
            view.execCompleted("Setor criado com sucesso");

        } else {
            view.failedExec("Falha em criar setor!");
        }
    }

    public void delete(){
        Integer id = view.requestId();
        Optional<Setor> s = dao.getById(id);

        if (s.isPresent()) {
            boolean confirmed = view.delete(s.get());

            if (confirmed) {
               boolean deleted = dao.delete(s.get().getId());

               if (deleted) {
                   view.execCompleted("Setor deletado com sucesso !");
               } else{
                   view.failedExec("Falha ao deletar setor !");
               }
            }
        } else {
            view.failedExec("Não foi possível localizar setor!");
        }
    }

    public void edit(){
        Setor s = view.edit();
        boolean edited = dao.update(s);

        if (edited) {
            view.execCompleted("Setor editado com sucesso!");
        } else {
            view.failedExec("Não foi possível editar o setor!");
        }
    }

    public void getAll(){
        List<Setor> list = dao.getAll();
        view.showAll(list);
    }

    public void get(){
        Integer id = view.requestId();
        Optional<Setor> s = dao.getById(id);

        if (s.isPresent()) {
            view.showItem(s.get());
        } else{
            view.failedExec("Não há nenhum setor com esse ID.");
        }
    }

    public void exit(){
        view.exit();
        input.close();
    }

    public void startMenu() {
        int opt = -1;
        String menu = """
                Setor
                ----------------------------------------------
                1 - Criar
                2 - Mostrar Item
                3 - Motrar todos os registros
                4 - Editar
                5 - Deletar
                
                0 - Sair
                -------------------------------------------
                
                Opção:
                """;

        while (opt != 0) {
            System.out.print(menu);
            opt = input.nextInt();

            switch (opt) {
                case 1: create(); break;
                case 2: get(); break;
                case 3: getAll(); break;
                case 4: edit(); break;
                case 5: delete(); break;
                case 0: exit(); break;

                default:
                    System.out.println("\nDigite uma opção válida !\n");
            }
        }
    }
}