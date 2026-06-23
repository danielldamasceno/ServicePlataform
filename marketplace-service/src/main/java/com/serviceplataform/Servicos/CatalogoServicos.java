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
        servicos.add(new Servico("Criação de Logo", "Criação de identidade visual para marcas", 150.00));
        servicos.add(new Servico("Desenvolvimento de Site", "Criação de site institucional", 2500.00));
        servicos.add(new Servico("Consultoria em TI", "Consultoria técnica especializada", 400.00));
    }

    public static synchronized CatalogoServicos getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoServicos();
        }
        return instancia;
    }

    public void cadastrar(Servico servico) {
        servicos.add(servico);
    }

    public List<Servico> listarTodos() {
        return new ArrayList<>(servicos);
    }

    public Optional<Servico> buscarPorNome(String nome) {
        return servicos.stream()
                .filter(s -> s.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public boolean removerPorNome(String nome) {
        return servicos.removeIf(s -> s.getNome().equalsIgnoreCase(nome));
    }
}
