package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.ConnectionFactory;
import br.com.matheus.projetopoo.models.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetorDAO extends BaseDAO<Setor> {
    public SetorDAO() {
        super("setor", "id");
    }

    @Override
    public Setor create(Setor c) {
        String sql = "INSERT INTO " + getNomeTabela() + " VALUES " + "(NULL, ?)";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, c.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao criar registro na tabela " + getNomeTabela());
        }

        return c;
    }

    @Override
    public Setor update(Setor c) {
        return null;
    }

    @Override
    Setor deserializer(ResultSet r) {
        try {
            Setor s = new Setor();
            s.setNome(r.getString(1));

            return s;

        } catch (SQLException e) {
            System.out.println("Erro ao desseralizar: " + e);
        }

        return null;
    }
}
