package com.serviceplataform.Pagamentos;

public interface Pagamento {
    /**
     * Realiza um pagamento de um determinado valor.
     * @param valor valor a pagar
     * @return true se o pagamento for bem-sucedido
     */
    boolean pagar(double valor);
}
