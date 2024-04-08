package com.mycompany.techgear;

public class DisplayGerenciarProdutos extends Display {
    private int opcao;
    String opcStr;
    Categoria catAux;
    ProdutoFisico prodFisAux;
    ProdutoVirtual prodVirAux;
    
    public DisplayGerenciarProdutos(Loja loja) {
        super(loja);
    }
    
    public void telaGerente() {
        printHeader();
        System.out.println("1 - Adicionar novo produto.");
        System.out.println("2 - Remover produto.");
        System.out.println("3 - Editar produto.");
        System.out.println("4 - Ver todos os produtos.");
        System.out.println("5 - Ver produto.");
        System.out.println("0 - Sair.");
        
        opcao = input.getIntInput();
        
        switch (opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
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
                System.out.println("Operacao invalida, voltando para a tela do usuario...");
                return;
        }
        
        telaGerente();
    }
    
    private void setCategoria(Produto prod) {
        opcStr = input.getStringInput();

        if (Input.inputHasNum(opcStr)) {
            prod.setCategoria(loja.buscarCategoria(Integer.parseInt(opcStr)));
        } else {
            prod.setCategoria(loja.buscarCategoria(opcStr));
        }
    }
    
    private void setProdsAux(String message) {
        System.out.println(message);
        opcStr = input.getStringInput();
        
        prodFisAux = null;
        prodVirAux = null;
        
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
    }
    
    private void adicionarProduto() {
        System.out.println("Informe os dados do produto que deseja criar:");
        System.out.println("Nome: ");
        prodFisAux.setNome(input.getStringInput());

        System.out.println("Preco: ");verProduto();
        prodFisAux.setPreco(input.getDoubleInput());

        System.out.println("Descricao: ");
        prodFisAux.setDescricao(input.getStringInput());

        System.out.println("Marca: ");
        prodFisAux.setMarca(input.getStringInput());

        System.out.println("Categoria (nome ou codigo): ");
        setCategoria(prodFisAux);
        
        if (prodFisAux.getCategoria() == null) {
            System.out.println("Nao encontramos a categoria desejada, retornando para a tela da gerencia...");
            return;
        }

        System.out.println("Id: ");
        if (!prodFisAux.setId(input.getIntInput())) {
            System.out.println("Id em uso, retornando para a tela da gerencia...");
            return;
        }

        System.out.println("O produto e virtual(v) ou fisico(f)? ");
        opcStr = input.getStringInput();

        if (!"v".equals(opcStr) && !"d".equals(opcStr) &&
                !"V".equals(opcStr) && !"D".equals(opcStr)) {
            System.out.println("Opcao invalida, retornando para a tela da gerencia...");
            return;
        }

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
            if (!prodFisAux.setDimensoes(opcStr)) {
                System.out.println("Input incorreto para dimensoes, retornando para a tela da gerencia...");
                return;
            }

            loja.adicionarProduto(prodFisAux);
        }
    }
    
    private void removerProduto() {
        setProdsAux("Informe o nome ou o id do produto que deseja remover:");

        if (prodVirAux != null) {
            System.out.println("Deseja remover " + prodVirAux.toString() + "? [S/N]");
        } else if (prodFisAux != null) {
            System.out.println("Deseja remover " + prodFisAux.toString() + "? [S/N]");
        } else {
            System.out.println("O produto nao foi encontrado, retornando para a tela da gerencia...");
            return;
        }

        opcStr = input.getStringInput();

        if (opcStr.equals("S") || opcStr.equals("s")) {
            System.out.println("Removendo...");
            loja.removerProduto(prodFisAux == null ? prodVirAux.getId() : prodFisAux.getId());
        } else {
            System.out.println("Operacao cancelada.");
        }
    }
    
    private void editarProduto() {
        setProdsAux("Informe o nome ou o codigo do produto que deseja editar:");
        
        if (prodVirAux != null) {
            mostrarProduto();
            System.out.println("Deseja edita-lo? [S/N]");
        } else if (prodFisAux != null) {
            mostrarProduto();
            System.out.println("Deseja edita-lo? [S/N]");
        } else {
            System.out.println("O produto nao foi encontrado, retornando para a tela da gerencia...");
            return;
        }
        
        opcStr = input.getStringInput();

        if (!opcStr.equals("S") && !opcStr.equals("s")) {
            System.out.println("Operacao cancelada.");
            return;
        }

        System.out.println("O campo a ser editado: ");
        System.out.println("1 - Nome.");
        System.out.println("2 - Preco.");
        System.out.println("3 - Descricao.");
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
                    }   if (catAux == null) {
                        System.out.println("Categoria nao encontrada, retornando para a tela da gerencia...");
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
                    }   if (catAux == null) {
                        System.out.println("Categoria nao encontrada, retornando para a tela da gerencia.");
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
    
    private void verProdutos() {
        for (Categoria categ : loja.getListaCategorias()) {
            DisplayGerenciarCategorias.mostrarCategoria(categ);
        }
    }
    
    private void verProduto() {
        setProdsAux("Informe o nome ou o codigo do produto que deseja ver:");
        mostrarProduto();
    }
    
    private void mostrarProduto() {
        if (prodFisAux != null) {
            System.out.println(prodFisAux.toString() + ":");
            System.out.println("Categoria: " + prodFisAux.getCategoria().getNome());
            System.out.println("Marca: " + prodFisAux.getMarca());
            System.out.println("Descricao: " + prodFisAux.getDescricao());
            System.out.println("Dimensoes: " + prodFisAux.getDimensoes());
            System.out.println("Peso: " + prodFisAux.getPeso());
            System.out.println("Estoque: " + prodFisAux.getEstoque());
            System.out.println("Preco: $" + prodFisAux.getPreco());
        } else if (prodVirAux != null) {
            System.out.println(prodVirAux.toString() + ":");
            System.out.println("Categoria: " + prodVirAux.getCategoria().getNome());
            System.out.println("Marca: " + prodVirAux.getMarca());
            System.out.println("Descricao: " + prodVirAux.getDescricao());
            System.out.println("Formato: " + prodVirAux.getFormato());
            System.out.println("Tamanho: " + prodVirAux.getTamanhoArquivo() + " GB");
            System.out.println("Estoque: " + prodVirAux.getEstoque());
            System.out.println("Preco: $" + prodVirAux.getPreco());
        } else {
            System.out.println("Produto nao encontrado, retornado para a tela de gerencia...");
        }
    }
}
