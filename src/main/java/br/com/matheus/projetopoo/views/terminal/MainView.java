package br.com.matheus.projetopoo.views.terminal;

import br.com.matheus.projetopoo.controllers.*;

import java.util.Scanner;

public abstract class MainView {
    private static final Scanner input = new Scanner(System.in);
    private static final String menu = """
            1 - Setor
            2 - Funcionário
            3 - Depósito
            4 - Item
            5 - Registros
            
            0 - Sair
            
            >
            """;

    private static void exit(){
        System.out.println("Saindo...");
    }

    public static void start(){
        int opt = -1;

        while (opt != 0) {
            System.out.print(menu);
            opt = input.nextInt();
            input.nextLine();

            switch (opt) {
                case 1: new SetorController().startMenu("Setor"); break;
                case 2: new FuncionarioController().startMenu("Funcionário"); break;
                case 3: new DepositoController().startMenu("Depósito"); break;
                case 4: new ItemController().startMenu("Item"); break;
                case 5: new RegistroController().startMenu("Registros");break;
                case 0: exit(); break;

                default:
                    System.out.println("\nDigite uma opção válida !\n");
            }
        }
    }
}
