/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoegrupo_09;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author danlevil
 */
public class dificilController implements Initializable {


    @FXML
    private ImageView im6;
    @FXML
    private ImageView im5;
    @FXML
    private ImageView im3;
    @FXML
    private ImageView im9;
    @FXML
    private ImageView im8;
    @FXML
    private ImageView im7;
    @FXML
    private ImageView im4;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private ImageView volver;
    
    //Datos que llegan de ElegirDificultadController
    private String currentPlayer;
    private char initialSymbol;
    
    //Datos que yo voy actualizando
    private String currentSymbol;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    
    @FXML
    private void volverMenu(MouseEvent event) {
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        System.out.println("initial de dificil: "+this.currentPlayer);
    }

    public void setInitialSymbol(char initialSymbol) {
        this.initialSymbol = initialSymbol;
        if(initialSymbol=='X'){
            currentSymbol=App.urlXfire;
        }else{
            currentSymbol=App.urlOfire;
        }
    }
    
    private void changeImageView(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        
        if (clickedImageView.getImage() == null) {
            Image newImage = new Image(currentSymbol);
            clickedImageView.setImage(newImage);
            changeCurrentSymbol(event);
        } else {
            clickedImageView.setImage(null);
        }
        
    }
    private void changeCurrentSymbol(MouseEvent event){
        currentSymbol=(this.currentSymbol.equals(App.urlXfire))?App.urlOfire:App.urlXfire;
    }
    private void changeCurrentSymbol(){
        currentSymbol=(this.currentSymbol.equals(App.urlXfire))?App.urlOfire:App.urlXfire;
    }

    
    


    
    @FXML
    private void changeIM1(MouseEvent event) {
    }

    @FXML
    private void changeIM2(MouseEvent event) {
    }
    @FXML
    private void changeIM3(MouseEvent event) {
    }
    @FXML
    private void changeIM4(MouseEvent event) {
    }
    @FXML
    private void changeIM5(MouseEvent event) {
    }
    @FXML
    private void changeIM6(MouseEvent event) {
    }
    @FXML
    private void changeIM7(MouseEvent event) {
    }
    @FXML
    private void changeIM8(MouseEvent event) {
    }
    @FXML
    private void changeIM9(MouseEvent event) {
    }


    
    
    
    
}
