package com.mycompany.techgear;

public abstract class Produto {
    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private String marca;
    private Categoria categoria;
    private int estoque;
    
    public Produto() {
        estoque = 0;
    }
    
    public Produto(
            int id, String nome, double preco, String descricao, String marca,
            Categoria categoria, int estoque
    ) {
        this();
        
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.marca = marca;
        this.categoria = categoria;
        this.estoque = estoque;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean setId(int id) {
        if (categoria.buscarProduto(id) == null) {
            this.id = id;
            return true;
        }
        
        return false;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public int getEstoque() {
        return estoque;
    }
    
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    public void atualizarEstoque(int quantidade) {
        if (estoque + quantidade > 0) {
            estoque += quantidade;
        }
    }
    
    public void atualizarPreco(double novoPreco) {
        if (novoPreco > 0) {
            preco = novoPreco;
        }
    }
    
    @Override
    public String toString() {
        return nome + " (" + id + ")";
    }
    
    public abstract String toLine();
}
