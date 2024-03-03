module cr.ac.una.tareaprogra {
    requires javafx.controls;
    requires javafx.fxml;

    opens cr.ac.una.tareaprogra to javafx.fxml;
    exports cr.ac.una.tareaprogra;
    requires javafx.graphicsEmpty;
}
