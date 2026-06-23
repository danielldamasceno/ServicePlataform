package com.serviceplataform.Pagamentos;

/**
Pagamento via Cartão de Crédito.
*/
public class PagamentoCartaoCredito implements Pagamento {

    private static final double TAXA_PERCENTUAL = 0.03; // 3%

    @Override
    public double calcularValorFinal(double valorBase) {
        return valorBase + calcularTaxa(valorBase);
    }

    @Override
    public double calcularTaxa(double valorBase) {
        return valorBase * TAXA_PERCENTUAL;
    }

    @Override
    public String getNomeExibicao() {
        return "Cartão de Crédito";
    }
}
