package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.FuncionarioViewTerminal;

import java.util.Optional;

public class FuncionarioController implements TerminalController{
    private final FuncionarioDAO dao = new FuncionarioDAO();
    private final FuncionarioViewTerminal view = new FuncionarioViewTerminal();
    private final SetorDAO daoSetor = new SetorDAO();

    @Override
    public void create() {
        Funcionario f = view.create();
        Optional<Setor> setor = daoSetor.getById(f.getSetorId());

        if (setor.isPresent()) {
            boolean confirm = view.confirmExec("Deseja cadastrar o funcionário no Setor:", setor.get().toString());

            if (confirm) {
                Optional<Integer> id = dao.insert(f);

                if (id.isPresent()) {
                    view.execCompleted("Funcionário criado com sucesso!");
                } else {
                    view.failedExec("Não foi possível cadastrar o Funcionário!");
                }
            }

        } else {
            view.failedExec("Setor não encontrado !");
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
