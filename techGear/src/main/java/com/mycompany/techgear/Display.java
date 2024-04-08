package com.mycompany.techgear;

public abstract class Display {
    protected Loja loja;
    protected Input input;
    
    public Display() {
        loja = null;
        input = new Input();
    }
    
    public Display(Loja loja) {
        this();
        this.loja = loja;
    }
    
    protected void printHeader() {
        System.out.println("----------------------------------");
        System.out.println(loja.getNome());
        System.out.println("----------------------------------");
        System.out.println("Digite a operacao que deseja fazer: ");
    }
}
