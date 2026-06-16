package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.controllers.FuncionarioController;

import java.sql.SQLException;

public class Main {
    static void teste() {
        FuncionarioController c = new FuncionarioController();
        c.create();
    }

    static void main(String[] args) throws SQLException {
        teste();
    }
}