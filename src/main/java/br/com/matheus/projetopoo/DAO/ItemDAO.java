package br.com.matheus.projetopoo.DAO;

import br.com.matheus.projetopoo.models.Categoria;
import br.com.matheus.projetopoo.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends DAO<Item>{
    public ItemDAO() {
        super("item", "id");
    }

    @Override
    public Integer insert(Item c) throws SQLException {
        List<String> colunas = List.of("nome", "descricao", "categoria", "funcionario_id");
        List<Object> valores = List.of(c.getNome(), c.getDescricao(), c.getCategoria(), c.getFuncionarioId());

        return super.insert(colunas, valores);
    }

    @Override
    public boolean update(Item c) throws SQLException {
        List<String> colunas = List.of("nome", "descricao", "categoria", "funcionario_id");
        List<Object> valores = List.of(c.getNome(), c.getDescricao(), c.getCategoria(), c.getFuncionarioId());

        return super.update(c.getId(), colunas, valores);
    }

    public List<Item> filterByCategoria(Categoria c) throws SQLException{
        String sql = "SELECT * FROM %s WHERE categoria = ?".formatted(getNomeTabela());
        List<Item> list = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){

            ResultSet result = stmt.executeQuery();

            while (result.next()){
                list.add(deserializer(result));
            }

            result.close();
        }

        return list;
    }

    @Override
    protected Item deserializer(ResultSet r) {
        try {
            Item model = new Item();
            model.setId(r.getInt("id"));
            model.setNome(r.getString("nome"));
            model.setCategoria(Categoria.valueOf(r.getString("categoria")));
            model.setDescricao(r.getString("descricao"));
            model.setFuncionarioId(r.getInt("funcionario_id"));

            return model;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
