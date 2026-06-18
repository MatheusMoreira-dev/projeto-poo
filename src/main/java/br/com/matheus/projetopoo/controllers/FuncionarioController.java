package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.FuncionarioViewTerminal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FuncionarioController implements TerminalController{
    private final FuncionarioDAO dao = new FuncionarioDAO();
    private final FuncionarioViewTerminal view = new FuncionarioViewTerminal();
    private final SetorDAO setorDAO = new SetorDAO();

    @Override
    public void create() {
        try {
            Funcionario f = view.create();
            Optional<Setor> setor = setorDAO.getById(f.getSetorId());

            if (setor.isEmpty()) {
                view.errorMsg("Não existe nenhum setor com esse id!");
                return;
            }

            dao.insert(f);
            view.successMsg("Funcionário cadastrado com sucesso!");


        } catch (SQLException e) {
            view.errorMsg("Não foi possível realizar operação!");
        }
    }

    @Override
    public void delete() {
        Integer id = view.requestId();

        try {
            Optional<Funcionario> s = dao.getById(id);

            if (s.isEmpty()){
                view.errorMsg("Não há nenhum funcionário com esse id.");
                return;
            }

            boolean deleted = dao.delete(s.get().getId());

            if (!deleted){
                view.successMsg("Operação cancelada!");
                return;
            }

            view.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            view.errorMsg("Falha na execução!");
        }
    }

    @Override
    public void edit() {
        try {
            Funcionario f = view.edit();
            boolean existsFuncionario = dao.confirmExistence(f.getId());

            if (!existsFuncionario) {
                view.errorMsg("Não existe nenhum funcionário com esse Id");
                return;
            }

            boolean existsSetor = setorDAO.confirmExistence(f.getSetorId());

            if (!existsSetor) {
                view.errorMsg("Não existe nenhum setor com esse Id");
                return;
            }

            dao.update(f);
            view.successMsg("Funcionário editado com sucesso!");

        } catch (SQLException e){
            view.errorMsg("Não foi possível editar o Funcionário!");
        }
    }

    @Override
    public void get() {
        Integer id = view.requestId();

        try {
            Optional<Funcionario> s = dao.getById(id);

            if (s.isEmpty()) {
                view.errorMsg("Não há nenhum funcionário com esse Id");
                return;
            }

            view.showItem(s.get());

        } catch (SQLException e) {
            view.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    @Override
    public void getAll() {
        try {
            List<Funcionario> list = dao.getAll();
            view.showAll(list);

        } catch (SQLException e) {
            view.errorMsg("Erro ao carregar todos os funcionários cadastrados!");
        }
    }

    @Override
    public void exit() {
        view.exit();
    }
}