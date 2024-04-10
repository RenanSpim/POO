package com.mycompany.techgear;

import java.util.Scanner;

/**
 * Classe responsável por obter o input do usuário.
 *
 * @author RenanSPim
 */
public class Input {

    private Scanner scanner;

    /**
     * Construtor da classe.
     */
    public Input() {
        scanner = new Scanner(System.in);
    }

    /**
     * Obtém um número inteiro fornecido pelo usuário.
     *
     * @return O número inteiro fornecido pelo usuário.
     */
    public int getIntInput() {
        return getIntInput(0, Integer.MAX_VALUE);
    }

    /**
     * Obtém um número inteiro fornecido pelo usuário dentro de um intervalo
     * específico.
     *
     * @param min O valor mínimo aceitável.
     * @param max O valor máximo aceitável.
     * @return O número inteiro fornecido pelo usuário.
     */
    public int getIntInput(int min, int max) {
        int inp;

        do {
            inp = scanner.nextInt();
            scanner.nextLine();
            if (inp < min || inp > max) {
                System.out.println("Input precisa estar entre " + min + " e " + max + ".");
            }
        } while (inp < min || inp > max);

        return inp;
    }

    /**
     * Obtém um número decimal fornecido pelo usuário.
     *
     * @return O número decimal fornecido pelo usuário.
     */
    public double getDoubleInput() {
        return getDoubleInput(0, Double.MAX_VALUE);
    }

    /**
     * Obtém um número decimal fornecido pelo usuário dentro de um intervalo
     * específico.
     *
     * @param min O valor mínimo aceitável.
     * @param max O valor máximo aceitável.
     * @return O número decimal fornecido pelo usuário.
     */
    public double getDoubleInput(double min, double max) {
        double inp;

        do {
            inp = scanner.nextDouble();
            scanner.nextLine();
            if (inp < min || inp > max) {
                System.out.println("Input precisa estar entre " + min + " e " + max + ".");
            }

        } while (inp < min || inp > max);

        return inp;
    }

    /**
     * Obtém uma string fornecida pelo usuário com pelo menos 1 caracter.
     *
     * @return A string fornecida pelo usuário.
     */
    public String getStringInput() {
        String inp;

        do {
            inp = scanner.nextLine();

            if (inp.length() == 0) {
                System.out.println("Input precisa conter pelo menos 1 caracter.");
            }

        } while (inp.length() == 0);

        return inp;
    }

    /**
     * Método estático que retorna true se houver algum dígito na entrada.
     *
     * @param input A entrada a ser verificada.
     * @return true se houver pelo menos um dígito na entrada, caso contrário
     * false.
     */
    public static boolean inputHasNum(String input) {
        return input != null && input.matches("\\d+");
    }
}
