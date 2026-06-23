package com.serviceplataform.Pagamentos;

/**
 * Contrato comum entre todas as formas de pagamento.
 *
 * RESPONSÁVEL POR IMPLEMENTAR (lógica de negócio): outro integrante do grupo.
 * A UI (PedidoPanel) já está pronta para consumir esta interface através
 * dos métodos {@link #calcularValorFinal(double)} e {@link #getNomeExibicao()}.
 *
 * Para adicionar uma nova forma de pagamento (ex: Etapa 2 - Criptomoeda,
 * Carteira Digital, Débito):
 *   1. Criar uma nova classe em com.serviceplataform.Pagamentos que implemente
 *      esta interface;
 *   2. Implementar calcularValorFinal(), calcularTaxa() e getNomeExibicao();
 *   3. Registrar a nova forma de pagamento em PagamentoFactory
 *      (ver TODO em PagamentoFactory.java) para que ela apareça automaticamente
 *      na tela de Pedido, sem precisar tocar no PedidoPanel.
 */
public interface Pagamento {

    /**
     * Calcula o valor final a ser cobrado, aplicando a taxa (se houver)
     * sobre o valor base do serviço.
     *
     * @param valorBase valor original do serviço, sem taxas.
     * @return valor final já com a taxa da forma de pagamento aplicada.
     */
    double calcularValorFinal(double valorBase);

    /**
     * Retorna o valor (em R$) da taxa aplicada sobre o valorBase informado.
     * Útil para exibir "Taxa Aplicada: R$ X,XX" no resumo do pedido.
     *
     * @param valorBase valor original do serviço, sem taxas.
     * @return valor da taxa cobrada.
     */
    double calcularTaxa(double valorBase);

    /**
     * Nome amigável da forma de pagamento, exibido na UI
     * (ex: "PIX", "Cartão de Crédito", "Boleto Bancário").
     */
    String getNomeExibicao();
}
