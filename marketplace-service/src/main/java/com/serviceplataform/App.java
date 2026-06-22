package com.serviceplataform;

import java.util.List;

import com.serviceplataform.Servicos.ServiceRepository;
import com.serviceplataform.Servicos.Servico;
import com.serviceplataform.Servicos.ServicoRepository;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/Interface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Service Marketplace");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {

        //launch(args);

        // Criando o repositório usando o polimorfismo (Interface de um lado, Implementação do outro)
        ServiceRepository repositorio = new ServiceRepository();

        // Regra de negócio: Iniciar com pelo menos três serviços cadastrados
        repositorio.cadastrar(new Servico("Criação de Logo", "Design de identidade visual", 150.00));
        repositorio.cadastrar(new Servico("Desenvolvimento de Site", "Criação de site responsivo", 2500.00));
        repositorio.cadastrar(new Servico("Consultoria em TI", "Consultoria especializada em infraestrutura", 4000.00));

        // 1. Testando a Listagem de todos os serviços
        System.out.println("\n--- Lista de Todos os Serviços ---");
        List<Servico> todos = repositorio.listarTodos();
        for (Servico s : todos) {
            System.out.println(s);
        }

        // 2. Testando a Busca por Nome e Exibição de informações completas
        System.out.println("\n--- Testando Busca ---");
        repositorio.exibirDetalhes("Criação de Logo");

        // 3. Testando as Regras de Negócio (Validações) pegando erros propositais
        System.out.println("\n--- Testando Validações (Devem gerar erros) ---");
        try {
            Servico servicoInvalido1 = new Servico("", "Teste", 100); // Nome vazio
        } catch (IllegalArgumentException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        try {
            Servico servicoInvalido2 = new Servico("Serviço Barato", "Teste", -50.00); // Valor negativo
        } catch (IllegalArgumentException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
    }
    
}
