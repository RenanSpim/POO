package com.mycompany.techgear;

/**
 * Classe principal que inicia o sistema TechGear.
 *
 * @author RenanSPim
 */
public class TechGear {

    /**
     * Método principal que inicia o sistema TechGear.
     *
     * @param args Os argumentos da linha de comando (não utilizados neste
     * caso).
     */
    public static void main(String[] args) {
        String categoriasPath = "./categorias.txt";
        String fisicosPath = "./produtoFisico.txt";
        String virtuaisPath = "./produtoVirtual.txt";
        String lojaPath = "./loja.txt";

        DisplayLoja dpLoja = new DisplayLoja(
                categoriasPath, fisicosPath, virtuaisPath, lojaPath
        );

        dpLoja.tela();
    }
}
