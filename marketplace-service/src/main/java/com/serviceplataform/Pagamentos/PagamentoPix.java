package com.serviceplataform.Pagamentos;

/**
 * Pagamento via PIX.
 * Regra de negócio (RF02): não possui taxa adicional, valor final = valor do serviço.
 *
 * TODO (integrante responsável pela lógica de pagamentos):
 *   - Confirmar/implementar o cálculo abaixo conforme regra de negócio definitiva.
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
