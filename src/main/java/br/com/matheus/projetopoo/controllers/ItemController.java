package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.DAO.FuncionarioDAO;
import br.com.matheus.projetopoo.DAO.ItemDAO;
import br.com.matheus.projetopoo.models.Categoria;
import br.com.matheus.projetopoo.models.Funcionario;
import br.com.matheus.projetopoo.models.Item;
import br.com.matheus.projetopoo.views.terminal.ItemView;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemController implements TerminalController{
    private final ItemDAO DAO = new ItemDAO();
    private final FuncionarioDAO FUNCIONARIO_DAO = new FuncionarioDAO();
    private final ItemView VIEW = new ItemView();

    @Override
    public void create() {
        try {
            Item i = VIEW.create();
            Optional<Funcionario> setor = FUNCIONARIO_DAO.getById(i.getFuncionarioId());

            if (setor.isEmpty()) {
                VIEW.errorMsg("Não existe nenhum funcionário com esse id!");
                return;
            }

            DAO.insert(i);
            VIEW.successMsg("Item cadastrado com sucesso!");


        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar operação!");
        }
    }

    @Override
    public void delete() {
        Integer id = VIEW.requestId();

        try {
            Optional<Item> s = DAO.getById(id);

            if (s.isEmpty()){
                VIEW.errorMsg("Não há nenhum item com esse id.");
                return;
            }

            boolean deleted = DAO.delete(s.get().getId());

            if (!deleted){
                VIEW.successMsg("Operação cancelada!");
                return;
            }

            VIEW.successMsg("Operação bem sucedida!");

        } catch (SQLException e) {
            VIEW.errorMsg("Falha na execução!");
        }
    }

    @Override
    public void edit() {
        try {
            Item i = VIEW.edit();
            boolean existsItem = DAO.confirmExistence(i.getId());

            if (!existsItem) {
                VIEW.errorMsg("Não existe nenhum item com esse Id");
                return;
            }

            boolean existsFuncionario = FUNCIONARIO_DAO.confirmExistence(i.getFuncionarioId());

            if (!existsFuncionario) {
                VIEW.errorMsg("Não existe nenhum funcionário com esse Id");
                return;
            }

            DAO.update(i);
            VIEW.successMsg("Item editado com sucesso!");

        } catch (SQLException e){
            VIEW.errorMsg("Não foi possível editar o Item!");
        }
    }

    @Override
    public void get() {
        Integer id = VIEW.requestId();

        try {
            Optional<Item> item = DAO.getById(id);

            if (item.isEmpty()) {
                VIEW.errorMsg("Não há nenhum item com esse Id");
                return;
            }

            VIEW.showItem(item.get());

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar operação de busca!");
        }
    }

    @Override
    public void getAll() {
        try {
            List<Item> list = DAO.getAll();
            VIEW.showAll(list);

        } catch (SQLException e) {
            VIEW.errorMsg("Erro ao carregar todos os itens cadastrados!");
        }
    }

    public void filterByCategeoria(){
        Categoria c = VIEW.getCategoria();

        try {
            List<Item> list = DAO.filterByCategoria(c);

            if(list.isEmpty()){
                VIEW.errorMsg("Não há nenhum item com essa categoria!");
                return;
            }

            list.forEach(System.out::println);

        } catch (SQLException e) {
            VIEW.errorMsg("Não foi possível realizar a consulta!");
        }
    }

    @Override
    public void exit() {
        VIEW.exit();
    }

    @Override
    public void startMenu(String titulo){
        int opt = -1;
        String menu = """
                %s
                ----------------------------------------------
                1 - Criar %s
                2 - Mostrar %s
                3 - Motrar todos
                4 - Editar %s
                5 - Deletar %s
                6 - Filtrar por categoria
                
                0 - Voltar
                -------------------------------------------
                
                Opção:
                """.formatted(titulo, titulo, titulo, titulo, titulo);

        while (opt != 0) {
            System.out.print(menu);
            opt = input.nextInt();

            switch (opt) {
                case 1: create(); break;
                case 2: get(); break;
                case 3: getAll(); break;
                case 4: edit(); break;
                case 5: delete(); break;
                case 6: filterByCategeoria();
                case 0: exit(); break;

                default:
                    System.out.println("\nDigite uma opção válida !\n");
            }
        }
    };
}
