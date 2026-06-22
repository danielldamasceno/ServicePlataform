package com.serviceplataform.Servicos;

public class Cadastrar {
    public class Servico {
    private String nome;
    private String descricao;
    private double valor;

    // Construtor
    public Servico(String nome, String descricao, double valor) {
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
    }

    // Getters e Setters com as Regras de Negócio
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do serviço não poderá ser vazio.");
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
            throw new IllegalArgumentException("O valor do serviço não poderá ser negativo.");
        }
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Descrição: " + descricao + " | Valor: R$ " + String.format("%.2f", valor);
    }
}
}
