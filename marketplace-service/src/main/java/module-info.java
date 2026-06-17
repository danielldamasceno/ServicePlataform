module com.serviceplataform {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.serviceplataform to javafx.fxml;
    opens com.serviceplataform.Interface to javafx.fxml;
    opens com.serviceplataform.Servicos to javafx.fxml;
    opens com.serviceplataform.Pagamentos to javafx.fxml;

    exports com.serviceplataform;
    exports com.serviceplataform.Interface;
    exports com.serviceplataform.Servicos;
    exports com.serviceplataform.Pagamentos;
}
