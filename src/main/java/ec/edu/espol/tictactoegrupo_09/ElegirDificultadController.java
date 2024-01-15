/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoegrupo_09;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private void empezarJuegoFacil(ActionEvent event) {
        chooseSymbol();
        choosePlayer();
        try {
            gameMode = "JugadorVsComputadora";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
            Parent root = fxmlLoader.load();

            TableroController tableroController = fxmlLoader.getController();
            tableroController.setSymbolPlayer1(symbolPlayer1);
            tableroController.setGameMode(gameMode);
            tableroController.setinitialSymbol(symbolPlayer1);
            tableroController.setInitialPlayer(initialPlayer);
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

    
    @FXML
    private void empezarJuegoDificil(ActionEvent event) {
        chooseSymbol();
        choosePlayer();
        try {
            gameMode = "JugadorVsComputadora";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dificil.fxml"));
            Parent root = fxmlLoader.load();
            dificilController tableroDificil= fxmlLoader.getController();
            tableroDificil.setInitialSymbol(symbolPlayer1);
            tableroDificil.setCurrentPlayer(initialPlayer);
            /*TableroController tableroController = fxmlLoader.getController();
            tableroController.setSymbolPlayer1(symbolPlayer1);
            tableroController.setGameMode(gameMode);
            tableroController.setinitialSymbol(symbolPlayer1);
            tableroController.setInitialPlayer(initialPlayer);
            tableroController.newGame();*/
            
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
    
    private void chooseSymbol() {
        ButtonType buttonTypeX = new ButtonType("X");
        ButtonType buttonTypeO = new ButtonType("O");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecciona símbolo");
        alert.setHeaderText("¿Qué símbolo quieres para jugar?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(buttonTypeX, buttonTypeO);
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        symbolPlayer1 = buttonType.getText().charAt(0);
        System.out.println(symbolPlayer1);
    }
    
    private void choosePlayer() {
        ButtonType buttonTypeX = new ButtonType("Persona");
        ButtonType buttonTypeO = new ButtonType("Computadora");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecciona quién inicia el juego");
        alert.setHeaderText("¿Quién inicia el juego?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(buttonTypeX, buttonTypeO);
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        initialPlayer = buttonType.getText();
        System.out.println(initialPlayer);
    }



    @FXML
    private void volverMenu(MouseEvent event) throws IOException {
        App.setRoot("Menu");
     
    }
    
}
