package br.com.matheus.projetopoo.controllers;

import br.com.matheus.projetopoo.utils.TerminalUtils;

import java.util.Scanner;

public interface TerminalController {
    Scanner input = new Scanner(System.in);

    void create();

    void delete();

    void edit();

    void get();

    void getAll();

    void exit();

    default void startMenu(){
        int opt = -1;
        String menu = """
                ----------------------------------------------
                1 - Criar
                2 - Mostrar Item
                3 - Motrar todos os registros
                4 - Editar
                5 - Deletar
                
                0 - Sair
                -------------------------------------------
                
                Opção:
                """;

        while (opt != 0) {
            System.out.print(menu);
            opt = input.nextInt();

            TerminalUtils.clearConsole();

            switch (opt) {
                case 1: create(); break;
                case 2: get(); break;
                case 3: getAll(); break;
                case 4: edit(); break;
                case 5: delete(); break;
                case 0: exit(); break;

                default:
                    System.out.println("\nDigite uma opção válida !\n");
            }
        }
    };
}