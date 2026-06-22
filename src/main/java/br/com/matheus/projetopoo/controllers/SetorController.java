package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.SetorView;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SetorController implements TerminalController {
    private final SetorDAO DAO = new SetorDAO();
    private final SetorView VIEW = new SetorView();

    public void create(){
        Setor s = VIEW.create();

        try {
            Integer id = DAO.insert(s);
            VIEW.successMsg("Setor criado com sucesso");

        } catch (SQLException e){
            VIEW.errorMsg("Falha em criar setor!");
        }
    }

    public void delete(){
        Integer id = VIEW.requestId();

        try {
            Optional<Setor> s = DAO.getById(id);

            if (s.isEmpty()){
                VIEW.errorMsg("Não há nenhum setor com esse id.");
                return;
            }

            boolean deleted = DAO.delete(s.get().getId());

            if (!deleted){
                VIEW.successMsg("Operação cancelada!");
                return;
            }

            VIEW.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível localizar o setor!");
        }
    }

    public void edit(){
        Setor s = VIEW.edit();

        try {
            boolean exists = DAO.confirmExistence(s.getId());

            if (!exists) {
                VIEW.errorMsg("Não existe nenhum setor com esse Id");
                return;
            }

            DAO.update(s);
            VIEW.successMsg("Setor editado com sucesso!");

        } catch (SQLException e){
            VIEW.errorMsg("Não foi possível editar o setor!");
        }
    }

    public void getAll() {
        try {
            List<Setor> list = DAO.getAll();
            VIEW.showAll(list);

        } catch (SQLException e) {
            VIEW.errorMsg("Erro ao carregar todos os setores cadastrados!");
        }
    }

    public void get(){
        Integer id = VIEW.requestId();

        try {
            Optional<Setor> s = DAO.getById(id);

            if (s.isEmpty()) {
                VIEW.errorMsg("Não há nenhum setor com esse Id");
                return;
            }

            VIEW.showItem(s.get());

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    public void exit(){
        VIEW.exit();
    }
}