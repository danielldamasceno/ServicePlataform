package com.serviceplataform.Pagamentos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Fábrica central de formas de pagamento.
 *
 * Por que esta classe existe:
 *   A UI (PedidoPanel) não deve conhecer PagamentoPix, PagamentoBoleto, etc.
 *   diretamente. Ela só conhece a interface Pagamento. Assim, quando a Etapa 2
 *   pedir novas formas de pagamento (Criptomoeda, Carteira Digital, Débito),
 *   basta registrá-las aqui — SEM alterar PedidoPanel.
 *
 * TODO (integrante responsável pela lógica de pagamentos):
 *   1. Para cada nova forma de pagamento da Etapa 2, criar a classe
 *      (ex: PagamentoCriptomoeda implements Pagamento) e adicionar uma linha
 *      no método registrarFormasPadrao(), seguindo o mesmo padrão das já
 *      existentes.
 *   2. A ordem de inserção no LinkedHashMap define a ordem de exibição no
 *      JComboBox da tela de Pedido.
 */
public final class PagamentoFactory {

    private static final Map<String, Supplier<Pagamento>> FORMAS_DISPONIVEIS = new LinkedHashMap<>();

    static {
        registrarFormasPadrao();
    }

    private PagamentoFactory() {
        // classe utilitária, não deve ser instanciada
    }

    private static void registrarFormasPadrao() {
        registrar("PIX", PagamentoPix::new);
        registrar("Cartão de Crédito", PagamentoCartaoCredito::new);
        registrar("Boleto Bancário", PagamentoBoleto::new);

        // TODO (Etapa 2): registrar as novas formas de pagamento aqui, por exemplo:
        // registrar("Criptomoeda", PagamentoCriptomoeda::new);
        // registrar("Carteira Digital", PagamentoCarteiraDigital::new);
        // registrar("Débito", PagamentoDebito::new);
    }

    /**
     * Registra uma nova forma de pagamento disponível no sistema.
     *
     * @param nomeExibicao nome que aparecerá no JComboBox da UI.
     * @param fabrica      forma de criar uma nova instância de Pagamento.
     */
    public static void registrar(String nomeExibicao, Supplier<Pagamento> fabrica) {
        FORMAS_DISPONIVEIS.put(nomeExibicao, fabrica);
    }

    /**
     * @return nomes de todas as formas de pagamento disponíveis, na ordem
     *         em que foram registradas. Usado para popular o JComboBox.
     */
    public static List<String> listarNomesDisponiveis() {
        return new ArrayList<>(FORMAS_DISPONIVEIS.keySet());
    }

    /**
     * Cria a instância de Pagamento correspondente ao nome de exibição
     * escolhido pelo usuário na UI.
     *
     * @param nomeExibicao valor selecionado no JComboBox (ex: "PIX").
     * @return instância de Pagamento correspondente.
     * @throws IllegalArgumentException se o nome não estiver registrado.
     */
    public static Pagamento criar(String nomeExibicao) {
        Supplier<Pagamento> fabrica = FORMAS_DISPONIVEIS.get(nomeExibicao);
        if (fabrica == null) {
            throw new IllegalArgumentException("Forma de pagamento não encontrada: " + nomeExibicao);
        }
        return fabrica.get();
    }
}
