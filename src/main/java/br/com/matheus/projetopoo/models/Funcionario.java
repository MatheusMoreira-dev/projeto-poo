package br.com.matheus.projetopoo.models;

public class Funcionario extends Model {
    private String nome;
    private int setorId;

    public int getSetorId() {
        return setorId;
    }

    public void setSetorId(int setorId) {
        this.setorId = setorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", setorId=" + setorId +
                '}';
    }
}
