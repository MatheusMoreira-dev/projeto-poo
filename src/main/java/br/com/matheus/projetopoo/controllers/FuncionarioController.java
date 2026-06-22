package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.FuncionarioView;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FuncionarioController implements TerminalController{
    private final FuncionarioDAO DAO = new FuncionarioDAO();
    private final FuncionarioView VIEW = new FuncionarioView();
    private final SetorDAO SETOR_DAO = new SetorDAO();

    @Override
    public void create() {
        try {
            Funcionario f = VIEW.create();
            Optional<Setor> setor = SETOR_DAO.getById(f.getSetorId());

            if (setor.isEmpty()) {
                VIEW.errorMsg("Não existe nenhum setor com esse id!");
                return;
            }

            DAO.insert(f);
            VIEW.successMsg("Funcionário cadastrado com sucesso!");


        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar operação!");
        }
    }

    @Override
    public void delete() {
        Integer id = VIEW.requestId();

        try {
            Optional<Funcionario> s = DAO.getById(id);

            if (s.isEmpty()){
                VIEW.errorMsg("Não há nenhum funcionário com esse id.");
                return;
            }

            boolean confirmDelete = VIEW.delete(s.get());

            if (!confirmDelete){
                VIEW.successMsg("Operação cancelada!");
                return;
            }

            DAO.delete(s.get().getId());
            VIEW.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            VIEW.errorMsg("Falha na execução!");
        }
    }

    @Override
    public void edit() {
        try {
            Funcionario f = VIEW.edit();
            boolean existsFuncionario = DAO.confirmExistence(f.getId());

            if (!existsFuncionario) {
                VIEW.errorMsg("Não existe nenhum funcionário com esse Id");
                return;
            }

            boolean existsSetor = SETOR_DAO.confirmExistence(f.getSetorId());

            if (!existsSetor) {
                VIEW.errorMsg("Não existe nenhum setor com esse Id");
                return;
            }

            DAO.update(f);
            VIEW.successMsg("Funcionário editado com sucesso!");

        } catch (SQLException e){
            VIEW.errorMsg("Não foi possível editar o Funcionário!");
        }
    }

    @Override
    public void get() {
        Integer id = VIEW.requestId();

        try {
            Optional<Funcionario> s = DAO.getById(id);

            if (s.isEmpty()) {
                VIEW.errorMsg("Não há nenhum funcionário com esse Id");
                return;
            }

            VIEW.showItem(s.get());

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    @Override
    public void getAll() {
        try {
            List<Funcionario> list = DAO.getAll();
            VIEW.showAll(list);

        } catch (SQLException e) {
            VIEW.errorMsg("Erro ao carregar todos os funcionários cadastrados!");
        }
    }

    @Override
    public void exit() {
        VIEW.exit();
    }
}