package com.serviceplataform.Pagamentos;

/**
 * Pagamento via Cartão de Crédito.
 * Regra de negócio (RF02): taxa de 3% sobre o valor do serviço.
 *
 * TODO (integrante responsável pela lógica de pagamentos):
 *   - Validar a constante TAXA_PERCENTUAL e o arredondamento usado.
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
