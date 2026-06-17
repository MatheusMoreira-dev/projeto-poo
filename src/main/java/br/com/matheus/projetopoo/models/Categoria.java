package br.com.matheus.projetopoo.models;

public enum Categoria {
    PAPELARIA(1, "Papelaria"),
    ELETRONICO(2, "Eletrônico"),
    INSTITUCIONAL(3, "Institucional"),
    ROUPAS_E_ACESSORIOS(4, "Roupas e Acessórios"),
    BRINDES(5, "Brindes"),
    DECORACAO(6, "Decoração");

    private final int codigo;
    private final String nome;

    Categoria(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
