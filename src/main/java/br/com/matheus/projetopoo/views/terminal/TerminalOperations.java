package br.com.matheus.projetopoo.views.terminal;

import java.util.Scanner;

public interface TerminalOperations {
    Scanner input = new Scanner(System.in);

    default void successMsg(String title){
        System.out.println("Sucesso: " + title);
    }

    default void successMsg(String title, String description){
        System.out.println("Sucesso: " + title);
        System.out.println("\n" + description);
    }

    default void errorMsg(String title) {
        System.err.println("Erro: " + title);
    }

    default void errorMsg(String title, String description){
        System.err.println("Erro: " + title);
        System.err.println("\n" + description);
    }

    default boolean confirmExec(String title, String description){
        System.out.println("\n" + title);
        System.out.println("\n" +  description + "\n");
        System.out.println("0 - Confirmar   |   1 - Cancelar");
        System.out.print("> ");
        int opt = input.nextInt();

        if (opt != 0 && opt != 1){
            System.out.println("Digite uma opção válida !");
            confirmExec(title, description);
        }

        return opt == 0;
    }

    default boolean confirmExec(String title){
        System.out.println("\n" + title);
        System.out.println("0 - Confirmar   |   1 - Cancelar");
        System.out.print("> ");
        int opt = input.nextInt();

        if (opt != 0 && opt != 1){
            System.out.println("Digite uma opção válida !");
            confirmExec(title);
        }

        return opt == 0;
    }

    default void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    default void exit(){
        System.out.println("Saindo...");
    };
}
