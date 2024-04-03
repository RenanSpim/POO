package com.mycompany.techgear;

import java.io.IOException;

public class TechGear {
    public static void main(String[] args) throws IOException {
        String categoriasPath = "./categorias.txt";
        String fisicosPath = "./produtoFisico.txt";
        String virtuaisPath = "./produtoVirtual.txt";
        
        DisplayLoja dpLoja = new DisplayLoja(
            categoriasPath, fisicosPath, virtuaisPath
        );
        
        dpLoja.telaUsuario();
    }
}
