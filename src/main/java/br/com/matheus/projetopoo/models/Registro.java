package br.com.matheus.projetopoo.models;

import java.time.LocalDate;

public class Registro extends Model {
    private LocalDate data;
    private int itemId;
    private int funcionarioId;
    private int depositoId;
    private int quantidade;
    private String descricao;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public int getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(int depositoId) {
        this.depositoId = depositoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "id: " + getId() +
                " | data: " + data +
                " | itemId:" + itemId +
                " | funcionarioId : " + funcionarioId +
                " | depositoId: " + depositoId +
                "\nquantidade: " + quantidade +
                "\ndescricao: \n" + descricao + '\'' +
                '}';
    }
}
