package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;

import java.sql.SQLException;

public class Main {
    static void teste() {
        Setor s = new Setor();
        s.setNome("Matheus");

        SetorDAO sd = new SetorDAO();
        sd.create(s);
    }

    static void main() throws SQLException {
        teste();
    }
}
