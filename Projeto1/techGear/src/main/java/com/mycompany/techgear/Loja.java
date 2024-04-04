package com.mycompany.techgear;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loja {
    private String nome;
    private String cnpj;
    private String endereco;
    private List<Categoria> listaCategorias;
    private String categoriaPath;
    private String produtoFisicoPath;
    private String produtoVirtualPath;
    
    private Loja() {
        listaCategorias = new ArrayList<>();
        nome = "Lojinha que vai falir o uai";
        cnpj = "13.618.897/0001-63";
        endereco = "R. Cristóvão Colombo, 2265 - Jardim Nazareth, São José do Rio Preto - SP, 15054-000";
    }
    
    public Loja(String categoriaPath, String fisicoPath, String virtualPath) {
        this();
        
        this.categoriaPath = categoriaPath;
        this.produtoFisicoPath = fisicoPath;
        this.produtoVirtualPath = virtualPath;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }
    
    public void adicionarCategoria(Categoria categoria) {
        if (buscarCategoria(categoria.getCodigo()) == null) {
            listaCategorias.add(categoria);
        }
    }
    
    public void removerCategoria(Categoria categoria) {
        listaCategorias.remove(categoria);
    }
    
    public void adicionarProduto(Produto produto) {
        if (produto.getCategoria() != null) {
            produto.getCategoria().adicionarProduto(produto);
        }
    }
    
    public Categoria buscarCategoria(String nome) {
        Categoria categoriaBuscada = null;
        
        for (Categoria categ : listaCategorias) {
            if (categ.getNome().equals(nome)) {
                categoriaBuscada = categ;
                break;
            }
        }
        
        return categoriaBuscada;
    }

    public Categoria buscarCategoria(int codigo) {
        Categoria categoriaBuscada = null;
        
        for (Categoria categ : listaCategorias) {
            if (categ.getCodigo() == codigo) {
                categoriaBuscada = categ;
                break;
            }
        }
        
        return categoriaBuscada;
    }
    
    public Produto buscarProduto(int id) {
        Produto produtoBuscado = null;
        
        for (Categoria categ : listaCategorias) {
            for (Produto prod : categ.listarProdutos()) {
                if (prod.getId() == id) {
                    produtoBuscado = prod;
                    break;
                }
            }
            
            if (produtoBuscado != null) {
                break;
            }
        }
        
        return produtoBuscado;
    }
    
    public Produto buscarProduto(String nome) {
        Produto produtoBuscado = null;
        
        for (Categoria categ : listaCategorias) {
            for (Produto prod : categ.listarProdutos()) {
                if (prod.getNome().equals(nome)) {
                    produtoBuscado = prod;
                    break;
                }
            }
            
            if (produtoBuscado != null) {
                break;
            }
        }
        
        return produtoBuscado;
    }
    
    public void removerProduto(int id) {
        for (Categoria categ : listaCategorias) {
            categ.removerProduto(id);
        }
    }
    
    public void salvar() {
        FileWriter fw = null;
        String conteudo, contAux;
        
        conteudo = "cod#nome#descricao\n";
        
        for (Categoria categoria : listaCategorias) {
            conteudo += categoria.toLine();
        }
        
        try {
            fw = new FileWriter(categoriaPath);
            fw.write(conteudo);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Loja.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        conteudo = "ID#nome#preco#descricao#marca#categoria#tamanhoArquivo#formato#estoque\n";
        contAux = "ID#nome#preco#descricao#marca#categoria#peso#dimensoes#estoque\n";
        
        for (Categoria categoria : listaCategorias) {
            for (Produto produto : categoria.listarProdutos()) {
                if (produto instanceof ProdutoVirtual) {
                    conteudo += produto.toLine();
                } else {
                    contAux += produto.toLine();
                }
            }
        }
        
        try {
            fw = new FileWriter(produtoFisicoPath);
            fw.write(contAux);
            fw.close();
            
            fw = new FileWriter(produtoVirtualPath);
            fw.write(conteudo);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Loja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
