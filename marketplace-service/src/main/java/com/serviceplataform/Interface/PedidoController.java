package com.serviceplataform.Interface;

import com.serviceplataform.Servicos.Pedido;
import com.serviceplataform.Servicos.ServiceRepository;
import com.serviceplataform.Servicos.Servico;

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
        // carregar serviços do arquivo (ServiceRepository)
        ServiceRepository repo = new ServiceRepository();
        java.util.List<Servico> all = repo.listAll();
        if (all != null && !all.isEmpty()) {
            for (Servico s : all) {
                choiceService.getItems().add(s.getNome());
            }
        } else {
            choiceService.getItems().addAll("Serviço A", "Serviço B", "Serviço C");
        }
        if (!choiceService.getItems().isEmpty()) choiceService.setValue(choiceService.getItems().get(0));
        choicePayment.getItems().addAll("PIX", "Cartão de Crédito", "Boleto");
    }

    @FXML
    private void confirmarPedido(ActionEvent event) {
        String nome = txtNome.getText();
        String servico = choiceService.getValue();
        String pagamento = choicePayment.getValue();

        double preco = 0;
        ServiceRepository repo = new ServiceRepository();
        Servico s = repo.findByName(servico);
        if (s != null) {
            preco = s.getPreco();
        }

        Pedido pedido = new Pedido(nome, servico, preco, pagamento);
        boolean ok = pedido.processarPagamento();
        if (ok) {
            valorTotal.setText(String.format("R$ %.2f", pedido.getValorFinal()));
            System.out.println("Pedido processado: " + nome + " | " + servico + " | " + pagamento + " | total R$ " + pedido.getValorFinal());
        } else {
            System.out.println("Falha ao processar pagamento para: " + nome);
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
