package com.serviceplataform.Servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Responsável por armazenar e gerenciar os serviços cadastrados (RF01).
 *
 * Implementado como Singleton: cada tela (JPanel) é independente, então usar
 * getInstancia() garante que TODAS as telas compartilhem a mesma lista de
 * serviços em memória, sem precisar passar o catálogo manualmente entre painéis.
 *
 * TODO (integrante responsável pela lógica de serviços):
 *   - Implementar os métodos abaixo seguindo as regras de negócio do RF01:
 *       * cadastrar(): nome não pode ser vazio, valor não pode ser negativo;
 *       * o sistema deve iniciar com pelo menos 3 serviços cadastrados
 *         (ver bloco de inicialização no construtor privado, já com TODO);
 *   - As assinaturas dos métodos já correspondem ao que a UI espera consumir,
 *     então não é necessário alterar nenhum painel — só implementar
 *     o corpo de cada método aqui.
 */
public class CatalogoServicos {

    private static CatalogoServicos instancia;

    private final List<Servico> servicos;

    private CatalogoServicos() {
        servicos = new ArrayList<>();
        // TODO: o sistema deve iniciar com pelo menos 3 serviços cadastrados (RF01).
        // Exemplo (ajustar valores/descrições conforme o documento da atividade):
        // servicos.add(new Servico("Criação de Logo", "Criação de identidade visual", 150.00));
        // servicos.add(new Servico("Desenvolvimento de Site", "Criação de site institucional", 2500.00));
        // servicos.add(new Servico("Consultoria em TI", "Consultoria técnica especializada", 400.00));
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
