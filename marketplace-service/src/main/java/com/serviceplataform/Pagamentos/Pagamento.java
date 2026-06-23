package com.serviceplataform.Pagamentos;

/**
 * Contrato comum entre todas as formas de pagamento.
*/
public interface Pagamento {

    double calcularValorFinal(double valorBase);
    double calcularTaxa(double valorBase);

    String getNomeExibicao();
}
