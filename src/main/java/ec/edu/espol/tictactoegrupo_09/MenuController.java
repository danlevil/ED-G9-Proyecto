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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane background;
    @FXML
    private Button jugadorComputadora;
    @FXML
    private Button pcGame;
    @FXML
    private Button jugadoresGame;
    @FXML
    private Label tittle;
    @FXML
    private Label labelMode;

    private String gameMode;
    private char symbolPlayer1;
    private char initialSymbol;
    @FXML
    private ImageView menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Utility.changeBackGround("#F0FFFF", background);
        Utility.styleButton(pcGame);
        Utility.styleButton(jugadorComputadora);
        Utility.styleButton(jugadoresGame);
        Utility.styleLabel(labelMode, 14, false);
        Utility.styleLabel(tittle, 14, true);
        tittle.setAlignment(Pos.CENTER);
        labelMode.setAlignment(Pos.CENTER);
        pcGame.setAlignment(Pos.CENTER);
        jugadorComputadora.setAlignment(Pos.CENTER);
        jugadoresGame.setAlignment(Pos.CENTER);

    }

  
    
    @FXML
    private void modo1(ActionEvent event) throws IOException {
        gameMode = "JugadorVsComputadora";
        abrirVentanaDificultad();
        
    }

    @FXML
    private void modo2(ActionEvent event) {
        gameMode = "ComputadoraVsComputadora";
        openGameWindow();
    }

    @FXML
    private void modo3(ActionEvent event) {
        gameMode = "JugadorVsJugador";
        if (initialPlayer() && chooseSymbol()) {
        openGameWindow();
    }
    }

    private void openGameWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tablero.fxml"));
            Parent root = fxmlLoader.load();

            TableroController tableroController = fxmlLoader.getController();
            tableroController.setSymbolPlayer1(symbolPlayer1);
            tableroController.setGameMode(gameMode);
            tableroController.setinitialSymbol(initialSymbol);
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
    
    private void abrirVentanaDificultad() throws IOException{
        App.setRoot("ElegirDificultad");
   
    }

    private boolean chooseSymbol() {
        ButtonType buttonTypeX = new ButtonType("X");
        ButtonType buttonTypeO = new ButtonType("O");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecciona símbolo");
        alert.setHeaderText("¿Qué símbolo quieres para Jugador 1?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(buttonTypeX, buttonTypeO);
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        symbolPlayer1 = buttonType.getText().charAt(0);
        System.out.println(symbolPlayer1);
        return true;
    }

    private boolean initialPlayer() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecciona Turno");
        alert.setHeaderText("¿Qué símbolo quieres que empiece el juego?");
        ButtonType buttonTypeX = new ButtonType("X");
        ButtonType buttonTypeO = new ButtonType("O");
        alert.getButtonTypes().setAll(buttonTypeX, buttonTypeO);
        Optional<ButtonType> resultado = alert.showAndWait();
        if(resultado.isPresent()){
          initialSymbol = resultado.get().getText().charAt(0);
          System.out.println(initialSymbol);  
          return true;
        }else{
          
        }
        return false;
    }

    @FXML
    private void abrirMenuDeOpciones(MouseEvent event) {
        
        
        
    }

}
