package com.mycompany.techgear;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa a loja.
 *
 * @author RenanSPim
 */
public class Loja {

    private String nome;
    private String cnpj;
    private String endereco;
    private List<Categoria> listaCategorias;
    private String categoriaPath;
    private String produtoFisicoPath;
    private String produtoVirtualPath;
    private String lojaPath;

    /**
     * Construtor padrão da classe.
     */
    private Loja() {
        listaCategorias = new ArrayList<>();
        nome = "TechGear";
        cnpj = "13.618.897/0001-63";
        endereco = "R. Cristóvão Colombo, 2265";
    }

    /**
     * Construtor da classe Loja com parâmetros personalizados.
     *
     * @param categoriaPath O caminho do arquivo para as categorias.
     * @param fisicoPath O caminho do arquivo para os produtos físicos.
     * @param virtualPath O caminho do arquivo para os produtos virtuais.
     * @param lojaPath O caminho do arquivo para a loja.
     */
    public Loja(String categoriaPath, String fisicoPath, String virtualPath, String lojaPath) {
        this();

        this.categoriaPath = categoriaPath;
        this.produtoFisicoPath = fisicoPath;
        this.produtoVirtualPath = virtualPath;
        this.lojaPath = lojaPath;
    }

    /**
     * Retorna o nome da loja.
     *
     * @return O nome da loja.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da loja.
     *
     * @param nome O nome da loja.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o CNPJ da loja.
     *
     * @return O CNPJ da loja.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ da loja.
     *
     * @param cnpj O CNPJ da loja.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Retorna o endereço da loja.
     *
     * @return O endereço da loja.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da loja.
     *
     * @param endereco O endereço da loja.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna a lista de categorias da loja.
     *
     * @return A lista de categorias da loja.
     */
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * Adiciona uma nova categoria à lista de categorias da loja.
     *
     * @param categoria A categoria a ser adicionada.
     */
    public void adicionarCategoria(Categoria categoria) {
        if (buscarCategoria(categoria.getCodigo()) == null) {
            listaCategorias.add(categoria);
        }
    }

    /**
     * Remove uma categoria da lista de categorias da loja.
     *
     * @param categoria A categoria a ser removida.
     */
    public void removerCategoria(Categoria categoria) {
        listaCategorias.remove(categoria);
    }

    /**
     * Adiciona um produto à loja.
     *
     * @param produto O produto a ser adicionado.
     */
    public void adicionarProduto(Produto produto) {
        if (produto.getCategoria() != null) {
            produto.getCategoria().adicionarProduto(produto);
        }
    }

    /**
     * Busca uma categoria pelo nome ou código.
     *
     * @param nome O nome ou código da categoria a ser buscada.
     * @return A categoria encontrada ou null se não encontrada.
     */
    public Categoria buscarCategoria(String nome) {
        Categoria categoriaBuscada = null;
        
        if (Input.inputHasNum(nome)) {
            for (Categoria categ : listaCategorias) {
                if (categ.getCodigo() == Integer.parseInt(nome)) {
                    categoriaBuscada = categ;
                    break;
                }
            }
        } else {
            for (Categoria categ : listaCategorias) {
                if (categ.getNome().equals(nome)) {
                    categoriaBuscada = categ;
                    break;
                }
            }
        }

        return categoriaBuscada;
    }

    /**
     * Busca uma categoria pelo código.
     *
     * @param codigo O código da categoria a ser buscada.
     * @return A categoria encontrada ou null se não encontrada.
     */
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

    /**
     * Busca um produto pelo nome.
     *
     * @param nome O nome do produto a ser buscado.
     * @return O produto encontrado ou null se não encontrado.
     */
    public Produto buscarProduto(String nome) {
        Produto produtoBuscado = null;

        if (Input.inputHasNum(nome)) {
            for (Categoria categ : listaCategorias) {
                for (Produto prod : categ.listarProdutos()) {
                    if (Integer.toString(prod.getId()).equals(nome)) {
                        produtoBuscado = prod;
                        break;
                    }
                }
            }
        } else {
            for (Categoria categ : listaCategorias) {
                for (Produto prod : categ.listarProdutos()) {
                    if (prod.getNome().equals(nome)) {
                        produtoBuscado = prod;
                        break;
                    }
                }
            }
        }

        return produtoBuscado;
    }

    /**
     * Remove um produto da loja pelo seu ID.
     *
     * @param id O ID do produto a ser removido.
     */
    public void removerProduto(int id) {
        for (Categoria categ : listaCategorias) {
            categ.removerProduto(id);
        }
    }

    /**
     * Salva os dados da loja em arquivos.
     */
    public void salvar() {
        FileWriter fw;
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

        conteudo = "nome#cnpj#endereco\n";
        conteudo += nome + "#" + cnpj + "#" + endereco + "\n";

        try {
            fw = new FileWriter(lojaPath);
            fw.write(conteudo);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Loja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
