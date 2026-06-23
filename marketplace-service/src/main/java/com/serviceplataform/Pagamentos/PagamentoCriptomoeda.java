package com.serviceplataform.Pagamentos;

/**
 * Pagamento via Criptomoeda.
 */
public class PagamentoCriptomoeda implements Pagamento {

    private static final double TAXA_PERCENTUAL = 0.025; // 2.5%

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
        return "Criptomoeda";
    }
}
