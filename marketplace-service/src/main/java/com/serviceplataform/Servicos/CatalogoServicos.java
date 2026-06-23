package com.serviceplataform.Servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
Armazena e gerencia os serviços cadastrados (RF01).
 */
public class CatalogoServicos {

    private static CatalogoServicos instancia;

    private final List<Servico> servicos;

    private CatalogoServicos() {
        servicos = new ArrayList<>();
    }

    public static synchronized CatalogoServicos getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoServicos();
        }
        return instancia;
    }

    /**
     * Cadastra um novo serviço no catálogo.
     *
     * @param servico serviço a ser adicionado.
     * @throws IllegalArgumentException se nome for vazio ou valor for negativo.
     */
    public void cadastrar(Servico servico) {
        // TODO: validar regras de negócio (nome não vazio, valor >= 0)
        // antes de adicionar à lista.
        servicos.add(servico);
    }

    /**
     * @return lista de todos os serviços cadastrados.
     */
    public List<Servico> listarTodos() {
        return new ArrayList<>(servicos);
    }

    /**
     * Busca um serviço pelo nome (RF01).
     *
     * @param nome nome do serviço a ser buscado.
     * @return Optional contendo o serviço, ou Optional.empty() se não encontrado.
     */
    public Optional<Servico> buscarPorNome(String nome) {
        // TODO: implementar busca (case-insensitive é recomendado).
        return Optional.empty();
    }

    /**
     * Remove um serviço do catálogo pelo nome.
     *
     * @param nome nome do serviço a ser removido.
     * @return true se o serviço foi encontrado e removido, false caso contrário.
     */
    public boolean removerPorNome(String nome) {
        // TODO: implementar remoção.
        return false;
    }
}
