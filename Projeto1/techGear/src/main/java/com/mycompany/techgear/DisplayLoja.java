package com.mycompany.techgear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayLoja {
    private Loja loja;
    private Scanner scanner;
    
    private DisplayLoja() {
        scanner = new Scanner(System.in);
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
                    campos[7] // dimensoes
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
                    campos[7] // formato
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
        
        opcao = scanner.nextInt();
        scanner.nextLine();
        
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
        // TODO
    }
    
    private void adicionarAoCarrinho(Produto produto) {
        // TODO
    }
    
    private void realizarCompra() {
        // TODO
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
        
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
                return;
            case 1:
                System.out.println("Informe os dados da categoria que deseja criar:");
                
                System.out.println("Codigo: ");
                catAux.setCodigo(scanner.nextInt());
                scanner.nextLine();
                
                System.out.println("Nome: ");
                catAux.setNome(scanner.nextLine());
                
                System.out.println("Descricao: ");
                catAux.setDescricao(scanner.nextLine());
                
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
                opcStr = scanner.nextLine();
                
                if (opcStr != null && opcStr.matches("[0-9.]+")) {
                    catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                } else {
                    catAux = loja.buscarCategoria(opcStr);
                }
                
                System.out.println("Atencao, todos os produtos da categoria tambem serao removidos.");
                System.out.println("Deseja remover " + catAux.getNome() + " (" + catAux.getCodigo() + ") e todos seus produtos? [S/N]");
                opcStr = scanner.nextLine();
                
                if (opcStr.equals("s") || opcStr.equals("S")) {
                    System.out.println("Removendo...");
                    loja.removerCategoria(catAux);
                } else {
                    System.out.println("Abortando operacao...");
                }
                break;
            case 3:
                System.out.println("Informe o nome ou o codigo da categoria que deseja editar:");
                opcStr = scanner.nextLine();
                
                if (opcStr != null && opcStr.matches("[0-9.]+")) {
                    catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                } else {
                    catAux = loja.buscarCategoria(opcStr);
                }
                
                System.out.println("Deseja editar " + catAux.toString() + "? [S/N]");
                opcStr = scanner.nextLine();
                
                if (opcStr.equals("N") || opcStr.equals("n")) {
                    System.out.println("Abortando operacao...");
                    break;
                }
                
                System.out.println("O campo a ser editado: ");
                System.out.println("1 - Nome.");
                System.out.println("2 - Descricao.");
                System.out.println("3 - Codigo.");
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                if (opcao == 1) {
                    catAux.setNome(scanner.nextLine());
                } else if (opcao == 2) {
                    catAux.setDescricao(scanner.nextLine());
                } else if (opcao == 3) {
                    catAux.setCodigo(scanner.nextInt());
                    scanner.nextLine();
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
                opcStr = scanner.nextLine();
                
                if (opcStr != null && opcStr.matches("[0-9.]+")) {
                    catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                } else {
                    catAux = loja.buscarCategoria(opcStr);
                }
                
                if (catAux == null) {
                    System.out.println("A categoria nao foi encontrada, retornando...");
                    break;
                }
                
                System.out.println(catAux.toString() + " :");
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
        double daux;
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
        
        opcao = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
                return;
            case 1:
                System.out.println("Novo produto:");
                System.out.println("Id: ");
                prodFisAux.setId(scanner.nextInt());
                scanner.nextLine();
                
                System.out.println("Nome: ");
                prodFisAux.setNome(scanner.nextLine());
                
                System.out.println("Preco: ");
                prodFisAux.setPreco(scanner.nextDouble());
                scanner.nextLine();
                
                System.out.println("Descricao: ");
                prodFisAux.setDescricao(scanner.nextLine());
                
                System.out.println("Marca: ");
                prodFisAux.setMarca(scanner.nextLine());
                
                System.out.println("Categoria (nome ou codigo): ");
                opcStr = scanner.nextLine();
                
                if (opcStr != null && opcStr.matches("[0-9.]+")) {
                    prodFisAux.setCategoria(loja.buscarCategoria(Integer.parseInt(opcStr)));
                } else {
                    prodFisAux.setCategoria(loja.buscarCategoria(opcStr));
                }
                
                do {
                    System.out.println("Virtual(v) ou fisico(f)? ");
                    opcStr = scanner.nextLine();
                    
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
                    
                    do {
                        daux = scanner.nextDouble();
                        scanner.nextLine();
                        
                        if (daux <= 0.0) {
                            System.out.println("Tamanho invalido, tente novamente.");
                        }
                    } while (daux <= 0.0);
                    prodVirAux.setTamanhoArquivo(daux);
                    
                    System.out.println("Formato: ");
                    prodVirAux.setFormato(scanner.nextLine());
                    
                    loja.adicionarProduto(prodVirAux);
                } else {
                    System.out.println("Peso: ");
                    prodFisAux.setPeso(scanner.nextDouble());
                    scanner.nextLine();
                    
                    System.out.println("Dimensoes: ");
                    opcStr = scanner.nextLine();
                    
                    for (String str : opcStr.split("x")) {
                        if (str.matches("\\D+")) {
                            return;
                        }
                    }
                    prodFisAux.setDimensoes(opcStr);
                    
                    loja.adicionarProduto(prodFisAux);
                }
                break;
            case 2:
                prodFisAux = null;
                prodVirAux = null;
                
                System.out.println("Informe o nome ou o id do produto que deseja remover:");
                opcStr = scanner.nextLine();
                
                if (opcStr != null && opcStr.matches("[0-9.]+")) {
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
                
                opcStr = scanner.nextLine();
                
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
                opcStr = scanner.nextLine();
                
                if (opcStr != null && opcStr.matches("[0-9.]+")) {
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
                opcStr = scanner.nextLine();
                
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
                opcao = scanner.nextInt();
                scanner.nextLine();

                if (prodFisAux != null) {
                    if (opcao == 1) {
                        prodFisAux.setId(scanner.nextInt());
                        scanner.nextLine();
                    } else if (opcao == 2) {
                        prodFisAux.setNome(scanner.nextLine());
                    } else if (opcao == 3) {
                        prodFisAux.setPreco(scanner.nextDouble());
                        scanner.nextLine();
                    } else if (opcao == 4) {
                        prodFisAux.setDescricao(scanner.nextLine());
                    } else if (opcao == 5) {
                        prodFisAux.setMarca(scanner.nextLine());
                    } else if (opcao == 6) {
                        opcStr = scanner.nextLine();
                        
                        if (opcStr.matches("\\d+")) {
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
                        prodFisAux.setEstoque(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        
                    }
                } else {
                    if (opcao == 1) {
                        prodVirAux.setId(Integer.parseInt(scanner.nextLine()));
                    } else if (opcao == 2) {
                        prodVirAux.setNome(scanner.nextLine());
                    } else if (opcao == 3) {
                        prodVirAux.setPreco(scanner.nextDouble());
                        scanner.nextLine();
                    } else if (opcao == 4) {
                        prodVirAux.setDescricao(scanner.nextLine());
                    } else if (opcao == 5) {
                        prodVirAux.setMarca(scanner.nextLine());
                    } else if (opcao == 6) {
                        opcStr = scanner.nextLine();
                        
                        if (opcStr.matches("\\d+")) {
                            catAux = loja.buscarCategoria(Integer.parseInt(opcStr));
                        } else {
                            catAux = loja.buscarCategoria(opcStr);
                        }
                        
                        if (catAux == null) {
                            System.out.println("Categoria nao encontrada...");
                        } else {
                            prodVirAux.setCategoria(catAux);
                        }
                    }
                }
                break;
            case 4:
                for (Categoria categ : loja.getListaCategorias()) {
                    System.out.println(categ.toString() + ":");
                    for (Produto prod : categ.listarProdutos()) {
                        System.out.println("\t" + prod.toString());
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
