package com.mycompany.techgear;

import java.util.Scanner;

public class Input {
    Scanner scanner;
    
    public Input() {
        scanner = new Scanner(System.in);
    }
    
    public int getIntInput() {
        return getIntInput(0, Integer.MAX_VALUE);
    }
    
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
    
    public double getDoubleInput() {
        return getDoubleInput(0, Double.MAX_VALUE);
    }
    
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
    
    public static boolean inputHasNum(String input) {
        return input != null && input.matches("\\d+");
    }
}
