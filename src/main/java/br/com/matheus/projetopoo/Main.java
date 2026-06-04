package br.com.matheus.projetopoo;

import br.com.matheus.projetopoo.DAO.PrestadorDAO;
import br.com.matheus.projetopoo.models.Prestador;

import java.sql.SQLException;

public class Main {
    static void teste(){
        PrestadorDAO p = new PrestadorDAO();
        Prestador prestador = new Prestador();

        prestador.setCnpj("12345678912346");
        prestador.setAtivo(false);
        prestador.setNomeFantasia("Outro PRESTADOR");
        prestador.setRazaoSocial("dsadasd");

        p.criar(prestador);
    }

    static void main() throws SQLException {
        teste();
    }
}
