package com.mycompany.techgear;

public class DisplayGerenciarLoja extends Display {
    
    public DisplayGerenciarLoja(Loja loja) {
        super(loja);
    }
    
    public void telaGerente() {
        int opcao;
        
        System.out.println("Selecione o que deseja modificar:");
        System.out.println("1 - Nome.");
        System.out.println("2 - CNPJ.");
        System.out.println("3 - Endereco.");
        System.out.println("0 - Sair.");
        
        opcao = input.getIntInput();
        
        switch(opcao) {
            case 0:
                System.out.println("Voltando para a tela do usuario...");
                return;
            case 1:
                alterarNome();
                break;
            case 2:
                alterarCnpj();
                break;
            case 3:
                alterarEndereco();
                break;
            default:
                System.out.println("Operacao invalida, voltando para a tela do usuario...");
                return;
        }
        
        System.out.println("Loja alterada com sucesso, voltando para a tela do usuario...");
    }
    
    private void alterarNome() {
        System.out.println("Informe o novo nome da loja:");
        loja.setNome(input.getStringInput());
    }
    
    private void alterarCnpj() {
        System.out.println("Informe o novo cnpj da loja:");
        loja.setCnpj(input.getStringInput());
    }
    
    private void alterarEndereco() {
        System.out.println("Informe o novo endereco da loja:");
        loja.setNome(input.getStringInput());
    }
}
