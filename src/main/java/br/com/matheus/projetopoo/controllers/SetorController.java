package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.utils.TerminalUtils;
import br.com.matheus.projetopoo.views.terminal.SetorViewTerminal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SetorController implements TerminalController {
    private final SetorDAO dao = new SetorDAO();
    private final SetorViewTerminal view = new SetorViewTerminal();

    public void create(){
        Setor s = view.create();

        try {
            Integer id = dao.insert(s);
            view.successMsg("Setor criado com sucesso");

        } catch (SQLException e){
            view.errorMsg("Falha em criar setor!");
        }
    }

    public void delete(){
        Integer id = view.requestId();

        try {
            Optional<Setor> s = dao.getById(id);

            if (s.isEmpty()){
                view.errorMsg("Não há nenhum setor com esse id.");
                return;
            }

            boolean deleted = dao.delete(s.get().getId());

            if (!deleted){
                view.successMsg("Operação cancelada!");
                return;
            }

            view.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            view.errorMsg("Não foi possível localizar o setor!");
        }
    }

    public void edit(){
        Setor s = view.edit();

        try {
            boolean exists = dao.confirmExistence(s.getId());

            if (!exists) {
                view.errorMsg("Não existe nenhum setor com esse Id");
                return;
            }

            dao.update(s);
            view.successMsg("Setor editado com sucesso!");

        } catch (SQLException e){
            view.errorMsg("Não foi possível editar o setor!");
        }
    }

    public void getAll() {
        try {
            List<Setor> list = dao.getAll();
            view.showAll(list);

        } catch (SQLException e) {
            view.errorMsg("Erro ao carregar todos os setores cadastrados!");
        }
    }

    public void get(){
        Integer id = view.requestId();

        try {
            Optional<Setor> s = dao.getById(id);

            if (s.isEmpty()) {
                view.errorMsg("Não há nenhum setor com esse Id");
                return;
            }

            view.showItem(s.get());

        } catch (SQLException e) {
            view.errorMsg("Não foi possível realizar operação de busca!");
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

            TerminalUtils.clearConsole();

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