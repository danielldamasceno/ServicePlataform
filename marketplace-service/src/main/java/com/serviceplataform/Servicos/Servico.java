package com.serviceplataform.Servicos;

/**
 * Representa um serviço disponível para contratação (RF01).
 *
 * TODO (integrante responsável pela lógica de serviços):
 *   - Validar as regras de negócio no construtor/setters, por exemplo:
 *       * nome não pode ser vazio;
 *       * valor não pode ser negativo.
 *   - Pode lançar IllegalArgumentException quando a regra for violada;
 *     a UI (CadastrarPanel) já está preparada para capturar exceções
 *     e exibir mensagem de erro ao usuário.
 */
public class Servico {

    private String nome;
    private String descricao;
    private double valor;

    public Servico(String nome, String descricao, double valor) {
        // TODO: aplicar validações de negócio aqui (nome não vazio, valor >= 0)
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        // TODO: validar nome não vazio antes de atribuir
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        // TODO: validar valor >= 0 antes de atribuir
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
