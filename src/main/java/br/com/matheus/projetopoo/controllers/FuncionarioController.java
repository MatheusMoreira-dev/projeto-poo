package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.FuncionarioViewTerminal;

import java.sql.SQLException;
import java.util.Optional;

public class FuncionarioController implements TerminalController{
    private final FuncionarioDAO dao = new FuncionarioDAO();
    private final FuncionarioViewTerminal view = new FuncionarioViewTerminal();
    private final SetorDAO daoSetor = new SetorDAO();

    @Override
    public void create() {
        Funcionario f = view.create();

        try {
            Optional<Setor> setor = daoSetor.getById(f.getSetorId());

            if (setor.isEmpty()) {
                view.failedExec("Não existe nenhum setor com esse id!");
                return;
            }

            dao.insert(f);
            view.execCompleted("Funcionário cadastrado com sucesso!");


        } catch (SQLException e) {
            view.failedExec("Não foi possível realizar operação!");
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void get() {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void startMenu() {

    }
}
