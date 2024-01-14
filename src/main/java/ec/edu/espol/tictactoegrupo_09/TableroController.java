/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoegrupo_09;

import Estructuras.Tree;
import Estructuras.TreeNode;
import Modelo.TicTacToe;
import Modelo.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class TableroController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button button00, button01, button02, button10, button11, button12, button20, button21, button22;

    private TicTacToe juego;
    private char symbolPlayer1; //No asignado
    private String gameMode;
    private char initialSymbol; //No asignado
    private char currentPlayer;
    private String initialPlayer;
    @FXML
    private Label winnerLabel;
    @FXML
    private ImageView imgVolver;
    List<Node> casillasDisponibles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GridPane.setConstraints(button00, 0, 0);
        GridPane.setConstraints(button01, 0, 1);
        GridPane.setConstraints(button02, 0, 2);
        GridPane.setConstraints(button10, 1, 0);
        GridPane.setConstraints(button11, 1, 1);
        GridPane.setConstraints(button12, 1, 2);
        GridPane.setConstraints(button20, 2, 0);
        GridPane.setConstraints(button21, 2, 1);
        GridPane.setConstraints(button22, 2, 2);
        Utility.changeBackGround("#F0FFFF", anchorPane);
        casillasDisponibles = new ArrayList<>(gridPane.getChildren());

    }

    public void newGame() {
        System.out.println(gameMode);
        juego = new TicTacToe();

        for (Node nodo : casillasDisponibles) {
            if (nodo instanceof Button) {
                Button boton = (Button) nodo;
                boton.setText("");
                boton.setDisable(false);
                boton.setOnAction(e -> clickButton(boton));
            }
        }

        if (gameMode.equals("ComputerVsComputer")) {

        } else if (gameMode.equals("JugadorVsComputadoraFacil")) {
            currentPlayer = initialSymbol;
            if (initialPlayer.equals("Computadora")) {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                realizarMovimientoComputadora();
            }
        } else if (gameMode.equals("JugadorVsComputadoraDificil")) {
            if (!juego.HayGanador(currentPlayer) && !juego.isFull()) {
               currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                realizarMovimientoMinimax();
               // currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        } else if (gameMode.equals("JugadorVsJugador")) {
            currentPlayer = initialSymbol;
        }
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setInitialPlayer(String intialPlayer) {
        this.initialPlayer = intialPlayer;
    }

    public void setinitialSymbol(char symbol) {
        this.initialSymbol = symbol;
    }

    public char getSymbolPlayer1() {
        return symbolPlayer1;
    }

    public void setSymbolPlayer1(char symbolPlayer1) {
        this.symbolPlayer1 = symbolPlayer1;
    }

    private void clickButton(Button boton) {
        int i = GridPane.getRowIndex(boton);
        int j = GridPane.getColumnIndex(boton);
        if (juego.getCells()[i][j] == ' ') {
            juego.setSymbol(i, j, currentPlayer);
            boton.setText(currentPlayer + "");
            boton.setDisable(true);
            casillasDisponibles.remove(boton);
            checkEstado();
        }

        if (gameMode.equals("JugadorVsJugador")) {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Cambia el turno al otro jugador
        } else if (gameMode.equals("JugadorVsComputadoraFacil")) {
            if (!juego.HayGanador(currentPlayer) && !juego.isFull()) {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                realizarMovimientoComputadora();
            }
        } else if (gameMode.equals("JugadorVsComputadoraDificil")) {
            if (!juego.HayGanador(currentPlayer) && !juego.isFull()) {
                realizarMovimientoMinimax();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private void realizarMovimientoComputadora() {

        int filaComputadora, columnaComputadora;
        if (!casillasDisponibles.isEmpty()) {
            Button botonPC = (Button) casillasDisponibles.get(obtenerIndiceAleatorio(casillasDisponibles.size()));
            filaComputadora = GridPane.getRowIndex(botonPC);
            columnaComputadora = GridPane.getColumnIndex(botonPC);

            botonPC.setText(currentPlayer + "");
            botonPC.setDisable(true);
            casillasDisponibles.remove(botonPC);

            juego.setSymbol(filaComputadora, columnaComputadora, currentPlayer);

            checkEstado();
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static int obtenerIndiceAleatorio(int tamanoLista) {
        Random random = new Random();
        if (tamanoLista > 0) {
            return random.nextInt(tamanoLista);
        }
        return 0;
    }

    private void desactivarBotones() {
        for (Node nodo : gridPane.getChildren()) {
            if (nodo instanceof Button) {
                Button boton = (Button) nodo;
                boton.setDisable(true);
            }
        }
    }

//    private void realizarMovimientoMinimax() {
//        if (!casillasDisponibles.isEmpty()) {
//            // Crea un nuevo nodo de árbol con el estado actual del juego
//            TreeNode<TicTacToe> node = new TreeNode<>(juego);
//
//            // Llama al método Minimax para encontrar el mejor movimiento
//            juego.minimax(node, 2, true); // Asume una profundidad de 3
//
//            // Encuentra el mejor movimiento entre los hijos del nodo
//            TreeNode<TicTacToe> bestMove = null;
//            for (Tree<TicTacToe> child : node.getChildren()) {
//                if (bestMove == null || child.getRootNode().getUtility() > bestMove.getUtility()) {
//                    bestMove = child.getRootNode();
//                }
//            }
//
//            // Actualiza el estado del juego al mejor movimiento
//            juego = bestMove.getContent();
//
//            // Actualiza la interfaz de usuario
//            for (Node nodo : casillasDisponibles) {
//                if (nodo instanceof Button) {
//                    Button boton = (Button) nodo;
//                    int i = GridPane.getRowIndex(boton);
//                    int j = GridPane.getColumnIndex(boton);
//                    if (juego.getCells()[i][j] != ' ') {
//                        boton.setText(juego.getCells()[i][j] + "");
//                        boton.setDisable(true);
//                        casillasDisponibles.remove(boton);
//                        break;
//                    }
//                }
//            }
//
//            //checkEstado();
//           // currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
//        }
//    }
    private void realizarMovimientoMinimax() {
        if (!casillasDisponibles.isEmpty()) {
            TreeNode<TicTacToe> node = new TreeNode<>(juego);

            juego.minimax(node, 2, true); // Asume una profundidad de 3

            TreeNode<TicTacToe> bestMove = null;
            for (Tree<TicTacToe> child : node.getChildren()) {
                if (bestMove == null || child.getRootNode().getUtility() > bestMove.getUtility()) {
                    bestMove = child.getRootNode();
                }
            }

            // Verifica si bestMove es null antes de usarlo
            if (bestMove != null) {
                // Actualiza el estado del juego al mejor movimiento
                juego = bestMove.getContent();

                // Actualiza la interfaz de usuario
                for (Node nodo : casillasDisponibles) {
                    if (nodo instanceof Button) {
                        Button boton = (Button) nodo;
                        int i = GridPane.getRowIndex(boton);
                        int j = GridPane.getColumnIndex(boton);
                        if (juego.getCells()[i][j] != ' ') {
                            boton.setText(juego.getCells()[i][j] + "");
                            boton.setDisable(true);
                            casillasDisponibles.remove(boton);
                            break;
                        }
                    }
                }

                // Después de actualizar el estado del juego y la interfaz de usuario, verifica el estado del juego
                checkEstado();
            } else {
                // Maneja el caso en que bestMove es null
                // Puedes decidir qué hacer en este caso según las necesidades de tu aplicación
            }

            // Cambia el turno al otro jugador
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private void checkEstado() {
        if (juego.HayGanador(currentPlayer)) {
            desactivarBotones();
            winnerLabel.setText("EL GANADOR ES: " + currentPlayer);
            winnerLabel.setAlignment(Pos.CENTER);
            Utility.styleLabel(winnerLabel, 30, true);
            winnerLabel.setTextFill(Color.BLACK);
        } else if (juego.isFull() && !juego.HayGanador(currentPlayer)) {
            winnerLabel.setText("EMPATE");
            winnerLabel.setAlignment(Pos.CENTER);
            Utility.styleLabel(winnerLabel, 30, true);
            winnerLabel.setTextFill(Color.BLACK);
        }
    }

    @FXML
    private void regresarAmenu(MouseEvent event) throws IOException {
        if (gameMode.equals("JugadorVsComputadora")) {

        } else if (gameMode.equals("JugadorVsJugador")) {

        }
    }

}
