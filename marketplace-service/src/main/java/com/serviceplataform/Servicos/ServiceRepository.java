package com.serviceplataform.Servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository {
    private final Path file;

    public ServiceRepository() {
        this(Paths.get(System.getProperty("user.dir"), "services.csv"));
    }

    public ServiceRepository(Path file) {
        this.file = file;
        try {
            File parent = this.file.toFile().getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();
            if (!Files.exists(this.file)) Files.createFile(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean save(Servico s) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file.toFile(), true))) {
            // simple CSV: name,description,price (no escaping)
            String line = (s.getNome() == null ? "" : s.getNome()) + "," +
                          (s.getDescricao() == null ? "" : s.getDescricao()) + "," +
                          s.getPreco();
            w.write(line);
            w.newLine();
            w.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized Servico findByName(String name) {
        if (name == null) return null;
        try (BufferedReader r = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 1 && parts[0].equalsIgnoreCase(name)) {
                    String descricao = parts.length > 1 ? parts[1] : "";
                    double preco = 0;
                    try { if (parts.length > 2) preco = Double.parseDouble(parts[2]); } catch (Exception ex) {}
                    return new Servico(parts[0], descricao, preco);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized List<Servico> listAll() {
        List<Servico> out = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(",", 3);
                String nome = parts.length > 0 ? parts[0] : "";
                String descricao = parts.length > 1 ? parts[1] : "";
                double preco = 0;
                try { if (parts.length > 2) preco = Double.parseDouble(parts[2]); } catch (Exception ex) {}
                out.add(new Servico(nome, descricao, preco));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
