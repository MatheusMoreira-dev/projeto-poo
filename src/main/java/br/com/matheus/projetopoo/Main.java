package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.controllers.FuncionarioController;
import br.com.matheus.projetopoo.controllers.TerminalController;

public class Main {
    static void teste() {
        TerminalController c = new FuncionarioController();
        c.startMenu();
    }

    public static void main(String[] args){
        teste();
    }
}