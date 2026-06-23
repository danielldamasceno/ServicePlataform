package com.serviceplataform.Pagamentos;

/**
 * Pagamento via Carteira Digital.
 */
public class PagamentoCarteiraDigital implements Pagamento {

    private static final double TAXA_PERCENTUAL = 0.015; // 1.5%

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
        return "Carteira Digital";
    }
}
