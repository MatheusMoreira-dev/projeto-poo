package br.com.matheus.projetopoo.views.terminal;

import java.util.List;
import java.util.Scanner;

public interface ViewTerminal<ClassModel> {
    Scanner input = new Scanner(System.in);

    ClassModel create();

    default boolean finish(){
        System.out.println("0 - Ver Menu | 1 - Finalizar ");
        return input.nextInt() == 0;
    }

    default void showItem(ClassModel c){
        System.out.println("\n" + c + "\n");
    };

    default void showAll(List<ClassModel> l) {
        System.out.println("\nTotal: " + l.size());
        System.out.println("----------------------------");
        l.forEach(System.out::println);
        System.out.println("\n");
    };

    default void execCompleted(String title){
        System.out.println("Execução completa: " + title);
    }

    default void failedExec(String title) {
        System.err.println("Falha na execução, tente novamente: " + title);
    }

    ClassModel edit();

    default Integer requestId() {
        System.out.println("Digite o ID: ");
        Integer id = input.nextInt();
        input.nextLine();

        return id;
    };

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

    default boolean delete(ClassModel c){
        return confirmExec("Tem certeza que deseja excluir ?", c.toString());
    }

    default void exit(){
        System.out.println("Saindo...");
    };
}