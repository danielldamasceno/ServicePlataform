package com.serviceplataform.Pagamentos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
formas de pagamento.
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
        registrar("Carteira Digital", PagamentoCarteiraDigital::new);
        registrar("Débito", PagamentoDebito::new);
        registrar("Criptomoeda", PagamentoCriptomoeda::new);
        registrar("Cartão de Crédito", PagamentoCartaoCredito::new);
        registrar("Boleto Bancário", PagamentoBoleto::new);
    }

    public static void registrar(String nomeExibicao, Supplier<Pagamento> fabrica) {
        FORMAS_DISPONIVEIS.put(nomeExibicao, fabrica);
    }
    
    public static List<String> listarNomesDisponiveis() {
        return new ArrayList<>(FORMAS_DISPONIVEIS.keySet());
    }

    public static Pagamento criar(String nomeExibicao) {
        Supplier<Pagamento> fabrica = FORMAS_DISPONIVEIS.get(nomeExibicao);
        if (fabrica == null) {
            throw new IllegalArgumentException("Forma de pagamento não encontrada: " + nomeExibicao);
        }
        return fabrica.get();
    }
}
