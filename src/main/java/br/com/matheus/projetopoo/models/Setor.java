package br.com.matheus.projetopoo.models;

public class Setor extends Model {
    private String nome;

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    @Override
    public String toString() {
        return "id: %d | nome: %s".formatted(getId(), nome);
    }
}
