package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.DAO.SetorDAO;
import br.com.matheus.projetopoo.models.Setor;
import br.com.matheus.projetopoo.views.terminal.SetorView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    static void teste() {
        SetorDAO dao = new SetorDAO();
        List<String> colunas = Arrays.asList("nome");
        List<Object> valores = Arrays.asList("Novo Setor");

        dao.update(6, colunas, valores);
    }

    static void main() throws SQLException {
        teste();
    }
}
