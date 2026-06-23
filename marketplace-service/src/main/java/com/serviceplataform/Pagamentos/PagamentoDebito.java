package com.serviceplataform.Pagamentos;

/**
 * Pagamento via Débito.
 */
public class PagamentoDebito implements Pagamento {

    private static final double TAXA_PERCENTUAL = 0.01; // 1.0%

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
        return "Débito";
    }
}
