package com.serviceplataform.Servicos;

import java.util.ArrayList;
import java.util.List;

public class ServiceStore {
    private static final List<Servico> list = new ArrayList<>();

    public static synchronized void add(Servico s) {
        list.add(s);
    }

    public static synchronized Servico findByName(String name) {
        if (name == null) return null;
        for (Servico s : list) {
            if (s.getNome() != null && s.getNome().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public static synchronized List<Servico> listAll() {
        return new ArrayList<>(list);
    }
}
