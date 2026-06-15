package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.views.terminal.SetorViewTerminal;

import java.sql.SQLException;

public class Main {
    static void teste() {
        SetorViewTerminal view = new SetorViewTerminal();
        view.create();
    }

    public static void main(String[] args) throws SQLException {
        teste();
    }
}
