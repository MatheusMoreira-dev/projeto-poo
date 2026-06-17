package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.utils.TerminalUtils;
import br.com.matheus.projetopoo.views.terminal.FuncionarioViewTerminal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FuncionarioController implements TerminalController{
    private final FuncionarioDAO dao = new FuncionarioDAO();
    private final FuncionarioViewTerminal view = new FuncionarioViewTerminal();
    private final SetorDAO daoSetor = new SetorDAO();

    @Override
    public void create() {
        try {
            Funcionario f = view.create();
            Optional<Setor> setor = daoSetor.getById(f.getSetorId());

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
        Funcionario f = view.edit();

        try {
            boolean existsFuncionario = dao.confirmExistence(f.getId());

            if (!existsFuncionario) {
                view.errorMsg("Não existe nenhum funcionário com esse Id");
                return;
            }

            boolean existsSetor = daoSetor.confirmExistence(f.getSetorId());

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
            view.errorMsg("Erro ao carregar todos os setores cadastrados!");
        }
    }

    @Override
    public void exit() {
        view.exit();
        input.close();
    }

    @Override
    public void startMenu() {
        int opt = -1;
        String menu = """
                Funcionário
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
            input.nextLine();

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