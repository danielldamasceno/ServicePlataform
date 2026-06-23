package com.serviceplataform.Pagamentos;

/**
Pagamento via Boleto Bancário.
*/
public class PagamentoBoleto implements Pagamento {

    private static final double TAXA_FIXA = 5.0;

    @Override
    public double calcularValorFinal(double valorBase) {
        return valorBase + calcularTaxa(valorBase);
    }

    @Override
    public double calcularTaxa(double valorBase) {
        return TAXA_FIXA;
    }

    @Override
    public String getNomeExibicao() {
        return "Boleto Bancário";
    }
}
