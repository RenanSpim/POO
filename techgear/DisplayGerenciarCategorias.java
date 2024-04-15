package com.mycompany.techgear;

/**
 * Classe responsável pelo gerenciamento das categorias da loja. Esta classe
 * implementa a interface Display para exibir a tela de gerenciamento de
 * categorias. Ela permite adicionar, remover, editar e visualizar categorias e
 * seus produtos.
 *
 * @author RenanSPim
 */
public class DisplayGerenciarCategorias implements Display {

    private Loja loja;
    private Input input;
    private String opcStr;
    private Categoria catAux;

    /**
     * Construtor da classe.
     *
     * @param loja A loja associada a esta instância de gerenciamento de
     * categorias.
     */
    public DisplayGerenciarCategorias(Loja loja) {
        this.input = new Input();
        this.loja = loja;
    }

    /**
     * Exibe a tela principal para o gerenciamento das categorias. Permite ao
     * usuário adicionar, remover, editar, visualizar categorias e seus
     * produtos.
     */
    public void telaUsuario() {
        int opcao;

        printHeader();
        System.out.println("1 - Adicionar categoria.");
        System.out.println("2 - Remover categoria.");
        System.out.println("3 - Editar categoria.");
        System.out.println("4 - Ver todas as categorias.");
        System.out.println("5 - Ver categoria.");
        System.out.println("0 - Voltar.");

        opcao = input.getIntInput();

        try {
            switch (opcao) {
                case 0:
                    System.out.println("Voltando para a tela do usuário...");
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
                    System.out.println("Operação inválida, voltando para a tela do usuário...");
                    return;
            }
        } catch (Exception ex) {
            String[] erroMsg = new String[]
                {"Voltar", "Adicionar categoria", "Remover categoria", "Editar categoria", "Ver todas as categorias", "Ver categoria"};
            System.out.println("Um erro ocorreu na tela de gerenciar categorias...");
            System.out.println("Na acao:\t" + erroMsg[opcao]);
            return;
        }
        
        if (opcao != 0) telaUsuario();
    }

    /**
     * Exibe o cabeçalho da tela de gerenciamento de categorias.
     */
    private void printHeader() {
        System.out.println("----------------");
        System.out.println("---Categorias---");
        System.out.println("----------------");
    }

    /**
     * Define a categoria auxiliar com base no input do usuário.
     *
     * @param message A mensagem para solicitar o input do usuário.
     */
    private void setCatAux(String message) {
        System.out.println(message);
        opcStr = input.getStringInput();

        if (Input.inputHasNum(opcStr)) {
            catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
        } else {
            catAux = loja.buscarCategoria(opcStr);
        }
    }

    /**
     * Permite ao usuário adicionar uma nova categoria.
     */
    private void adicionarCategoria() {
        catAux = new Categoria();
        System.out.println("Informe os dados da categoria que deseja criar:");

        System.out.println("Codigo: ");
        int codigo = input.getIntInput();
        
        if (loja.buscarCategoria(codigo) == null) {
            catAux.setCodigo(codigo);
        } else {
            System.out.println("Codigo em uso, retornando para a tela da gerência...");
            return;
        }

        System.out.println("Nome: ");
        catAux.setNome(input.getStringInput());

        System.out.println("Descricao: ");
        catAux.setDescricao(input.getStringInput());

        loja.adicionarCategoria(catAux);
        System.out.println("Categoria adicionada com sucesso.");
    }

    /**
     * Permite ao usuário remover uma categoria existente.
     */
    private void removerCategoria() {
        setCatAux("Informe o nome ou o código da categoria que deseja remover:");

        if (catAux == null) {
            System.out.println("A categoria não foi encontrada, retornando para a tela da gerência...");
            return;
        }

        System.out.println("[ATENÇÃO] Todos os produtos da categoria também serão removidos.");
        System.out.println("Deseja remover " + catAux.toString() + " e todos seus produtos? [S/N]");
        opcStr = input.getStringInput();

        if (opcStr.equals("S") || opcStr.equals("s")) {
            System.out.println("Categoria removida com sucesso.");
            loja.removerCategoria(catAux);
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Permite ao usuário editar uma categoria existente.
     */
    private void editarCategoria() {
        int opcao;

        setCatAux("Informe o nome ou o código da categoria que deseja editar:");

        if (catAux == null) {
            System.out.println("A categoria não foi encontrada, retornando para a tela da gerência...");
            return;
        }

        mostrarCategoria(catAux);
        System.out.println("Deseja editá-la? [S/N]");
        opcStr = input.getStringInput();

        if (!opcStr.equals("S") && !opcStr.equals("s")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("O campo a ser editado: ");
        System.out.println("1 - Nome.");
        System.out.println("2 - Descrição.");
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

    /**
     * Exibe todas as categorias e seus produtos.
     */
    private void verCategorias() {
        for (Categoria categ : loja.getListaCategorias()) {
            System.out.println(categ.toString());
        }
    }

    /**
     * Exibe os detalhes de uma categoria específica.
     */
    private void verCategoria() {
        setCatAux("Informe o nome ou o código da categoria que deseja ver:");

        if (catAux == null) {
            System.out.println("Categoria não encontrada, retornando para a tela da gerência...");
            return;
        }

        mostrarCategoria(catAux);
    }

    /**
     * Método estático para mostrar os detalhes de uma categoria.
     *
     * @param categoria A categoria cujos detalhes serão exibidos.
     */
    protected static void mostrarCategoria(Categoria categoria) {
        System.out.println(categoria.toString() + ": ");

        for (Produto prod : categoria.listarProdutos()) {
            System.out.println("\t" + prod.toString() + " || Estoque: " + prod.getEstoque());
        }
    }
}
