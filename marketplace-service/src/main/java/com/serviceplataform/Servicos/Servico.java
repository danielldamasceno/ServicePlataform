package com.serviceplataform.Servicos;

/**
 * Representa um serviço disponível para contratação RF01.
 */
public class Servico {

    private String nome;
    private String descricao;
    private double valor;

    public Servico(String nome, String descricao, double valor) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do serviço não pode ser vazio.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do serviço não pode ser negativo.");
        }
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do serviço não pode ser vazio.");
        }
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
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do serviço não pode ser negativo.");
        }
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
