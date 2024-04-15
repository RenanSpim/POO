package com.mycompany.techgear;

/**
 * Representa um produto físico na loja.
 *
 * @author RenanSPim
 */
public class ProdutoFisico extends Produto {

    private double peso;
    private double dimensoes[];

    /**
     * Construtor padrão da classe ProdutoFisico. Inicializa o array de
     * dimensões com valores padrão.
     */
    public ProdutoFisico() {
        super();
        dimensoes = new double[3];
    }

    /**
     * Construtor parametrizado da classe ProdutoFisico.
     *
     * @param id O ID do produto.
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     * @param descricao A descrição do produto.
     * @param marca A marca do produto.
     * @param categoria A categoria do produto.
     * @param peso O peso do produto.
     * @param dimensoes As dimensões do produto.
     * @param estoque O estoque do produto.
     */
    public ProdutoFisico(
            int id, String nome, double preco, String descricao, String marca,
            Categoria categoria, double peso, String dimensoes, int estoque
    ) {
        super(id, nome, preco, descricao, marca, categoria, estoque);

        String[] dimArr = dimensoes.split("x");

        this.peso = peso;
        this.dimensoes = new double[3];

        for (int i = 0; i < 3; i++) {
            this.dimensoes[i] = Double.parseDouble(dimArr[i]);
        }
    }

    /**
     * Obtém o peso do produto.
     *
     * @return O peso do produto.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Define o peso do produto.
     *
     * @param peso O peso do produto.
     */
    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        }
    }

    /**
     * Obtém as dimensões do produto.
     *
     * @return Um array contendo as dimensões do produto (largura, altura e
     * profundidade).
     */
    public double[] getDimensoes() {
        return dimensoes;
    }

    /**
     * Converte as dimensões do produto em uma string.
     *
     * @return Uma string representando as dimensões do produto.
     */
    public String getDimensoesStr() {
        String dimStr = "";

        for (double d : dimensoes) {
            dimStr += "x" + Double.toString(d);
        }

        return dimStr.substring(1);
    }

    /**
     * Define as dimensões do produto.
     *
     * @param dimensoes As dimensões do produto no formato "largura x altura x
     * profundidade".
     * @return true se as dimensões foram definidas com sucesso, false caso
     * contrário.
     */
    public boolean setDimensoes(String dimensoes) {
        String[] dimArr = dimensoes.split("x");

        for (String str : dimArr) {
            if (str.matches("\\D+")) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            this.dimensoes[i] = Double.parseDouble(dimArr[i]);
        }

        return true;
    }

    /**
     * Calcula o frete do produto baseado na distância.
     *
     * @param distancia A distância para o envio do produto.
     * @return O valor do frete.
     */
    public double calcularFrete(double distancia) {
        double volume = dimensoes[0] * dimensoes[1] * dimensoes[2];
        double taxaVariavel = (volume / 100_000) * (peso / 100) * distancia;
        double taxaFixa = (volume / 10_000) * (peso / 10);

        return Math.round((taxaVariavel + taxaFixa) * 100) / 100.0;
    }
    
    /**
     * @return o produto no formato da base de dados.
     */
    @Override
    public String toLine() {
        return getId() + "#" + getNome() + "#" + getPreco() + "#"
                + getDescricao() + "#" + getMarca() + "#" + getCategoria().getCodigo() + "#"
                + peso + "#" + getDimensoesStr() + "#" + getEstoque() + "\n";
    }
}
