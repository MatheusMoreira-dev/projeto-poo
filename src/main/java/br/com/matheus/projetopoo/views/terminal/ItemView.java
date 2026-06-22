package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Categoria;
import br.com.matheus.projetopoo.models.Item;

import java.util.List;

public class ItemView implements TerminalCRUD<Item> {
    @Override
    public Item create() {
        Item i = new Item();

        System.out.println("Nome:");
        String nome = input.nextLine();
        i.setNome(nome);

        System.out.println("Descrição:");
        String descricao = input.nextLine();

        if(!descricao.trim().isBlank())
            i.setDescricao(descricao);

        System.out.println("Cod da Categoria: ");
        int cod = input.nextInt();
        input.nextLine();

        try{
            Categoria categoria = Categoria.getByCod(cod);
            i.setCategoria(categoria);
        } catch (IllegalArgumentException e) {
            errorMsg("Não existe nenhuma categoria com esse id!");
        }

        System.out.println("Funcionário Id: ");
        int funcId = input.nextInt();
        input.nextLine();
        i.setFuncionarioId(funcId);

        return i;
    }

    @Override
    public Item edit() {
        Item item = create();
        int id = requestId();

        item.setId(id);

        return item;
    }

    public Categoria getCategoria(){
        List<Categoria> categorias = List.of(Categoria.values());

        System.out.println("Selecione uma categoria: ");
        categorias.forEach(System.out::println);

        System.out.println("Cod: ");
        int opt = input.nextInt();
        input.nextLine();

        try {
            return categorias.get(opt - 1);
        } catch (IllegalArgumentException e){
            errorMsg("");
        }
    }
}
