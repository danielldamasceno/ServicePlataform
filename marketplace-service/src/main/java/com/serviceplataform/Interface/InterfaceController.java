package com.serviceplataform.Interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InterfaceController {

    @FXML private Button btnPedido;
    @FXML private Button btnBuscar;
    @FXML private Button btnCadastrar;
    @FXML private Button btnListar;
    @FXML private Button btnSair;

    @FXML
    private void abrirPedido(ActionEvent event) {
        abrirTela("/fxml/Pedido.fxml", "Fazer Pedido", event);
    }

    @FXML
    private void abrirPesquisar(ActionEvent event) {
        abrirTela("/fxml/Pesquisar.fxml", "Buscar Serviço", event);
    }

    @FXML
    private void abrirCadastrar(ActionEvent event) {
        abrirTela("/fxml/Cadastrar.fxml", "Cadastrar Serviço", event);
    }

    @FXML
    private void abrirListar(ActionEvent event) {
        // TODO: implementar quando a tela de listagem estiver pronta
        System.out.println("Listar Serviços - em breve!");
    }

    @FXML
    private void sair(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    private void abrirTela(String fxmlPath, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
