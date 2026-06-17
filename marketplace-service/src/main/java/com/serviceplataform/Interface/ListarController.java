package com.serviceplataform.Interface;

import java.util.List;

import com.serviceplataform.Servicos.ServiceRepository;
import com.serviceplataform.Servicos.Servico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ListarController {

    @FXML private ListView<String> listView;
    @FXML private Button btnVoltar;

    @FXML
    public void initialize() {
        ServiceRepository repo = new ServiceRepository();
        List<Servico> all = repo.listAll();
        for (Servico s : all) {
            listView.getItems().add(s.getNome() + " - R$ " + s.getPreco());
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Interface.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setTitle("Service Marketplace");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
