package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.models.Item;

public class ItemViewTerminal implements ViewTerminalCRUD<Item>{
    @Override
    public Item create() {
        Item i = new Item();

        System.out.println("Nome:");
        System.out.println("Descrição:");
        System.out.println("Categoria: ");
        System.out.println("Funcionário Id: ");

        return i;
    }

    @Override
    public Item edit() {
        return null;
    }
}
