package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.DepositoDAO;
import br.com.matheus.projetopoo.models.Deposito;
import br.com.matheus.projetopoo.views.terminal.DepositoView;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepositoController implements TerminalController{
    private final DepositoDAO DAO = new DepositoDAO();
    private final DepositoView VIEW = new DepositoView();

    public void create(){
        Deposito s = VIEW.create();

        try {
            Integer id = DAO.insert(s);
            VIEW.successMsg("Depósito criado com sucesso");

        } catch (SQLException e){
            VIEW.errorMsg("Falha em criar depósito!");
        }
    }

    public void delete(){
        Integer id = VIEW.requestId();

        try {
            Optional<Deposito> s = DAO.getById(id);

            if (s.isEmpty()){
                VIEW.errorMsg("Não há nenhum depósito com esse id.");
                return;
            }

            boolean deleted = DAO.delete(s.get().getId());

            if (!deleted){
                VIEW.successMsg("Operação cancelada!");
                return;
            }

            VIEW.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível localizar o depósito!");
        }
    }

    public void edit(){
        Deposito s = VIEW.edit();

        try {
            boolean exists = DAO.confirmExistence(s.getId());

            if (!exists) {
                VIEW.errorMsg("Não existe nenhum depósito com esse Id");
                return;
            }

            DAO.update(s);
            VIEW.successMsg("Depósito editado com sucesso!");

        } catch (SQLException e){
            VIEW.errorMsg("Não foi possível editar o depósito!");
        }
    }

    public void getAll() {
        try {
            List<Deposito> list = DAO.getAll();
            VIEW.showAll(list);

        } catch (SQLException e) {
            VIEW.errorMsg("Erro ao carregar todos os depósitos cadastrados!");
        }
    }

    public void get(){
        Integer id = VIEW.requestId();

        try {
            Optional<Deposito> s = DAO.getById(id);

            if (s.isEmpty()) {
                VIEW.errorMsg("Não há nenhum deposito com esse Id");
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
