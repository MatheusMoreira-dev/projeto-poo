package br.com.matheus.projetopoo.controllers;

import java.sql.SQLException;
import java.util.Scanner;

public interface TerminalController {
    Scanner input = new Scanner(System.in);

    void create();

    void delete();

    void edit();

    void get();

    void getAll() throws SQLException;

    void exit();

    void startMenu();
}