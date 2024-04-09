package com.mycompany.techgear;

/**
 * Classe abstrata que representa um produto na loja.
 */
public abstract class Produto {

    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private String marca;
    private Categoria categoria;
    private int estoque;

    /**
     * Construtor padrão da classe Produto. Inicializa o estoque com zero.
     */
    public Produto() {
        estoque = 0;
    }

    /**
     * Construtor parametrizado da classe Produto.
     *
     * @param id O ID do produto.
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     * @param descricao A descrição do produto.
     * @param marca A marca do produto.
     * @param categoria A categoria do produto.
     * @param estoque O estoque do produto.
     */
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

    /**
     * Retorna o ID do produto.
     *
     * @return O ID do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id O ID do produto.
     * @return true se o ID foi definido com sucesso, false caso contrário.
     */
    public boolean setId(int id) {
        if (categoria.buscarProduto(id) == null) {
            this.id = id;
            return true;
        }

        return false;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome O nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return O preço do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco O preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao A descrição do produto.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a marca do produto.
     *
     * @return A marca do produto.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do produto.
     *
     * @param marca A marca do produto.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return A categoria do produto.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria A categoria do produto.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna o estoque do produto.
     *
     * @return O estoque do produto.
     */
    public int getEstoque() {
        return estoque;
    }

    /**
     * Define o estoque do produto.
     *
     * @param estoque O estoque do produto.
     */
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    /**
     * Atualiza o estoque do produto com a quantidade fornecida.
     *
     * @param quantidade A quantidade a ser adicionada ou removida do estoque.
     */
    public void atualizarEstoque(int quantidade) {
        if (estoque + quantidade > 0) {
            estoque += quantidade;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Atualiza o preço do produto para o novo preço fornecido, se for maior que
     * zero.
     *
     * @param novoPreco O novo preço do produto.
     */
    public void atualizarPreco(double novoPreco) {
        if (novoPreco > 0) {
            preco = novoPreco;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Retorna uma representação em string do produto.
     *
     * @return Uma representação em string do produto.
     */
    @Override
    public String toString() {
        return "(" + id + ") " + nome + " $" + Double.toString(preco);
    }

    /**
     * Retorna uma representação em linha do produto.
     *
     * @return Uma representação em linha do produto.
     */
    public abstract String toLine();
}
