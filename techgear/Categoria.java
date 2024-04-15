package com.mycompany.techgear;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma categoria de produtos.
 *
 * @author RenanSPim
 */
public class Categoria {

    private int codigo;
    private String nome;
    private String descricao;
    private List<Produto> listaProdutos;

    /**
     * Construtor vazio para inicialização da lista de produtos.
     */
    public Categoria() {
        listaProdutos = new ArrayList<>();
    }

    /**
     * Construtor público da classe Categoria.
     *
     * @param codigo O código da categoria.
     * @param nome O nome da categoria.
     * @param descricao A descrição da categoria.
     */
    public Categoria(int codigo, String nome, String descricao) {
        this();

        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Obtém o código da categoria.
     *
     * @return O código da categoria.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código da categoria.
     *
     * @param codigo O código da categoria.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o nome da categoria.
     *
     * @return O nome da categoria.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nome O nome da categoria.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição da categoria.
     *
     * @return A descrição da categoria.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da categoria.
     *
     * @param descricao A descrição da categoria.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Adiciona um produto à lista de produtos da categoria.
     *
     * @param produto O produto a ser adicionado.
     */
    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    /**
     * Remove um produto da lista de produtos da categoria com base no seu ID.
     *
     * @param id O ID do produto a ser removido.
     */
    public void removerProduto(int id) {
        Produto prod = buscarProduto(id);

        if (prod != null) {
            listaProdutos.remove(prod);
        }
    }

    /**
     * Busca um produto na lista de produtos da categoria com base no seu ID.
     *
     * @param id O ID do produto a ser buscado.
     * @return O produto encontrado, ou null se não for encontrado.
     */
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

    /**
     * Obtém a lista de produtos da categoria.
     *
     * @return A lista de produtos da categoria.
     */
    public List<Produto> listarProdutos() {
        return listaProdutos;
    }

    /**
     * Retorna uma representação dos dados da categoria em formato de linha para
     * armazenamento.
     *
     * @return Uma string contendo os dados da categoria formatados para
     * armazenamento.
     */
    public String toLine() {
        return codigo + "#" + nome + "#" + descricao + "\n";
    }

    /**
     * Retorna uma representação em formato de string da categoria.
     *
     * @return Uma string representando a categoria no formato "(código) Nome".
     */
    @Override
    public String toString() {
        return "(" + codigo + ") " + nome;
    }
}
