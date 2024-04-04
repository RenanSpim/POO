package com.mycompany.techgear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayLoja {
    private Loja loja;
    private Input input;
    private List<Produto> carrinho;
    
    private DisplayLoja() {
        input = new Input();
        carrinho = new ArrayList<Produto>();
    }
    
    public DisplayLoja(
        String categoriasPath, String fisicosPath, String virtuaisPath
    ) {
        this();
        
        loja = new Loja(categoriasPath, fisicosPath, virtuaisPath);
        Scanner scn = null;
        File arquivo = new File(categoriasPath);
        
        try {
            scn = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de categorias nao encontrado");
            
            Logger
                .getLogger(TechGear.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        scn.nextLine();
        
        while (scn.hasNextLine()) {
            String linha = scn.nextLine();
            String[] campos = linha.split("#");
            
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
            System.out.println("Arquivo de produtos fisicos nao encontrado");
            
            Logger
                .getLogger(TechGear.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        scn.nextLine();
        
        while (scn.hasNextLine()) {
            String linha = scn.nextLine();
            String[] campos = linha.split("#");
            
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
            System.out.println("Arquivo de produtos virtuais nao encontrado");
            
            Logger
                .getLogger(TechGear.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        scn.nextLine();
        
        while (scn.hasNextLine()) {
            String linha = scn.nextLine();
            String[] campos = linha.split("#");
            
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
    }
    
    public Loja getLoja() {
        return loja;
    }
    
    public void telaUsuario() {
        int opcao;
        
        printHeader();
        System.out.println("1 - Buscar produtos.");
        System.out.println("2 - Realizar compra.");
        System.out.println("3 - Gerenciar categorias.");
        System.out.println("4 - Gerenciar produtos.");
        System.out.println("5 - Salvar.");
        System.out.println("0 - Sair.");
        
        opcao = input.getIntInput();
        
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
                loja.salvar();
                System.out.println("Salvo com exito.");
                break;
            default:
                System.out.println("Operacao invalida, tente novamente...");
                break;
        }
        
        telaUsuario();
    }
    
    private void printHeader() {
        System.out.println("----------------------------------");
        System.out.println(loja.getNome());
        System.out.println("----------------------------------");
        System.out.println("Digite a operacao que deseja fazer: ");
    }
    
    private void buscarProdutos() {
        String opcStr;
        ProdutoFisico prodFisAux;
        ProdutoVirtual prodVirAux;
        
        System.out.println("Informe o nome ou o id do produto que deseja buscar:");
        opcStr = input.getStringInput();
        
        if (loja.buscarProduto(Integer.parseInt(opcStr)) instanceof ProdutoFisico) {
            if (Input.inputHasNum(opcStr)) {
                prodFisAux = (ProdutoFisico) loja.buscarProduto(Integer.parseInt(opcStr));
            } else {
                prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);
            }

            System.out.println("O produto " + prodFisAux.toString() + " tem " + prodFisAux.getEstoque() + " itens no estoque.");
            adicionarAoCarrinho(opcStr);
        } else {
            if (Input.inputHasNum(opcStr)) {
                prodVirAux = (ProdutoVirtual) loja.buscarProduto(Integer.parseInt(opcStr));
            } else {
                prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
            }

            System.out.println("O produto " + prodVirAux.toString() + " tem " + prodVirAux.getEstoque() + " itens no estoque.");
            adicionarAoCarrinho(opcStr);
        }
    }
    
    private void adicionarAoCarrinho(Produto produto) {
        carrinho.add(produto);
    }
    
    private void adicionarAoCarrinho(String opcStr) {
        int opcao;
        ProdutoFisico prodFisAux;
        ProdutoVirtual prodVirAux;
        
        if (loja.buscarProduto(Integer.parseInt(opcStr)) instanceof ProdutoFisico) {
            if (Input.inputHasNum(opcStr)) {
                prodFisAux = (ProdutoFisico) loja.buscarProduto(Integer.parseInt(opcStr));
            } else {
                prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);
            }

            System.out.println("Quantos " + prodFisAux.toString() + " vao ser adicionados ao carrinho?");
            System.out.println("Em estoque: " + prodFisAux.getEstoque());
            opcao = input.getIntInput(0, prodFisAux.getEstoque());
            
            for (int i = 0; i < opcao; i++) {
                adicionarAoCarrinho(prodFisAux);
            }
        } else {
            if (Input.inputHasNum(opcStr)) {
                prodVirAux = (ProdutoVirtual) loja.buscarProduto(Integer.parseInt(opcStr));
            } else {
                prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
            }

            System.out.println("Quantos " + prodVirAux.toString() + " vao ser adicionados ao carrinho?");
            System.out.println("Em estoque: " + prodVirAux.getEstoque());
            opcao = input.getIntInput(1, prodVirAux.getEstoque());
            
            for (int i = 0; i < opcao; i++) {
                adicionarAoCarrinho(prodVirAux);
            }
        }
    }
    
    private void mostrarCarrinho() {
        double precoTotal = 0;
        
        System.out.println("Carrinho (" + carrinho.size() + "):");
        for (Produto prod : carrinho) {
            System.out.println(prod.getNome() + ": $" + prod.getPreco());
            precoTotal += prod.getPreco();
        }
        
        System.out.println("Preco total: $" + precoTotal);
    }
    
    private void realizarCompra() {
        String opcStr;
        
        mostrarCarrinho();
        System.out.println("O cliente deseja prosseguir com a compra? [S/N]");
        opcStr = input.getStringInput();
        
        if (opcStr.equals("S") || opcStr.equals("s")) {
            System.out.println("Limpando o carrinho...");
            
            for (Produto prod : carrinho) {
                prod.atualizarEstoque(-1);
            }
            
            carrinho = new ArrayList<>();
        }
    }
    
    private void gerenciarCategorias() {
        int opcao;
        String opcStr;
        Categoria catAux = new Categoria();
        
        printHeader();
        System.out.println("1 - Adicionar categoria.");
        System.out.println("2 - Remover categoria.");
        System.out.println("3 - Editar categoria.");
        System.out.println("4 - Ver todas as categorias.");
        System.out.println("5 - Ver categoria.");
        System.out.println("0 - Voltar.");
        
        opcao = input.getIntInput();

        switch (opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
                return;
            case 1:
                System.out.println("Informe os dados da categoria que deseja criar:");
                
                System.out.println("Codigo: ");
                int codigo = input.getIntInput();
                
                if (loja.buscarCategoria(codigo) == null) {
                    catAux.setCodigo(codigo);
                } else {
                    System.out.println("Codigo em uso.");
                }
                
                System.out.println("Nome: ");
                catAux.setNome(input.getStringInput());
                
                System.out.println("Descricao: ");
                catAux.setDescricao(input.getStringInput());
                
                if (loja.buscarCategoria(catAux.getCodigo()) != null) {
                    System.out.println("Ja existe um produto com esse codigo.");
                } else if (loja.buscarCategoria(catAux.getNome()) != null) {
                    System.out.println("Ja existe um produto com esse nome.");
                } else {
                    System.out.println("Operacao bem sucedida.");
                    loja.adicionarCategoria(catAux);
                }
                
                break;
            case 2:
                System.out.println("Informe o nome ou o codigo da categoria que deseja remover:");
                opcStr = input.getStringInput();
                
                if (Input.inputHasNum(opcStr)) {
                    catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                } else {
                    catAux = loja.buscarCategoria(opcStr);
                }
                
                System.out.println("Atencao, todos os produtos da categoria tambem serao removidos.");
                System.out.println("Deseja remover " + catAux.toString() + " e todos seus produtos? [S/N]");
                opcStr = input.getStringInput();
                
                if (opcStr.equals("s") || opcStr.equals("S")) {
                    System.out.println("Removendo...");
                    loja.removerCategoria(catAux);
                } else {
                    System.out.println("Abortando operacao...");
                }
                break;
            case 3:
                System.out.println("Informe o nome ou o codigo da categoria que deseja editar:");
                opcStr = input.getStringInput();
                
                if (Input.inputHasNum(opcStr)) {
                    catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                } else {
                    catAux = loja.buscarCategoria(opcStr);
                }
                
                System.out.println("Deseja editar " + catAux.toString() + "? [S/N]");
                opcStr = input.getStringInput();
                
                if (opcStr.equals("N") || opcStr.equals("n")) {
                    System.out.println("Abortando operacao...");
                    break;
                }
                
                System.out.println("O campo a ser editado: ");
                System.out.println("1 - Nome.");
                System.out.println("2 - Descricao.");
                opcao = input.getIntInput();
                
                if (opcao == 1) {
                    catAux.setNome(input.getStringInput());
                } else if (opcao == 2) {
                    catAux.setDescricao(input.getStringInput());
                } else {
                    System.out.println("Retornando, nada mudado.");
                    break;
                }
                System.out.println("Categoria alterada com sucesso.");
                break;
            case 4:
                for (Categoria categ : loja.getListaCategorias()) {
                    System.out.println(categ.toString());
                }
                break;
            case 5:
                catAux = null;
                
                System.out.println("Informe o nome ou o codigo da categoria que deseja ver:");
                opcStr = input.getStringInput();
                
                if (Input.inputHasNum(opcStr)) {
                    catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                } else {
                    catAux = loja.buscarCategoria(opcStr);
                }
                
                if (catAux == null) {
                    System.out.println("A categoria nao foi encontrada, retornando...");
                    break;
                }
                
                System.out.println(catAux.toString() + ": ");
                for (Produto prod : catAux.listarProdutos()) {
                    System.out.println("\t" + prod.toString());
                }
                break;
            default:
                System.out.println("Operacao invalida, tente novamente...");
                gerenciarCategorias();
                break;
        }
    }
    
    private void gerenciarProdutos() {
        int opcao;
        String opcStr;
        Categoria catAux = null;
        ProdutoFisico prodFisAux = new ProdutoFisico();
        ProdutoVirtual prodVirAux = new ProdutoVirtual();
        
        printHeader();
        System.out.println("1 - Adicionar novo produto.");
        System.out.println("2 - Remover produto.");
        System.out.println("3 - Editar produto.");
        System.out.println("4 - Ver todos os produtos.");
        System.out.println("0 - Sair.");
        
        opcao = input.getIntInput();
        
        switch (opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
                return;
            case 1:
                System.out.println("Novo produto:");                
                System.out.println("Nome: ");
                prodFisAux.setNome(input.getStringInput());
                
                System.out.println("Preco: ");
                prodFisAux.setPreco(input.getDoubleInput());
                
                System.out.println("Descricao: ");
                prodFisAux.setDescricao(input.getStringInput());
                
                System.out.println("Marca: ");
                prodFisAux.setMarca(input.getStringInput());
                
                System.out.println("Categoria (nome ou codigo): ");
                opcStr = input.getStringInput();
                
                if (Input.inputHasNum(opcStr)) {
                    prodFisAux.setCategoria(loja.buscarCategoria(Integer.parseInt(opcStr)));
                } else {
                    prodFisAux.setCategoria(loja.buscarCategoria(opcStr));
                }
                
                System.out.println("Id: ");
                boolean idEmUso;
                do {
                    idEmUso = !prodFisAux.setId(input.getIntInput());
                    if (idEmUso) {
                        System.out.println("Id em uso");
                    }
                } while (idEmUso);
                
                do {
                    System.out.println("Virtual(v) ou fisico(f)? ");
                    opcStr = input.getStringInput();
                    
                    if (!"v".equals(opcStr) && !"d".equals(opcStr)) {
                        System.out.println("Opcao invalida, tente novamente.");
                    }
                } while (!"v".equals(opcStr) && !"d".equals(opcStr));
                
                if (opcStr.equals("v")) {
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
                    
                    System.out.println("Dimensoes: ");
                    boolean inputCorreto;
                    do {
                        opcStr = input.getStringInput();
                        inputCorreto = prodFisAux.setDimensoes(opcStr);
                    } while (!inputCorreto);
                    
                    loja.adicionarProduto(prodFisAux);
                }
                break;
            case 2:
                prodFisAux = null;
                prodVirAux = null;
                
                System.out.println("Informe o nome ou o id do produto que deseja remover:");
                opcStr = input.getStringInput();
                
                if (Input.inputHasNum(opcStr)) {
                    if (loja.buscarProduto(Integer.parseInt(opcStr)) instanceof ProdutoFisico) {
                        prodFisAux = (ProdutoFisico) loja.buscarProduto(Integer.parseInt(opcStr));
                    } else {
                        prodVirAux = (ProdutoVirtual) loja.buscarProduto(Integer.parseInt(opcStr));
                    }
                } else {
                    if (loja.buscarProduto(Integer.parseInt(opcStr)) instanceof ProdutoFisico) {
                        prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);
                    } else {
                        prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
                    }
                }
                
                if (prodVirAux == null) {
                    System.out.println("Deseja remover " + prodVirAux.toString() + "? [S/N]");
                } else {
                    System.out.println("Deseja remover " + prodFisAux.toString() + "? [S/N]");
                }
                
                opcStr = input.getStringInput();
                
                if (opcStr.equals("s") || opcStr.equals("S")) {
                    System.out.println("Removendo...");
                    loja.removerProduto(prodFisAux == null ? prodVirAux.getId() : prodFisAux.getId());
                } else {
                    System.out.println("Abortando operacao...");
                }
                break;
            case 3:
                prodFisAux = null;
                prodVirAux = null;
                catAux = null;
                
                System.out.println("Informe o nome ou o codigo do produto que deseja editar:");
                opcStr = input.getStringInput();
                
                if (Input.inputHasNum(opcStr)) {
                    if (loja.buscarProduto(Integer.parseInt(opcStr)) instanceof ProdutoFisico) {
                        prodFisAux = (ProdutoFisico) loja.buscarProduto(Integer.parseInt(opcStr));
                    } else {
                        prodVirAux = (ProdutoVirtual) loja.buscarProduto(Integer.parseInt(opcStr));
                    }
                } else {
                    if (loja.buscarProduto(Integer.parseInt(opcStr)) instanceof ProdutoFisico) {
                        prodFisAux = (ProdutoFisico) loja.buscarProduto(opcStr);
                    } else {
                        prodVirAux = (ProdutoVirtual) loja.buscarProduto(opcStr);
                    }
                }
                
                System.out.println("Deseja editar " + (prodFisAux == null ? prodVirAux : prodFisAux).toString() + "? [S/N]");
                opcStr = input.getStringInput();
                
                if (opcStr.equals("N") || opcStr.equals("n")) {
                    System.out.println("Abortando operacao...");
                    break;
                }
                
                System.out.println("O campo a ser editado: ");
                System.out.println("1 - Id.");
                System.out.println("2 - Nome.");
                System.out.println("3 - Preco.");
                System.out.println("4 - Descricao.");
                System.out.println("5 - Marca.");
                System.out.println("6 - Categoria.");
                System.out.println("7 - Estoque.");
                opcao = input.getIntInput();

                if (prodFisAux != null) {
                    if (opcao == 1) {
                        if (!prodFisAux.setId(input.getIntInput())) {
                            System.out.println("Ja existe um produto com esse Id.");
                        }
                    } else if (opcao == 2) {
                        prodFisAux.setNome(input.getStringInput());
                    } else if (opcao == 3) {
                        prodFisAux.setPreco(input.getDoubleInput());
                    } else if (opcao == 4) {
                        prodFisAux.setDescricao(input.getStringInput());
                    } else if (opcao == 5) {
                        prodFisAux.setMarca(input.getStringInput());
                    } else if (opcao == 6) {
                        opcStr = input.getStringInput();
                        
                        if (Input.inputHasNum(opcStr)) {
                            catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                        } else {
                            catAux = loja.buscarCategoria(opcStr);
                        }
                        
                        if (catAux == null) {
                            System.out.println("Categoria nao encontrada...");
                        } else {
                            prodFisAux.setCategoria(catAux);
                        }
                    } else if (opcao == 7) {
                        prodFisAux.setEstoque(input.getIntInput());
                    } else {
                        System.out.println("Essa opcao nao existe, retornando...");
                    }
                } else {
                    if (opcao == 1) {
                        if (!prodVirAux.setId(input.getIntInput())) {
                            System.out.println("Ja existe um produto com esse Id.");
                        }
                    } else if (opcao == 2) {
                        prodVirAux.setNome(input.getStringInput());
                    } else if (opcao == 3) {
                        prodVirAux.setPreco(input.getDoubleInput());
                    } else if (opcao == 4) {
                        prodVirAux.setDescricao(input.getStringInput());
                    } else if (opcao == 5) {
                        prodVirAux.setMarca(input.getStringInput());
                    } else if (opcao == 6) {
                        opcStr = input.getStringInput();
                        
                        if (Input.inputHasNum(opcStr)) {
                            catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                        } else {
                            catAux = loja.buscarCategoria(opcStr);
                        }
                        
                        if (catAux == null) {
                            System.out.println("Categoria nao encontrada...");
                        } else {
                            prodVirAux.setCategoria(catAux);
                        }
                    } else if (opcao == 7) {
                        prodFisAux.setEstoque(input.getIntInput());
                    } else {
                        System.out.println("Essa opcao nao existe, retornando...");
                    }
                }
                break;
            case 4:
                for (Categoria categ : loja.getListaCategorias()) {
                    System.out.println(categ.toString() + ":");
                    for (Produto prod : categ.listarProdutos()) {
                        System.out.println("\t" + prod.toString() + " (" + prod.getEstoque() +")");
                    }
                }
                break;
            default:
                System.out.println("Operacao invalida, tente novamente...");
                gerenciarCategorias();
                break;
        }
    }
}
