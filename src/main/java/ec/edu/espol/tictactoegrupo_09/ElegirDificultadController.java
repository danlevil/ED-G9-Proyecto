/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoegrupo_09;

import Modelo.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Charlie
 */
public class ElegirDificultadController implements Initializable {

    @FXML
    private ImageView imgVolver;
    @FXML
    private Button btFacil;
    @FXML
    private Button btDificil;

    private String gameMode;
    private char symbolPlayer1;
    private char initialSymbol;
    private String initialPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void empezarJuegoFacil(ActionEvent event) throws IOException {
        if (chooseSymbol()) {
            if (choosePlayer()) {
                try {
                    gameMode = "JugadorVsComputadora";
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
                    Parent root = fxmlLoader.load();

                    TableroController tableroController = fxmlLoader.getController();
                    tableroController.setSymbolPlayer1(symbolPlayer1);
                    tableroController.setGameMode(gameMode);
                    tableroController.setinitialSymbol(symbolPlayer1);
                    tableroController.setInitialPlayer(initialPlayer);
                    tableroController.setDificultadJuego("Facil");
                    tableroController.newGame();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Tres en Raya");
                    Image icono = new Image("images/tictactoe.png");
                    stage.getIcons().add(icono);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @FXML
    private void empezarJuegoDificil(ActionEvent event) throws IOException {

        if (chooseSymbol()) {
            if (choosePlayer()) {
                try {
                    gameMode = "JugadorVsComputadora";
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
                    Parent root = fxmlLoader.load();

                    TableroController tableroController = fxmlLoader.getController();
                    tableroController.setSymbolPlayer1(symbolPlayer1);
                    tableroController.setGameMode(gameMode);
                    tableroController.setinitialSymbol(symbolPlayer1);
                    tableroController.setInitialPlayer(initialPlayer);
                    tableroController.setDificultadJuego("Dificil");
                    tableroController.newGame();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Tres en Raya");
                    Image icono = new Image("images/tictactoe.png");
                    stage.getIcons().add(icono);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private boolean chooseSymbol() throws IOException {
        ButtonType buttonTypeX = new ButtonType("X");
        ButtonType buttonTypeO = new ButtonType("O");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecciona símbolo");
        alert.setHeaderText("¿Qué símbolo quieres para Jugador 1?");
        alert.getButtonTypes().setAll(buttonTypeX, buttonTypeO, buttonTypeCancel);

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(buttonTypeCancel);
        cancelButton.setVisible(false);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() != buttonTypeCancel) {
            symbolPlayer1 = result.get().getText().charAt(0);
            System.out.println(symbolPlayer1);
            return true;
        } else {

        }

        return false;
    }

    private boolean choosePlayer() {
        ButtonType buttonTypeX = new ButtonType("Persona");
        ButtonType buttonTypeO = new ButtonType("Computadora");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecciona quién inicia el juego");
        alert.setHeaderText("¿Quién inicia el juego?");
        alert.getButtonTypes().setAll(buttonTypeX, buttonTypeO, buttonTypeCancel);

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(buttonTypeCancel);
        cancelButton.setVisible(false);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() != buttonTypeCancel) {
            initialPlayer = result.get().getText();
            System.out.println(initialPlayer);
            return true;
        }

        return false;
    }

    @FXML
    private void volverMenu() throws IOException {
        App.setRoot("Menu");

    }

}
