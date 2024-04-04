package com.mycompany.techgear;

public class ProdutoFisico extends Produto {
    private double peso;
    private String dimensoes;
    
    public ProdutoFisico() {
        super();
    }
    
    public ProdutoFisico(
            int id, String nome, double preco, String descricao, String marca,
            Categoria categoria, double peso, String dimensoes, int estoque
    ) {
        super(id, nome, preco, descricao, marca, categoria, estoque);
        
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
    
    public boolean setDimensoes(String dimensoes) {
        for (String str : dimensoes.split("x")) {
            if (str.matches("\\D+")) {
                return false;
            }
        }
        
        this.dimensoes = dimensoes;
        return true;
    }
    
    public double calcularFrete() {
        // TODO
        return 0.0;
    }
    
    public String toLine() {
        return getId() + "#" + getNome() + "#" + getPreco() + "#"
            + getDescricao() + "#" + getMarca() + "#" + getCategoria().getCodigo() + "#"
            + peso + "#" + dimensoes + "#" + getEstoque() + "\n";
    }
}
