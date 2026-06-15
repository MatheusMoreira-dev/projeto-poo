package br.com.matheus.projetopoo.views.terminal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public interface ViewTerminal<ClassModel> {
    Scanner input = new Scanner(System.in);

    ClassModel create();

    Optional<ClassModel> getItem();

    List<ClassModel> getAll();

    ClassModel edit();

    boolean delete();

    void exit();

    default void mainMenu() {
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
    };
}
