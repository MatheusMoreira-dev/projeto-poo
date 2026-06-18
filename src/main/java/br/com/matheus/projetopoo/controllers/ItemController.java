package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.DepositoDAO;
import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.ItemDAO;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Item;
import br.com.matheus.projetopoo.views.terminal.ItemViewTerminal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemController implements TerminalController{
    private final ItemDAO dao = new ItemDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ItemViewTerminal view = new ItemViewTerminal();

    @Override
    public void create() {
        try {
            Item i = view.create();
            Optional<Funcionario> setor = funcionarioDAO.getById(i.getFuncionarioId());

            if (setor.isEmpty()) {
                view.errorMsg("Não existe nenhum funcionário com esse id!");
                return;
            }

            dao.insert(i);
            view.successMsg("Item cadastrado com sucesso!");


        } catch (SQLException e) {
            view.errorMsg("Não foi possível realizar operação!");
        }
    }

    @Override
    public void delete() {
        Integer id = view.requestId();

        try {
            Optional<Item> s = dao.getById(id);

            if (s.isEmpty()){
                view.errorMsg("Não há nenhum item com esse id.");
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
            Item i = view.edit();
            boolean existsItem = dao.confirmExistence(i.getId());

            if (!existsItem) {
                view.errorMsg("Não existe nenhum item com esse Id");
                return;
            }

            boolean existsFuncionario = funcionarioDAO.confirmExistence(i.getFuncionarioId());

            if (!existsFuncionario) {
                view.errorMsg("Não existe nenhum funcionário com esse Id");
                return;
            }

            dao.update(i);
            view.successMsg("Item editado com sucesso!");

        } catch (SQLException e){
            view.errorMsg("Não foi possível editar o Item!");
        }
    }

    @Override
    public void get() {
        Integer id = view.requestId();

        try {
            Optional<Item> item = dao.getById(id);

            if (item.isEmpty()) {
                view.errorMsg("Não há nenhum item com esse Id");
                return;
            }

            view.showItem(item.get());

        } catch (SQLException e) {
            view.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    @Override
    public void getAll() {
        try {
            List<Item> list = dao.getAll();
            view.showAll(list);

        } catch (SQLException e) {
            view.errorMsg("Erro ao carregar todos os itens cadastrados!");
        }
    }

    @Override
    public void exit() {
        view.exit();
    }
}
