package com.serviceplataform.Interface;

import com.serviceplataform.Servicos.ServiceRepository;
import com.serviceplataform.Servicos.Servico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarController {

    @FXML private TextField txtNome;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private Button btnVoltar;
    @FXML private Button btnOk;

    @FXML
    private void cadastrar(ActionEvent event) {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        String valor = txtValor.getText();

            double preco = 0;
            try { preco = Double.parseDouble(valor.replace(',', '.')); } catch (Exception e) { }
            Servico s = new Servico(nome, descricao, preco);
            ServiceRepository repo = new ServiceRepository();
            boolean ok = repo.save(s);
            if (ok) System.out.println("Cadastrado (arquivo): " + s.getNome());
            else System.out.println("Falha ao gravar: " + s.getNome());
            txtNome.clear(); txtDescricao.clear(); txtValor.clear();
    }

    @FXML
    private void voltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Interface.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle("Service Marketplace");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
