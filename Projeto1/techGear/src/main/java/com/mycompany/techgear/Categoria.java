package com.mycompany.techgear;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private int codigo;
    private String nome;
    private String descricao;
    private List<Produto> listaProdutos;
    
    public Categoria() {
        listaProdutos = new ArrayList<>();
    }
    
    public Categoria(int codigo, String nome, String descricao) {
        this();
        
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }
    
    public void removerProduto(int id) {
       Produto prod = buscarProduto(id);
       
       if (prod != null) {
           listaProdutos.remove(prod);
       }
    }
    
    public Produto buscarProduto(int id) {
        Produto produtoBuscado = null;
        
        for (Produto prod : listaProdutos) {
            if (prod.getId() == id) {
                produtoBuscado = prod;
                break;
            }
        }
        
        return produtoBuscado;
    }
    
    public List<Produto> listarProdutos() {
        return listaProdutos;
    }
    
    @Override
    public String toString() {
        return nome + " (" + codigo + ")";
    }
    
    public String toLine() {
        return codigo + "#" + nome + "#" + descricao + "\n";
    }
}
