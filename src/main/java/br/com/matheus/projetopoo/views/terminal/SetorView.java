package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.controllers.Controller;
import br.com.matheus.projetopoo.controllers.SetorController;

public class SetorView implements InterfaceTerminal {
    private final SetorController controller;

    public SetorView(){
        controller = new SetorController();
    };

    @Override
    public void create() {
        System.out.println("Nome: ");
        controller.create(input.next());
    }

    @Override
    public void showItem() {

    }

    @Override
    public void showAll() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void exit() {

    }
}
