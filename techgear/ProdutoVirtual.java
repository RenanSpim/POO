package com.mycompany.techgear;

/**
 * Representa um produto virtual na loja.
 *
 * @author RenanSPim
 */
public class ProdutoVirtual extends Produto {

    private double tamanhoArquivo;
    private String formato;

    /**
     * Construtor padrão da classe ProdutoVirtual.
     */
    public ProdutoVirtual() {
        super();
    }

    /**
     * Construtor parametrizado da classe ProdutoVirtual.
     *
     * @param id O ID do produto.
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     * @param descricao A descrição do produto.
     * @param marca A marca do produto.
     * @param categoria A categoria do produto.
     * @param tamanhoArquivo O tamanho do arquivo do produto em gigabytes.
     * @param formato O formato do arquivo do produto.
     * @param estoque O estoque do produto.
     */
    public ProdutoVirtual(
            int id, String nome, double preco, String descricao, String marca,
            Categoria categoria, double tamanhoArquivo, String formato, int estoque
    ) {
        super(id, nome, preco, descricao, marca, categoria, estoque);

        this.tamanhoArquivo = tamanhoArquivo;
        this.formato = formato;
    }

    /**
     * Obtém o tamanho do arquivo do produto.
     *
     * @return O tamanho do arquivo do produto em gigabytes.
     */
    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    /**
     * Define o tamanho do arquivo do produto.
     *
     * @param tamanhoArquivo O tamanho do arquivo do produto em gigabytes.
     */
    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    /**
     * Obtém o formato do arquivo do produto.
     *
     * @return O formato do arquivo do produto.
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Define o formato do arquivo do produto.
     *
     * @param formato O formato do arquivo do produto.
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * Simula o download do produto, reduzindo o estoque em 1 unidade.
     */
    public void realizarDownload() {
        atualizarEstoque(-1);
    }

    /**
     * @return o produto no formato da base de dados.
     */
    @Override
    public String toLine() {
        return getId() + "#" + getNome() + "#" + getPreco() + "#"
                + getDescricao() + "#" + getMarca() + "#" + getCategoria().getCodigo() + "#"
                + tamanhoArquivo + " GB" + "#" + formato + "#" + getEstoque() + "\n";
    }
}
