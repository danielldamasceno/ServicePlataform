package com.serviceplataform.Pagamentos;

/**
Pagamento via PIX.
 */
public class PagamentoPix implements Pagamento {

    @Override
    public double calcularValorFinal(double valorBase) {
        return valorBase + calcularTaxa(valorBase);
    }

    @Override
    public double calcularTaxa(double valorBase) {
        return 0.0;
    }

    @Override
    public String getNomeExibicao() {
        return "PIX";
    }
}
