module ec.edu.espol.tictactoegrupo_09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.tictactoegrupo_09 to javafx.fxml;
    exports ec.edu.espol.tictactoegrupo_09;
}
