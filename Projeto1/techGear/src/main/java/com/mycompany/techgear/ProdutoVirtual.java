package com.mycompany.techgear;

public class ProdutoVirtual extends Produto {
    private double tamanhoArquivo;
    private String formato;
    
    public ProdutoVirtual() {
        super();
    }
    
    public ProdutoVirtual(
            int id, String nome, double preco, String descricao, String marca,
            Categoria categoria, double tamanhoArquivo, String formato, int estoque
    ) {
        super(id, nome, preco, descricao, marca, categoria, estoque);
        
        this.tamanhoArquivo = tamanhoArquivo;
        this.formato = formato;
    }
    
    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    
    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }
    
    public String getFormato() {
        return formato;
    }
    
    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    public void realizarDownload() {
        // TODO
    }
    
    public String toLine() {
        return getId() + "#" + getNome() + "#" + getPreco() + "#"
            + getDescricao() + "#" + getMarca() + "#" + getCategoria().getCodigo() + "#"
            + tamanhoArquivo + " GB" + "#" + formato + "#" + getEstoque() + "\n";
    }
}
