package br.com.matheus.projetopoo.views.terminal;

import java.util.List;
import java.util.Scanner;

public interface ViewTerminalCRUD<ClassModel> extends BasicsTerminalView {
    ClassModel create();

    default void showItem(ClassModel c){
        System.out.println("\n" + c + "\n");
    };

    default void showAll(List<ClassModel> l) {
        System.out.println("\nTotal: " + l.size());
        System.out.println("----------------------------");
        l.forEach(System.out::println);
        System.out.println("\n");
    };

    ClassModel edit();

    default Integer requestId() {
        System.out.println("Digite o ID: ");
        Integer id = input.nextInt();
        input.nextLine();

        return id;
    };

    default boolean delete(ClassModel c){
        return confirmExec("Tem certeza que deseja excluir ?", c.toString());
    }

    default void exit(){
        System.out.println("Saindo...");
    };
}