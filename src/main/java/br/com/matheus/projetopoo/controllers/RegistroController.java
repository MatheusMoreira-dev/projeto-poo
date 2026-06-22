package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.DepositoDAO;
import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.ItemDAO;
import br.com.matheus.projetopoo.DAO.RegistroDAO;
import br.com.matheus.projetopoo.models.Deposito;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Item;
import br.com.matheus.projetopoo.models.Registro;
import br.com.matheus.projetopoo.views.terminal.RegistroView;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RegistroController implements TerminalController{
    private static final RegistroDAO DAO = new RegistroDAO();
    private static final ItemDAO ITEM_DAO = new ItemDAO();
    private static final FuncionarioDAO FUNCIONARIO_DAO = new FuncionarioDAO();
    private static final DepositoDAO DEPOSITO_DAO = new DepositoDAO();
    private static final RegistroView VIEW = new RegistroView();

    @Override
    public void create() {
        try {
            Registro r = VIEW.create();

            Optional<Item> item = ITEM_DAO.getById(r.getItemId());

            if (item.isEmpty()){
                VIEW.errorMsg("Não há nenhum item com esse id!");
                return;
            }

            Optional<Funcionario> funcionario = FUNCIONARIO_DAO.getById(r.getFuncionarioId());

            if (funcionario.isEmpty()){
                VIEW.errorMsg("Não há nenhum funcionário com esse id!");
                return;
            }

            Optional<Deposito> deposito = DEPOSITO_DAO.getById(r.getDepositoId());

            if (deposito.isEmpty()){
                VIEW.errorMsg("Não há nenhum depósito com esse id!");
                return;
            }

            DAO.insert(r);
            VIEW.successMsg("Registro cadastrado com sucesso!");

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível cadastrar!");
        }
    }

    @Override
    public void delete() {
        Integer id = VIEW.requestId();

        try {
            Optional<Registro> s = DAO.getById(id);

            if (s.isEmpty()){
                VIEW.errorMsg("Não há nenhum registro com esse id.");
                return;
            }

            boolean confirmDelete = DAO.delete(s.get().getId());

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
            Registro r = VIEW.edit();

            boolean existsItem = ITEM_DAO.confirmExistence(r.getItemId());
            if (!existsItem){
                VIEW.errorMsg("Não existe nenhum item com esse id.");
                return;
            }

            boolean existsFuncionario = ITEM_DAO.confirmExistence(r.getFuncionarioId());
            if (!existsFuncionario){
                VIEW.errorMsg("Não existe nenhum funcionário com esse id.");
                return;
            }

            boolean existsDeposito = DEPOSITO_DAO.confirmExistence(r.getDepositoId());
            if(!existsDeposito){
                VIEW.errorMsg("Não existe nenhum depósito com esse id.");
                return;
            }

            DAO.update(r);
            VIEW.successMsg("Item cadastrado com sucesso!");

        } catch (SQLException e){
            VIEW.errorMsg("Não foi possível editar o item!");
        }
    }

    @Override
    public void get() {
        Integer id = VIEW.requestId();

        try {
            Optional<Registro> r = DAO.getById(id);

            if (r.isEmpty()) {
                VIEW.errorMsg("Não há nenhum registro com esse Id");
                return;
            }

            VIEW.showItem(r.get());

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    @Override
    public void getAll() {
        try {
            List<Registro> list = DAO.getAll();
            VIEW.showAll(list);

        } catch (SQLException e) {
            VIEW.errorMsg("Erro ao carregar todos os registros cadastrados!");
        }
    }

    @Override
    public void exit() {
        VIEW.exit();
    }
}
