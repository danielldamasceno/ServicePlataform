package com.serviceplataform.Pagamentos;

public class CartaoCreditoPagamento implements Pagamento {

    private static final double TAXA_PERCENT = 0.03; // 3%

    @Override
    public boolean pagar(double valor) {
        if (valor <= 0) return false;
        double taxa = valor * TAXA_PERCENT;
        double total = valor + taxa;
        System.out.println(String.format("Cartão: valor R$ %.2f | taxa (3%%) R$ %.2f | total R$ %.2f", valor, taxa, total));
        return true;
    }
}
