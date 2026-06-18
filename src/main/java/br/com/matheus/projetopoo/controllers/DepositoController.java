package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.DepositoDAO;
import br.com.matheus.projetopoo.models.Deposito;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.DepositoViewTerminal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepositoController implements TerminalController{
    private final DepositoDAO dao = new DepositoDAO();
    private final DepositoViewTerminal view = new DepositoViewTerminal();

    public void create(){
        Deposito s = view.create();

        try {
            Integer id = dao.insert(s);
            view.successMsg("Depósito criado com sucesso");

        } catch (SQLException e){
            view.errorMsg("Falha em criar depósito!");
        }
    }

    public void delete(){
        Integer id = view.requestId();

        try {
            Optional<Deposito> s = dao.getById(id);

            if (s.isEmpty()){
                view.errorMsg("Não há nenhum depósito com esse id.");
                return;
            }

            boolean deleted = dao.delete(s.get().getId());

            if (!deleted){
                view.successMsg("Operação cancelada!");
                return;
            }

            view.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            view.errorMsg("Não foi possível localizar o depósito!");
        }
    }

    public void edit(){
        Deposito s = view.edit();

        try {
            boolean exists = dao.confirmExistence(s.getId());

            if (!exists) {
                view.errorMsg("Não existe nenhum depósito com esse Id");
                return;
            }

            dao.update(s);
            view.successMsg("Depósito editado com sucesso!");

        } catch (SQLException e){
            view.errorMsg("Não foi possível editar o depósito!");
        }
    }

    public void getAll() {
        try {
            List<Deposito> list = dao.getAll();
            view.showAll(list);

        } catch (SQLException e) {
            view.errorMsg("Erro ao carregar todos os depósitos cadastrados!");
        }
    }

    public void get(){
        Integer id = view.requestId();

        try {
            Optional<Deposito> s = dao.getById(id);

            if (s.isEmpty()) {
                view.errorMsg("Não há nenhum deposito com esse Id");
                return;
            }

            view.showItem(s.get());

        } catch (SQLException e) {
            view.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    public void exit(){
        view.exit();
    }
}
