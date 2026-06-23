package com.serviceplataform.Pagamentos;

/**
 * Pagamento via Boleto Bancário.
 * Regra de negócio (RF02): taxa fixa de R$ 5,00.
 *
 * TODO (integrante responsável pela lógica de pagamentos):
 *   - Validar a constante TAXA_FIXA.
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
