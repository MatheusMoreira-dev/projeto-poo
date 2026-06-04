package br.com.matheus.projetopoo.models;

public abstract class BaseModel {
    private String nameTable;

    BaseModel(String nameTable){this.nameTable = nameTable;}

    public String getNameTable(){
        return nameTable;
    };
}