package br.com.matheus.projetopoo.controllers;

import java.util.Scanner;

public interface TerminalController {
    Scanner input = new Scanner(System.in);

    void create();

    void delete();

    void edit();

    void get();

    void getAll();

    void exit();

    default void startMenu(String titulo){
        int opt = -1;
        String menu = """
                %s
                ----------------------------------------------
                1 - Criar %s
                2 - Mostrar %s
                3 - Motrar todos
                4 - Editar %s
                5 - Deletar %s
                
                0 - Voltar
                -------------------------------------------
                
                Opção:
                """.formatted(titulo, titulo, titulo, titulo, titulo);

        while (opt != 0) {
            System.out.print(menu);
            opt = input.nextInt();

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