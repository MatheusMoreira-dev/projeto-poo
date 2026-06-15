package br.com.matheus.projetopoo.views.terminal;

import java.util.List;
import java.util.Scanner;

public interface ViewTerminal<ClassModel> {
    Scanner input = new Scanner(System.in);

    ClassModel create();

    default void showItem(ClassModel c){
        System.out.println(c);
    };

    default void showAll(List<ClassModel> l) {
        l.forEach(System.out::println);
    };

    default void execCompleted(String title){
        System.out.println("Execução completa: " + title);
    }

    default void failedExec(String title) {
        System.out.println("Falha na execução, tente novamente: " + title);
    }

    ClassModel edit();

    default Integer delete() {
        System.out.println("Digite o ID: ");
        return input.nextInt();
    };

    default boolean confirmDelete (ClassModel c){
        System.out.println("Tem certeza que deseja excluir ?");
        showItem(c);

        System.out.println("0 - Confirmar   |   1 - Cancelar");
        int opt = input.nextInt();

        if (opt != 0 && opt != 1){
            System.out.println("Digite uma opção válida !");
            confirmDelete(c);
        }

        return opt == 0;
    }

    default void exit(){
        System.out.println("Saindo...");
    };
    /*default void mainMenu() {
        int opt = -1;
        String menu = """
                1 - Criar
                2 - Mostrar Item
                3 - Motrar todos os registros
                4 - Editar
                5 - Deletar
                
                0 - Sair
                """;

        while (opt != 0) {
            System.out.println(menu);
            opt = input.nextInt();

            switch (opt) {
                case 1: create(); break;
                case 2: showItem(); break;
                case 3: showAll(); break;
                case 4: edit(); break;
                case 5: delete(); break;
                case 0: exit(); break;

                default:
                    System.out.println("Digite uma opção válida !");
            }
        }
    };*/
}
