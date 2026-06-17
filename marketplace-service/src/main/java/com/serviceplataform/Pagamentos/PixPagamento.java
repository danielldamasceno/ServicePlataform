package com.serviceplataform.Pagamentos;

public class PixPagamento implements Pagamento {

    @Override
    public boolean pagar(double valor) {
        if (valor <= 0) return false;
        double taxa = 0.0;
        double total = valor + taxa;
        System.out.println(String.format("PIX: valor R$ %.2f | taxa R$ %.2f | total R$ %.2f", valor, taxa, total));
        return true;
    }
}
