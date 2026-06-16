package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.controllers.FuncionarioController;
import br.com.matheus.projetopoo.controllers.SetorController;

import java.sql.SQLException;

public class Main {
    static void teste() {
        SetorController c = new SetorController();
        c.startMenu();
    }

    static void main(String[] args) throws SQLException {
        teste();
    }
}