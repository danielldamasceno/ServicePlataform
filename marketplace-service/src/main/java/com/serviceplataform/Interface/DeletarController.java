package com.serviceplataform.Interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeletarController {

    @FXML private TextField txtNome;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private Button btnVoltar;
    @FXML private Button btnOk;

    @FXML
    private void deletar(ActionEvent event) {
        String nome = txtNome.getText();

        // TODO: deletar do banco de dados
        System.out.println("Deletando: " + nome);
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
