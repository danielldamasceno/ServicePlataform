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

public class PesquisarController {

    @FXML private TextField txtNome;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private Button btnVoltar;
    @FXML private Button btnOk;

    @FXML
    private void pesquisar(ActionEvent event) {
        String nome = txtNome.getText();
        if (nome == null || nome.isEmpty()) return;
        ServiceRepository repo = new ServiceRepository();
        Servico s = repo.findByName(nome);
        if (s != null) {
            txtDescricao.setText(s.getDescricao());
            txtValor.setText(String.valueOf(s.getPreco()));
        } else {
            txtDescricao.setText("");
            txtValor.setText("");
            System.out.println("Serviço não encontrado: " + nome);
        }
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
