package com.mycompany.techgear;

public class TechGear {
    public static void main(String[] args) {
        String categoriasPath = "./categorias.txt";
        String fisicosPath = "./produtoFisico.txt";
        String virtuaisPath = "./produtoVirtual.txt";
        
        DisplayLoja dpLoja = new DisplayLoja(
            categoriasPath, fisicosPath, virtuaisPath
        );
        
        dpLoja.telaUsuario();
    }
}
