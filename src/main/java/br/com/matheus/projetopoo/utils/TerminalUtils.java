package br.com.matheus.projetopoo.utils;

import java.io.IOException;

public class TerminalUtils {
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Limpa o console no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpa o console no Linux e macOS
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao tentar limpar o terminal: " + e.getMessage());
        }
    }
}