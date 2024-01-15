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
            if (initialPlayer.equals("Computadora")) {
                currentPlayer = initialSymbol;
                realizarMovimientoComputadora();
            } else {
                currentPlayer = initialSymbol;
            }

        } else if (gameMode.equals("JugadorVsComputadoraDificil")) {
            if (initialPlayer.equals("Computadora")) {
                currentPlayer = (symbolPlayer1 == 'X') ? 'O' : 'X';
                realizarMovimientoMinimax(currentPlayer);
                System.out.println(currentPlayer + "CIRCULO");
                System.out.println(initialSymbol + "EQUIS");
            } else {
                currentPlayer = initialSymbol;

                char computadora = (initialSymbol == 'X') ? 'O' : 'X';
                realizarMovimientoMinimax(computadora);
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
        if (gameMode.equals("JugadorVsJugador")) {
            int i = GridPane.getRowIndex(boton);
            int j = GridPane.getColumnIndex(boton);
            if (juego.getCells()[i][j] == ' ') {
                juego.setSymbol(i, j, currentPlayer);
                boton.setText(currentPlayer + "");
                boton.setDisable(true);
                checkEstado();

            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Cambia el turno al otro jugador
        } else if (gameMode.equals("JugadorVsComputadoraFacil")) {
            int i = GridPane.getRowIndex(boton);
            int j = GridPane.getColumnIndex(boton);
            if (juego.getCells()[i][j] == ' ') {
                juego.setSymbol(i, j, currentPlayer);
                boton.setText(currentPlayer + "");
                boton.setDisable(true);
                casillasDisponibles.remove(boton);
                System.out.println(casillasDisponibles);
                checkEstado();

            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            realizarMovimientoComputadora();

        } else if (gameMode.equals("JugadorVsComputadoraDificil")) {
            int i = GridPane.getRowIndex(boton);
            int j = GridPane.getColumnIndex(boton);
            if (juego.getCells()[i][j] == ' ') {
                juego.setSymbol(i, j, currentPlayer);
                boton.setText(currentPlayer + "");
                boton.setDisable(true);
                checkEstado();

                // Cambia el turno al otro jugador
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

                // Si es el turno de la computadora y la computadora es el jugador inicial, realiza el movimiento Minimax
                realizarMovimientoMinimax(currentPlayer);
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

    private void actualizarInterfazUsuarioConMejorMovimiento(TreeNode<TicTacToe> bestMove) {
        // Obtén el contenido del mejor movimiento
        TicTacToe juego = bestMove.getContent();
        char[][] cells = juego.getCells();

        // Recorre todas las casillas y actualiza la interfaz de usuario
        for (Node nodo : casillasDisponibles) {
            if (nodo instanceof Button) {
                Button boton = (Button) nodo;
                int i = GridPane.getRowIndex(boton);
                int j = GridPane.getColumnIndex(boton);

                // Si la celda en el mejor movimiento no está vacía y el botón aún no está desactivado
                if (cells[i][j] != ' ' && !boton.isDisabled()) {
                    // Actualiza el texto del botón con el símbolo correspondiente
                    boton.setText(String.valueOf(cells[i][j]));
                    // Desactiva el botón para evitar más interacciones
                    boton.setDisable(true);
                }
            }
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

    private void realizarMovimientoMinimax(char maximizer) {
        if (!casillasDisponibles.isEmpty()) {
            TreeNode<TicTacToe> node = new TreeNode<>(juego);

            juego.minimax(node, 1, true, maximizer);

            TreeNode<TicTacToe> bestMove = null;
            int bestValue = Integer.MIN_VALUE; // Inicializa para maximizar
            for (Tree<TicTacToe> child : node.getChildren()) {
                System.out.println(child.getRoot());
                int childUtility = child.getRootNode().getUtility();
                if (childUtility > bestValue) {
                    bestMove = child.getRootNode();
                    bestValue = childUtility;
                }
            }

            // Verifica si bestMove es null antes de usarlo
            if (bestMove != null) {
                // Actualiza el estado del juego al mejor movimiento
                juego = bestMove.getContent();

                // Actualiza la interfaz de usuario
                actualizarInterfazUsuarioConMejorMovimiento(bestMove);

                // Después de actualizar el estado del juego y la interfaz de usuario, verifica el estado del juego
                checkEstado();
            } else {
                // Maneja el caso en que bestMove es null
                System.out.println("No se encontró el mejor movimiento.");
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
