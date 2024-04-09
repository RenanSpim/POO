package com.mycompany.techgear;

/**
 * Classe responsável por exibir o gerenciamento dos produtos da loja. Esta
 * classe implementa a interface Display para exibir a tela de gerenciamento dos
 * produtos da loja. Permite ao usuário adicionar, remover, editar, ver todos os
 * produtos ou ver um produto específico da loja. Os produtos podem ser físicos
 * ou virtuais.
 *
 * @author RenanSPim
 */
public class DisplayGerenciarProdutos implements Display {

    private Loja loja;
    private Input input;
    private String opcStr;
    private Categoria catAux;
    private ProdutoFisico prodFisAux;
    private ProdutoVirtual prodVirAux;

    /**
     * Construtor da classe.
     *
     * @param loja A loja associada a esta instância de gerenciamento de
     * produtos.
     */
    public DisplayGerenciarProdutos(Loja loja) {
        this.input = new Input();
        this.loja = loja;
    }

    /**
     * Exibe a tela principal para o gerenciamento dos produtos. Permite ao
     * usuário selecionar entre várias opções, como adicionar, remover, editar,
     * ver todos os produtos ou ver um produto específico.
     */
    public void tela() {
        int opcao;

        printHeader();
        System.out.println("1 - Adicionar novo produto.");
        System.out.println("2 - Remover produto.");
        System.out.println("3 - Editar produto.");
        System.out.println("4 - Ver todos os produtos.");
        System.out.println("5 - Ver produto.");
        System.out.println("0 - Voltar.");

        opcao = input.getIntInput();

        try {
            switch (opcao) {
                case 0:
                    System.out.println("Voltando para a tela do usuário...");
                    return;
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    editarProduto();
                    break;
                case 4:
                    verProdutos();
                    break;
                case 5:
                    verProduto();
                    break;
                default:
                    System.out.println("Operação inválida, voltando para a tela do usuário...");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Um erro ocorreu na tela de gerenciar produtos.");
        } finally {
            if (opcao != 0) {
                tela();
            }
        }
    }

    /**
     * Imprime o cabeçalho.
     */
    private void printHeader() {
        System.out.println("----------------");
        System.out.println("----Produtos----");
        System.out.println("----------------");
    }

    /**
     * Define o produto auxiliar com base no input do usuário.
     *
     * @param message A mensagem a ser exibida para solicitar o input do
     * usuário.
     */
    private void setProdsAux(String message) {
        System.out.println(message);
        opcStr = input.getStringInput();

        prodFisAux = null;
        prodVirAux = null;

        if (loja.buscarProduto(opcStr) instanceof ProdutoFisico) {
            prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);
        } else {
            prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
        }
    }

    /**
     * Define a categoria de um produto.
     *
     * @param prod O produto cuja categoria será definida.
     */
    private void setCategoria(Produto prod) {
        opcStr = input.getStringInput();
        prod.setCategoria(loja.buscarCategoria(opcStr));
    }

    /**
     * Adiciona um novo produto com base nos dados fornecidos pelo usuário.
     */
    private void adicionarProduto() {
        prodFisAux = new ProdutoFisico();
        prodVirAux = new ProdutoVirtual();

        System.out.println("Informe os dados do produto que deseja criar:");
        System.out.println("Nome: ");
        prodFisAux.setNome(input.getStringInput());

        System.out.println("Preço: ");
        prodFisAux.setPreco(input.getDoubleInput());

        System.out.println("Descrição: ");
        prodFisAux.setDescricao(input.getStringInput());

        System.out.println("Marca: ");
        prodFisAux.setMarca(input.getStringInput());

        System.out.println("Categoria (nome ou código): ");
        setCategoria(prodFisAux);

        if (prodFisAux.getCategoria() == null) {
            System.out.println("Não foi encontrada a categoria desejada, retornando para a tela de gerência...");
            return;
        }

        System.out.println("Id: ");
        if (!prodFisAux.setId(input.getIntInput())) {
            System.out.println("Id em uso, retornando para a tela de gerência...");
            return;
        }

        System.out.println("O produto é virtual (v) ou físico (f)? ");
        opcStr = input.getStringInput();

        if (!"v".equalsIgnoreCase(opcStr) && !"f".equalsIgnoreCase(opcStr)) {
            System.out.println("Opção inválida, retornando para a tela de gerência...");
            return;
        }

        if ("v".equalsIgnoreCase(opcStr)) {
            prodVirAux.setCategoria(prodFisAux.getCategoria());
            prodVirAux.setDescricao(prodFisAux.getDescricao());
            prodVirAux.setId(prodFisAux.getId());
            prodVirAux.setMarca(prodFisAux.getMarca());
            prodVirAux.setNome(prodFisAux.getNome());
            prodVirAux.setPreco(prodFisAux.getPreco());

            System.out.println("Tamanho do arquivo (GB): ");
            prodVirAux.setTamanhoArquivo(input.getDoubleInput());

            System.out.println("Formato: ");
            prodVirAux.setFormato(input.getStringInput());

            loja.adicionarProduto(prodVirAux);
        } else {
            System.out.println("Peso: ");
            prodFisAux.setPeso(input.getDoubleInput());

            System.out.println("Dimensões: ");

            if (!prodFisAux.setDimensoes(input.getStringInput())) {
                System.out.println("Input incorreto para dimensões, retornando para a tela de gerência...");
                return;
            }

            loja.adicionarProduto(prodFisAux);
        }

        System.out.println("Produto adicionado com sucesso.");
    }

