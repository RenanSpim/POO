package com.mycompany.techgear;

/**
 * Classe responsável pelo gerenciamento dos atributos da loja. Esta classe
 * implementa a interface Display para exibir a tela de gerenciamento dos
 * atributos da loja. Permite ao usuário modificar o nome, CNPJ e endereço da
 * loja.
 *
 * @author RenanSPim
 */
public class DisplayGerenciarLoja implements Display {

    private Loja loja;
    private Input input;

    /**
     * Construtor da classe.
     *
     * @param loja A loja associada a esta instância de gerenciamento de loja.
     */
    public DisplayGerenciarLoja(Loja loja) {
        this.input = new Input();
        this.loja = loja;
    }

    /**
     * Exibe a tela principal para o gerenciamento dos atributos da loja.
     * Permite ao usuário selecionar o que deseja modificar: nome, CNPJ ou
     * endereço.
     */
    public void tela() {
        int opcao;

        System.out.println("Selecione o que deseja modificar:");
        System.out.println("1 - Nome (" + loja.getNome() + ")");
        System.out.println("2 - CNPJ (" + loja.getCnpj() + ")");
        System.out.println("3 - Endereço (" + loja.getEndereco() + ")");
        System.out.println("0 - Voltar.");

        opcao = input.getIntInput();

        try {
            switch (opcao) {
                case 0:
                    System.out.println("Voltando para a tela do usuário...");
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
                    System.out.println("Operação inválida, voltando para a tela do usuário...");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Um erro ocorreu na tela de gerenciar loja.");
        } finally {
            System.out.println("Loja alterada com sucesso, voltando para a tela do usuário...");
        }
    }

    /**
     * Altera o nome da loja com base no input do usuário.
     */
    private void alterarNome() {
        System.out.println("Informe o novo nome da loja:");
        loja.setNome(input.getStringInput());
    }

    /**
     * Altera o CNPJ da loja com base no input do usuário.
     */
    private void alterarCnpj() {
        System.out.println("Informe o novo CNPJ da loja:");
        loja.setCnpj(input.getStringInput());
    }

    /**
     * Altera o endereço da loja com base no input do usuário.
     */
    private void alterarEndereco() {
        System.out.println("Informe o novo endereço da loja:");
        loja.setEndereco(input.getStringInput());
    }
}
