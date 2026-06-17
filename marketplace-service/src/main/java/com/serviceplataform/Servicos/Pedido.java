package com.serviceplataform.Servicos;
import java.util.Scanner;

public class Pedido {
/* 
    //Atributos do pedido 
   protected String nomeCliente;
    protected String servicoEscolhido;
    protected double precoBase;
    protected String formaPagamento;
    protected double valorFinal;

    //Scanner compartilhado
    private Scanner scanner;

    //Construtor
    public Pedido(Scanner scanner) {
        this.scanner = scanner;
    }

    //Método principal que orquestra o fluxo
    public void realizarPedido() {
        solicitarNomeCliente();
        escolherServico();
        escolherFormaPagamento();
        calcularValorFinal();
        exibirResumo();
    }

    //nome do cliente
    private void solicitarNomeCliente() {
        System.out.print("\nInforme o nome do cliente: ");
        nomeCliente = scanner.nextLine().trim();
    }

    //listagem e escolha de serviço
    private void escolherServico() {
        System.out.println("\n=== Serviços Disponíveis ===");
        for (int i = 0; i < SERVICOS.length; i++) {
            System.out.printf("  [%d] %-20s R$ %.2f%n", i + 1, SERVICOS[i], PRECOS[i]);
        }

        int opcao = lerOpcaoValida("\nEscolha o serviço desejado: ", 1, SERVICOS.length);
        servicoEscolhido = SERVICOS[opcao - 1];
        precoBase = PRECOS[opcao - 1];
    }

    //Etapa 3: forma de pagamento
    private void escolherFormaPagamento() {
        System.out.println("\n=== Formas de Pagamento ===");
        for (int i = 0; i < FORMAS_PAGAMENTO.length; i++) {
            System.out.printf("  [%d] %s%n", i + 1, FORMAS_PAGAMENTO[i]);
        }

        int opcao = lerOpcaoValida("\nEscolha a forma de pagamento: ", 1, FORMAS_PAGAMENTO.length);
        formaPagamento = FORMAS_PAGAMENTO[opcao - 1];
    }

    //cálculo do valor final
    private void calcularValorFinal() {
        valorFinal = precoBase;

        if (formaPagamento.contains("Dinheiro") || formaPagamento.contains("PIX")) {
        }
    }

    //resumo do pedido
    private void exibirResumo() {
        System.out.println("\n╔══════════════════════════════════════╗"); //que trampo miseravel isso
        System.out.println("║           RESUMO DO PEDIDO           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf( "║  Cliente   : %-23s║%n", nomeCliente);
        System.out.printf( "║  Serviço   : %-23s║%n", servicoEscolhido);
        System.out.printf( "║  Preço base: R$ %-20.2f║%n", precoBase);
        System.out.printf( "║  Pagamento : %-23s║%n", formaPagamento);
        System.out.printf( "║  TOTAL     : R$ %-20.2f║%n", valorFinal);
        System.out.println("╚══════════════════════════════════════╝");
    }

    //leitura validada de opção numérica
    private int lerOpcaoValida(String mensagem, int min, int max) {
        int opcao = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensagem);
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= min && opcao <= max) {
                    valido = true;
                } else {
                    System.out.printf("  Opção inválida! Digite entre %d e %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida! Digite um número.");
            }
        }

        return opcao;
    }*/
}
