module g9 {
    requires javafx.controls;
    requires javafx.fxml;

    opens g9 to javafx.fxml;
    exports g9;
}
