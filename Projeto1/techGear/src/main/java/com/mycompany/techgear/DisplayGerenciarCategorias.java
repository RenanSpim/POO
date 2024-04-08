package com.mycompany.techgear;

public class DisplayGerenciarCategorias extends Display {
    private int opcao;
    private String opcStr;
    private Categoria catAux;

    public DisplayGerenciarCategorias(Loja loja) {
        super(loja);
    }
    
    public void telaGerente() {
        printHeader();
        System.out.println("1 - Adicionar categoria.");
        System.out.println("2 - Remover categoria.");
        System.out.println("3 - Editar categoria.");
        System.out.println("4 - Ver todas as categorias.");
        System.out.println("5 - Ver categoria.");
        System.out.println("0 - Voltar.");
        
        switch (opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
                return;
            case 1:
                adicionarCategoria();
                break;
            case 2:
                removerCategoria();
                break;
            case 3:
                editarCategoria();
                break;
            case 4:
                verCategorias();
                break;
            case 5:
                verCategoria();
                break;
            default:
                System.out.println("Operacao invalida, voltando para a tela do usuario...");
                return;
        }
        
        telaGerente();
    }
    
    protected static void mostrarCategoria(Categoria categoria) {
        System.out.println(categoria.toString() + ": ");
        
        for (Produto prod : categoria.listarProdutos()) {
            System.out.println("\t" + prod.toString() + "\t Estoque: " + prod.getEstoque());
        }
    }
    
    private void setCatAux(String message) {
        System.out.println(message);
        opcStr = input.getStringInput();
        
        if (Input.inputHasNum(opcStr)) {
            catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
        } else {
            catAux = loja.buscarCategoria(opcStr);
        }
    }
    
    private void adicionarCategoria() {
        System.out.println("Informe os dados da categoria que deseja criar:");
                
        System.out.println("Codigo: ");
        int codigo = input.getIntInput();

        if (loja.buscarCategoria(codigo) == null) {
            catAux.setCodigo(codigo);
        } else {
            System.out.println("Codigo em uso, retornando para a tela da gerencia...");
            return;
        }

        System.out.println("Nome: ");
        catAux.setNome(input.getStringInput());

        System.out.println("Descricao: ");
        catAux.setDescricao(input.getStringInput());

        System.out.println("Operacao bem sucedida.");
        loja.adicionarCategoria(catAux);
    }
    
    private void removerCategoria() {
        setCatAux("Informe o nome ou o codigo da categoria que deseja remover:");
        
        if (catAux == null) {
            System.out.println("A categoria nao foi encontrada, retornando para a tela da gerencia...");
            return;
        }
        
        System.out.println("[ATENCAO] Todos os produtos da categoria tambem serao removidos.");
        System.out.println("Deseja remover " + catAux.toString() + " e todos seus produtos? [S/N]");
        opcStr = input.getStringInput();

        if (opcStr.equals("S") || opcStr.equals("s")) {
            System.out.println("Removendo...");
            loja.removerCategoria(catAux);
        } else {
            System.out.println("Operacao cancelada.");
        }
    }
    
    private void editarCategoria() {
        setCatAux("Informe o nome ou o codigo da categoria que deseja editar:");
        
        if (catAux == null) {
            System.out.println("A categoria nao foi encontrada, retornando para a tela da gerencia...");
            return;
        }
        
        mostrarCategoria(catAux);
        System.out.println("Deseja edita-la? [S/N]");
        opcStr = input.getStringInput();

        if (!opcStr.equals("S") && !opcStr.equals("s")) {
            System.out.println("Operacao cancelada.");
            return;
        }

        System.out.println("O campo a ser editado: ");
        System.out.println("1 - Nome.");
        System.out.println("2 - Descricao.");
        opcao = input.getIntInput();

        switch (opcao) {
            case 1:
                catAux.setNome(input.getStringInput());
                break;
            case 2:
                catAux.setDescricao(input.getStringInput());
                break;
            default:
                System.out.println("Nada mudado.");
                return;
        }
        
        System.out.println("Categoria alterada com sucesso.");
    }
    
    private void verCategorias() {
        for (Categoria categ : loja.getListaCategorias()) {
            System.out.println(categ.toString());
        }
    }
    
    private void verCategoria() {
        setCatAux("Informe o nome ou o codigo da categoria que deseja ver:");

        if (catAux == null) {
            System.out.println("Categoria nao encontrada, retornando para a tela da gerencia...");
            return;
        }

        mostrarCategoria(catAux);
    }
}
