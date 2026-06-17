package com.serviceplataform.Pagamentos;

public class BoletoPagamento implements Pagamento {

    private static final double TAXA_FIXA = 5.0; // R$5 fixos

    @Override
    public boolean pagar(double valor) {
        if (valor < 0) return false;
        double taxa = TAXA_FIXA;
        double total = valor + taxa;
        System.out.println(String.format("Boleto: valor R$ %.2f | taxa fixa R$ %.2f | total R$ %.2f", valor, taxa, total));
        return true;
    }
}
