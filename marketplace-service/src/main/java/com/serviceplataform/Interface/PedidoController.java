package com.serviceplataform.Interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PedidoController {

    @FXML private TextField txtNome;
    @FXML private ChoiceBox<String> choiceService;
    @FXML private ChoiceBox<String> choicePayment;
    @FXML private Label valorTotal;
    @FXML private Button btnVoltar;
    @FXML private Button btnOk;

    @FXML
    public void initialize() {
        // TODO: carregar serviços do banco de dados
        choiceService.getItems().addAll("Serviço A", "Serviço B", "Serviço C");
        choicePayment.getItems().addAll("PIX", "Cartão de Crédito", "Boleto");
    }

    @FXML
    private void confirmarPedido(ActionEvent event) {
        String nome = txtNome.getText();
        String servico = choiceService.getValue();
        String pagamento = choicePayment.getValue();

        // TODO: salvar pedido no banco de dados
        System.out.println("Pedido: " + nome + " | " + servico + " | " + pagamento);
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