    /**
     * Remove um produto específico com base nos dados escolhidos pelo usuário.
     */
    private void removerProduto() {
        setProdsAux("Informe o nome ou o id do produto que deseja remover:");

        if (prodVirAux != null) {
            System.out.println("Deseja remover " + prodVirAux.toString() + "? [S/N]");
        } else if (prodFisAux != null) {
            System.out.println("Deseja remover " + prodFisAux.toString() + "? [S/N]");
        } else {
            System.out.println("O produto não foi encontrado, retornando para a tela de gerência...");
            return;
        }

        opcStr = input.getStringInput();

        if (opcStr.equalsIgnoreCase("S")) {
            System.out.println("Produto removido com sucesso.");
            loja.removerProduto(prodFisAux == null ? prodVirAux.getId() : prodFisAux.getId());
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Edita um produto específico com base nos dados escolhidos pelo usuário.
     */
    private void editarProduto() {
        int opcao;

        setProdsAux("Informe o nome ou o id do produto que deseja editar:");

        if (prodVirAux != null) {
            mostrarProduto();
            System.out.println("Deseja editá-lo? [S/N]");
        } else if (prodFisAux != null) {
            mostrarProduto();
            System.out.println("Deseja editá-lo? [S/N]");
        } else {
            System.out.println("O produto não foi encontrado, retornando para a tela de gerência...");
            return;
        }

        opcStr = input.getStringInput();

        if (!opcStr.equalsIgnoreCase("S")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("O campo a ser editado: ");
        System.out.println("1 - Nome.");
        System.out.println("2 - Preço.");
        System.out.println("3 - Descrição.");
        System.out.println("4 - Marca.");
        System.out.println("5 - Categoria.");
        System.out.println("6 - Estoque.");
        opcao = input.getIntInput();

        if (prodFisAux != null) {
            switch (opcao) {
                case 1:
                    prodFisAux.setNome(input.getStringInput());
                    break;
                case 2:
                    prodFisAux.setPreco(input.getDoubleInput());
                    break;
                case 3:
                    prodFisAux.setDescricao(input.getStringInput());
                    break;
                case 4:
                    prodFisAux.setMarca(input.getStringInput());
                    break;
                case 5:
                    opcStr = input.getStringInput();
                    if (Input.inputHasNum(opcStr)) {
                        catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                    } else {
                        catAux = loja.buscarCategoria(opcStr);
                    }
                    if (catAux == null) {
                        System.out.println("Categoria não encontrada, retornando para a tela de gerência...");
                        return;
                    } else {
                        prodFisAux.setCategoria(catAux);
                    }
                    break;
                case 6:
                    prodFisAux.setEstoque(input.getIntInput());
                    break;
                default:
                    System.out.println("Nada mudado.");
                    return;
            }

            System.out.println("Produto alterado com sucesso.");
        } else {
            switch (opcao) {
                case 1:
                    prodVirAux.setNome(input.getStringInput());
                    break;
                case 2:
                    prodVirAux.setPreco(input.getDoubleInput());
                    break;
                case 3:
                    prodVirAux.setDescricao(input.getStringInput());
                    break;
                case 4:
                    prodVirAux.setMarca(input.getStringInput());
                    break;
                case 5:
                    opcStr = input.getStringInput();
                    if (Input.inputHasNum(opcStr)) {
                        catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                    } else {
                        catAux = loja.buscarCategoria(opcStr);
                    }
                    if (catAux == null) {
                        System.out.println("Categoria não encontrada, retornando para a tela de gerência.");
                        return;
                    } else {
                        prodVirAux.setCategoria(catAux);
                    }
                    break;
                case 6:
                    prodFisAux.setEstoque(input.getIntInput());
                    break;
                default:
                    System.out.println("Nada mudado.");
                    return;
            }

            System.out.println("Produto alterado com sucesso.");
        }
    }

    /**
     * Exibe todos os produtos existentes na loja.
     */
    private void verProdutos() {
        for (Categoria categ : loja.getListaCategorias()) {
            DisplayGerenciarCategorias.mostrarCategoria(categ);
        }
    }

    /**
     * Exibe um produto específico.
     */
    private void verProduto() {
        setProdsAux("Informe o nome ou o código do produto que deseja ver:");
        mostrarProduto();
    }

    /**
     * Imprime os detalhes do produto auxiliar.
     */
    private void mostrarProduto() {
        if (prodFisAux != null) {
            System.out.println(prodFisAux.toString() + ":");
            System.out.println("Categoria: " + prodFisAux.getCategoria().getNome());
            System.out.println("Marca: " + prodFisAux.getMarca());
            System.out.println("Descrição: " + prodFisAux.getDescricao());
            System.out.println("Dimensões: " + prodFisAux.getDimensoesStr());
            System.out.println("Peso: " + prodFisAux.getPeso());
            System.out.println("Estoque: " + prodFisAux.getEstoque());
            System.out.println("Preço: $" + prodFisAux.getPreco());
        } else if (prodVirAux != null) {
            System.out.println(prodVirAux.toString() + ":");
            System.out.println("Categoria: " + prodVirAux.getCategoria().getNome());
            System.out.println("Marca: " + prodVirAux.getMarca());
            System.out.println("Descrição: " + prodVirAux.getDescricao());
            System.out.println("Formato: " + prodVirAux.getFormato());
            System.out.println("Tamanho: " + prodVirAux.getTamanhoArquivo() + " GB");
            System.out.println("Estoque: " + prodVirAux.getEstoque());
            System.out.println("Preço: $" + prodVirAux.getPreco());
        } else {
            System.out.println("Produto não encontrado, retornando para a tela de gerência...");
        }
    }
}