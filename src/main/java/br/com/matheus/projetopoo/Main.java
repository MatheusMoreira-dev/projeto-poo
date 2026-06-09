package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.SetorView;

import java.sql.SQLException;

public class Main {
    static void teste() {
        SetorView s = new SetorView();
        s.main();
    }

    static void main() throws SQLException {
        teste();
    }
}
