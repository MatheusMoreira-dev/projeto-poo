package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.views.terminal.SetorView;

import java.sql.SQLException;

public class Main {
    static void teste() {
        SetorView view = new SetorView();
        view.create();
    }

    public static void main(String[] args) throws SQLException {
        teste();
    }
}
