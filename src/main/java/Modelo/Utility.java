/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author johan
 */
public class Utility {

    public static void changeBackGround(String string, AnchorPane anchorpane) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.web(string), CornerRadii.EMPTY, Insets.EMPTY);
        Background background1 = new Background(backgroundFill);
        anchorpane.setBackground(background1);
    }

    public static void styleButton(Button button) {
        button.setStyle("-fx-border-color: #0D0D0D; -fx-font-family: Monospaced;");
    }

    public static void styleLabel(Label label, int fontSize, boolean isBold) {
        String style = "-fx-font-family: 'Times New Roman'; -fx-font-size: " + fontSize + "px;";
        if (isBold) {
            style += " -fx-font-weight: bold;";
        }
        label.setStyle(style);
    }

}
