package com.mycompany.techgear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import java.util.logging.Logger;

/**
 * Esta classe representa a interface de exibição da loja. Permite interagir com
 * os produtos, carrinho, categorias e loja em geral.
 */
public class DisplayLoja implements Display {

    private Loja loja;
    private Input input;
    private List<Produto> carrinho;
    private DisplayGerenciarCategorias displayCategorias;
    private DisplayGerenciarProdutos displayProdutos;
    private DisplayGerenciarLoja displayLoja;

    /**
     * Construtor privado para inicializar variáveis.
     */
    private DisplayLoja() {
        input = new Input();
        carrinho = new ArrayList<>();
    }

    /**
     * Construtor que inicializa a loja e seus elementos.
     *
     * @param categoriasPath Caminho para o arquivo de categorias.
     * @param fisicosPath Caminho para o arquivo de produtos físicos.
     * @param virtuaisPath Caminho para o arquivo de produtos virtuais.
     * @param lojaPath Caminho para o arquivo da loja.
     */
    public DisplayLoja(
            String categoriasPath, String fisicosPath, String virtuaisPath,
            String lojaPath
    ) {
        this();

        String linha, campos[];

        loja = new Loja(categoriasPath, fisicosPath, virtuaisPath, lojaPath);

        displayCategorias = new DisplayGerenciarCategorias(loja);
        displayProdutos = new DisplayGerenciarProdutos(loja);
        displayLoja = new DisplayGerenciarLoja(loja);

        Scanner scn = null;
        File arquivo = new File(categoriasPath);

        try {
            scn = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de categorias nao encontrado.");

            Logger
                    .getLogger(TechGear.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        scn.nextLine();

        while (scn.hasNextLine()) {
            linha = scn.nextLine();
            campos = linha.split("#");

            loja.adicionarCategoria(
                    new Categoria(
                            Integer.parseInt(campos[0]), // codigo
                            campos[1], // nome
                            campos[2] // descricao
                    )
            );
        }

        arquivo = new File(fisicosPath);

        try {
            scn = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de produtos fisicos nao encontrado.");

            Logger.getLogger(TechGear.class.getName()).log(Level.SEVERE, null, ex);
        }

        scn.nextLine();

        while (scn.hasNextLine()) {
            linha = scn.nextLine();
            campos = linha.split("#");

            loja.adicionarProduto(
                    new ProdutoFisico(
                            Integer.parseInt(campos[0]), // id
                            campos[1], // nome
                            Double.parseDouble(campos[2]), // preco
                            campos[3], // descricao
                            campos[4], //marca
                            loja.buscarCategoria(Integer.parseInt(campos[5])), // categoria
                            Double.parseDouble(campos[6]), // peso
                            campos[7], // dimensoes
                            campos.length == 9 ? Integer.parseInt(campos[8]) : 0 // estoque
                    )
            );
        }

        arquivo = new File(virtuaisPath);

        try {
            scn = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de produtos virtuais nao encontrado.");

            Logger
                    .getLogger(TechGear.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        scn.nextLine();

        while (scn.hasNextLine()) {
            linha = scn.nextLine();
            campos = linha.split("#");

            loja.adicionarProduto(
                    new ProdutoVirtual(
                            Integer.parseInt(campos[0]), // id
                            campos[1], // nome
                            Double.parseDouble(campos[2]), // preco
                            campos[3], // descricao
                            campos[4], //marca
                            loja.buscarCategoria(Integer.parseInt(campos[5])), // categoria
                            Double.parseDouble(campos[6].split(" ")[0]), // tamanho
                            campos[7], // formato
                            campos.length == 9 ? Integer.parseInt(campos[8]) : 0 // estoque
                    )
            );
        }

        arquivo = new File(lojaPath);

        try {
            scn = new Scanner(arquivo);
            scn.nextLine();

            campos = scn.nextLine().split("#");

            loja.setNome(campos[0]);
            loja.setCnpj(campos[1]);
            loja.setEndereco(campos[2]);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo da loja nao foi encontrado.");
        }
    }

    /**
     * Método para exibir a tela principal da loja e suas opções.
     */
    public void telaUsuario() {
        int opcao;

        printHeader();
        System.out.println("1 - Adicionar ao carrinho.");
        System.out.println("2 - Realizar compra.");
        System.out.println("3 - Gerenciar categorias.");
        System.out.println("4 - Gerenciar produtos.");
        System.out.println("5 - Gerenciar loja.");
        System.out.println("6 - Salvar.");
        System.out.println("0 - Sair.");

        opcao = input.getIntInput();

        try {
            switch (opcao) {
                case 0:
                    System.out.println("Programa concluido.");
                    return;
                case 1:
                    buscarProdutos();
                    break;
                case 2:
                    realizarCompra();
                    break;
                case 3:
                    gerenciarCategorias();
                    break;
                case 4:
                    gerenciarProdutos();
                    break;
                case 5:
                    gerenciarLoja();
                    break;
                case 6:
                    loja.salvar();
                    System.out.println("Salvo com exito.");
                    break;
                default:
                    System.out.println("Operacao invalida, tente novamente...");
                    break;
            }
        } catch (Exception ex) {
            String[] erroMsg = new String[]
                {"Sair", "Adicionar ao carrinho", "Realizar compra", "Gerenciar categorias", "Gerenciar produtos", "Gerenciar loja", "Salvar"};
            
            System.out.println("Um erro ocorreu na tela principal do programa...");
            System.out.println("Na acao:\t" + erroMsg[opcao]);
        }
        
        if (opcao != 0) telaUsuario();
    }

    /**
     * Método para imprimir o cabeçalho da loja.
     */
    private void printHeader() {
        System.out.println("----------------");
        System.out.println("----" + loja.getNome() + "----");
        System.out.println("----------------");
    }

    /**
     * Método para buscar produtos na loja.
     */
    private void buscarProdutos() {
        String opcStr;
        ProdutoFisico prodFisAux;
        ProdutoVirtual prodVirAux;

        System.out.println("Informe o nome ou o id do produto que deseja buscar:");
        opcStr = input.getStringInput();

        if (loja.buscarProduto(opcStr) instanceof ProdutoFisico) {
            prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);

            System.out.println(prodFisAux.toString() + " tem " + prodFisAux.getEstoque() + " itens no estoque.");
            adicionarAoCarrinho(opcStr);
        } else {
            prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
            System.out.println(prodVirAux.toString() + " tem " + prodVirAux.getEstoque() + " itens no estoque.");
            adicionarAoCarrinho(opcStr);
        }
    }

    /**
     * Método para adicionar produtos ao carrinho.
     *
     * @param opcStr O nome ou ID do produto a ser adicionado ao carrinho.
     */
    private void adicionarAoCarrinho(String opcStr) {
        int opcao;
        ProdutoFisico prodFisAux;
        ProdutoVirtual prodVirAux;

        if (loja.buscarProduto(opcStr) instanceof ProdutoFisico) {
            prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);
            System.out.println("Quantos " + prodFisAux.toString() + " vao ser adicionados ao carrinho?");
            System.out.println("Em estoque: " + prodFisAux.getEstoque());
            opcao = input.getIntInput(0, prodFisAux.getEstoque());

            for (int i = 0; i < opcao; i++) {
                carrinho.add(prodFisAux);
            }
        } else {
            prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
            System.out.println("Quantos " + prodVirAux.toString() + " vao ser adicionados ao carrinho?");
            System.out.println("Em estoque: " + prodVirAux.getEstoque());
            opcao = input.getIntInput(1, prodVirAux.getEstoque());

            for (int i = 0; i < opcao; i++) {
                carrinho.add(prodVirAux);
            }
        }
    }

    /**
     * Método para exibir o carrinho de compras.
     */
    private void mostrarCarrinho() {
        double precoTotal = 0;

        System.out.println("Carrinho (" + carrinho.size() + "):");
        for (Produto prod : carrinho) {
            System.out.println(prod.getNome() + ": $" + prod.getPreco());
            precoTotal += prod.getPreco();
        }

        System.out.println("Preco total: $" + precoTotal);
    }

    /**
     * Método para realizar a compra dos itens no carrinho.
     */
    private void realizarCompra() {
        String opcStr;

        mostrarCarrinho();
        System.out.println("O cliente deseja prosseguir com a compra? [S/N]");
        opcStr = input.getStringInput();

        if (opcStr.equals("S") || opcStr.equals("s")) {
            for (Produto prod : carrinho) {
                prod.atualizarEstoque(-1);
            }

            loja.salvar();
            System.out.println("Compra feita com sucesso.");
            carrinho = new ArrayList<>();
        }
    }

    /**
     * Método para gerenciar as categorias da loja.
     */
    private void gerenciarCategorias() {
        displayCategorias.telaUsuario();
    }

    /**
     * Método para gerenciar os produtos da loja.
     */
    private void gerenciarProdutos() {
        displayProdutos.telaUsuario();
    }

    /**
     * Método para gerenciar as informações da loja.
     */
    private void gerenciarLoja() {
        displayLoja.telaUsuario();
    }
}
