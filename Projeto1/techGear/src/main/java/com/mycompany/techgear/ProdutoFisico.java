package com.mycompany.techgear;

public class ProdutoFisico extends Produto {
    private double peso;
    private String dimensoes;
    
    public ProdutoFisico() {
        super();
    }
    
    public ProdutoFisico(
            int id, String nome, double preco, String descricao, String marca,
            Categoria categoria, double peso, String dimensoes
    ) {
        super(id, nome, preco, descricao, marca, categoria);
        
        this.peso = peso;
        this.dimensoes = dimensoes;
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        }
    }
    
    public String getDimensoes() {
        return dimensoes;
    }
    
    public void setDimensoes(String dimensoes) {
        for (String str : dimensoes.split("x")) {
            if (str.matches("\\D+")) {
                return;
            }
        }
        
        this.dimensoes = dimensoes;
    }
    
    public double calcularFrete() {
        // TODO
        return 0.0;
    }
}
