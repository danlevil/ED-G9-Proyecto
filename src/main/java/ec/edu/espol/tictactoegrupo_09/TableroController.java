/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoegrupo_09;

import Estructuras.TreeNode;
import Modelo.TicTacToe;
import Modelo.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private String dificultadJuego;
    private String initialPlayer;
    @FXML
    private Label winnerLabel;
    List<Node> casillasDisponibles;
    @FXML
    private int filaSugerida;
    private int colSugerida;
    @FXML
    private ImageView PistaBtn;

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

    public void nuevoJuego() {
        System.out.println(gameMode);
        juego = new TicTacToe();

        for (Node nodo : casillasDisponibles) {
            if (nodo instanceof Button) {
                Button boton = (Button) nodo;
                boton.setText("");
                boton.setDisable(false);
                boton.setOnAction(e -> clickearBoton(boton));
            }
        }

        if (gameMode.equals("ComputadoraVsComputadora")) {
            currentPlayer = initialSymbol; 
            initialPlayer = "Computadora";

            while (!juego.isFull(juego.getCells()) && !juego.HayGanador(currentPlayer)) {
                if (currentPlayer == 'X') {
                    realizarMovimientoComputadoraDificil();
                    DibujarTableroEnConsola(juego.getCells());

                } else if (currentPlayer == 'O') {
                    realizarMovimientoComputadoraDificil();
                    DibujarTableroEnConsola(juego.getCells());

                }

            }

        } else if (gameMode.equals("JugadorVsComputadora")) {
            currentPlayer = initialSymbol; //Simbolo del jugador
            Image im = new Image("images/bulb2.jpg");
            PistaBtn.setImage(im);
            if (initialPlayer.equals("Computadora")) {
                if (dificultadJuego.equals("Facil")) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    realizarMovimientoAleatiriosComputadora();
                } else if (dificultadJuego.equals("Dificil")) {

                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    realizarMovimientoComputadoraDificil();
                }

            }
        } else if (gameMode.equals("JugadorVsJugador")) {
            currentPlayer = initialSymbol;
        }
    }

    static void DibujarTableroEnConsola(char[][] board) {
        Map<Character, String> symbols = Map.of('X', "X", 'O', "O", ' ', " ");
        for (char[] row : board) {
            for (char col : row) {
                String symbol = symbols.get(col);
                System.out.print("| " + symbol + " |");
            }
            System.out.println("\n---------------");

        }
        System.out.println("===============");
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setDificultadJuego(String dificultadJuego) {
        this.dificultadJuego = dificultadJuego;
    }

    public void setInitialPlayer(String intialPlayer) {
        this.initialPlayer = intialPlayer;
    }

    public void setinitialSymbol(char symbol) {
        this.initialSymbol = symbol;
    }

    public String getDificultadJuego() {
        return dificultadJuego;
    }

    public char getSymbolPlayer1() {
        return symbolPlayer1;
    }

    public void setSymbolPlayer1(char symbolPlayer1) {
        this.symbolPlayer1 = symbolPlayer1;
    }

    private void clickearBoton(Button boton) {
        if (gameMode.equals("JugadorVsJugador")) {
            int i = GridPane.getRowIndex(boton);
            int j = GridPane.getColumnIndex(boton);
            if (juego.getCells()[i][j] == ' ') {
                juego.setSymbol(juego.getCells(), i, j, currentPlayer);
                boton.setText(currentPlayer + "");
                boton.setDisable(true);
                checkEstado();

            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

        } else if (gameMode.equals("JugadorVsComputadora")) {
            if (dificultadJuego.equals("Facil")) {

                int i = GridPane.getRowIndex(boton);
                int j = GridPane.getColumnIndex(boton);
                if (juego.getCells()[i][j] == ' ') {
                    juego.setSymbol(juego.getCells(), i, j, currentPlayer);
                    boton.setText(currentPlayer + "");
                    boton.setDisable(true);
                    boton.setStyle("-fx-background-color: #d6d2d2;");
                    casillasDisponibles.remove(boton);
                    System.out.println(casillasDisponibles);
                    checkEstado();

                }

                if (!juego.HayGanador(currentPlayer)) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    realizarMovimientoAleatiriosComputadora();
                }

            } else if (dificultadJuego.equals("Dificil")) {
                Image im = new Image("images/bulb2.jpg");
                PistaBtn.setImage(im);

                int i = GridPane.getRowIndex(boton);
                int j = GridPane.getColumnIndex(boton);
                if (juego.getCells()[i][j] == ' ') {
                    juego.setSymbol(juego.getCells(), i, j, currentPlayer);
                    boton.setText(currentPlayer + "");
                    boton.setDisable(true);
                    boton.setStyle("-fx-background-color: #d6d2d2;");
                    casillasDisponibles.remove(boton);
                    System.out.println(casillasDisponibles);
                    checkEstado();

                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                realizarMovimientoComputadoraDificil();

            }

        } else if (gameMode.equals("ComputadoraVsComputadora")) {

        }
    }

    private void realizarMovimientoAleatiriosComputadora() {

        int filaComputadora, columnaComputadora;
        if (!casillasDisponibles.isEmpty()) {
            Button botonPC = (Button) casillasDisponibles.get(obtenerIndiceAleatorio(casillasDisponibles.size()));
            filaComputadora = GridPane.getRowIndex(botonPC);
            columnaComputadora = GridPane.getColumnIndex(botonPC);

            botonPC.setText(currentPlayer + "");
            botonPC.setDisable(true);
            casillasDisponibles.remove(botonPC);

            juego.setSymbol(juego.getCells(), filaComputadora, columnaComputadora, currentPlayer);

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

    public void realizarMovimientoComputadoraDificil() {

        if (gameMode.equals("ComputadoraVsComputadora")) {
            TreeNode root = new TreeNode(null, 0);
            if (currentPlayer == 'X') {
                int[] move = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, true, root);
                mover(move);

            } else if (currentPlayer == 'O') {
                int[] move = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, false, root);
                mover(move);
            }
        } else {

            int[] move;
            if (symbolPlayer1 == 'X') {
                
                TreeNode root = new TreeNode(null, 0);
                
                move = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, false, root);

            } else {
                
                TreeNode root = new TreeNode(null, 0);

                move = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, true, root);

            }

            mover(move);

        }

    }

    private void mover(int[] move) {
        Button botonPC = null;

        if (juego.isEmpty(juego.getCells())) {

            juego.setSymbol(juego.getCells(), 1, 1, currentPlayer);
            botonPC = (Button) casillasDisponibles.get(4);

            botonPC.setText(currentPlayer + "");
            botonPC.setDisable(true);
            casillasDisponibles.remove(botonPC);
            checkEstado();
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        } else {
            juego.setSymbol(juego.getCells(), move[0], move[1], currentPlayer);

            for (Node nodo : casillasDisponibles) {
                if (nodo instanceof Button) {
                    int i = GridPane.getRowIndex(nodo);
                    int j = GridPane.getColumnIndex(nodo);
                    if (i == move[0] && j == move[1]) {
                        botonPC = (Button) nodo;
                        break;
                    }
                }
            }

            if (botonPC != null) {
                botonPC.setText(currentPlayer + "");
                botonPC.setDisable(true);
                casillasDisponibles.remove(botonPC);
            } else {

                System.out.println(" ");
            }

            checkEstado();
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

        }

    }

    private void quitarLoAmarillo() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getStyle().equals("-fx-background-color: yellow;")) {
                    button.setStyle("-fx-background-color: #d6d2d2;");
                }

            }

        }
    }

    private void checkEstado() {
        if (juego.HayGanador(currentPlayer)) {
            desactivarBotones();
            resaltarGanador();

            winnerLabel.setText("EL GANADOR ES: " + currentPlayer);
            winnerLabel.setAlignment(Pos.CENTER);
            Utility.styleLabel(winnerLabel, 30, true);
            winnerLabel.setTextFill(Color.BLACK);
        } else if (juego.isFull(juego.getCells()) && !juego.HayGanador(currentPlayer)) {
            winnerLabel.setText("EMPATE");
            winnerLabel.setAlignment(Pos.CENTER);
            Utility.styleLabel(winnerLabel, 30, true);
            winnerLabel.setTextFill(Color.BLACK);
        }
        quitarLoAmarillo();
    }

    private void regresarAmenu(MouseEvent event) throws IOException {
        if (gameMode.equals("JugadorVsComputadora")) {

        } else if (gameMode.equals("JugadorVsJugador")) {

        }
    }

    private void resaltarGanador() {
        char[][] tablero = juego.getCells();
        for (int i = 0; i < 3; i++) {
            if (revisarLinea(tablero, i, 0, i, 1, i, 2) || revisarLinea(tablero, 0, i, 1, i, 2, i)) {
                return;
            }
        }
        if (revisarLinea(tablero, 0, 0, 1, 1, 2, 2) || revisarLinea(tablero, 0, 2, 1, 1, 2, 0)) {
            return;
        }
    }

    private boolean revisarLinea(char[][] tablero, int row1, int col1, int row2, int col2, int row3, int col3) {
        if (tablero[row1][col1] != '\0' && tablero[row1][col1] == tablero[row2][col2] && tablero[row2][col2] == tablero[row3][col3]) {
            resaltarBotones(row1, col1, row2, col2, row3, col3);
            return true;
        }
        return false;
    }

    private void resaltarBotones(int row1, int col1, int row2, int col2, int row3, int col3) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setDisable(false);
                button.setStyle("-fx-background-color: #d6d2d2;");
                button.setOnAction(event -> {
                });
                int row = GridPane.getRowIndex(button);
                int col = GridPane.getColumnIndex(button);
                if ((row == row1 && col == col1) || (row == row2 && col == col2) || (row == row3 && col == col3)) {
                    button.setStyle("-fx-background-color: red;");
                }
            }
        }
    }

    @FXML
    private void generarPista(MouseEvent event) {
        int suggestedRow;
        int suggestedCol;
        int[] posicionesVacias;

        if (symbolPlayer1 == 'X') {
            if (juego.isEmpty(juego.getCells())) {
                posicionesVacias = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                suggestedRow = 1;
                suggestedCol = 1;
            } else {
                posicionesVacias = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                suggestedRow = posicionesVacias[0];
                suggestedCol = posicionesVacias[1];

            }

        } else {
            if (juego.isEmpty(juego.getCells())) {
                posicionesVacias = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                suggestedRow = 1;
                suggestedCol = 1;
            } else {
                posicionesVacias = juego.abminimax(9, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                suggestedRow = posicionesVacias[0];
                suggestedCol = posicionesVacias[1];

            }

        }
        Button suggestedButton = encontrarBotonPorID("button" + suggestedRow + suggestedCol);

        if (juego.isEmpty(juego.getCells())) {
            if (suggestedButton != null) {

                suggestedButton.setStyle("-fx-background-color: yellow;");
            }

            System.out.println("pedi una pista");
        } else {
            for (Node nodo : casillasDisponibles) {
                if (nodo instanceof Button) {
                    int i = GridPane.getRowIndex(nodo);
                    int j = GridPane.getColumnIndex(nodo);
                    if (i == posicionesVacias[0] && j == posicionesVacias[1]) {
                        suggestedButton = (Button) nodo;
                        if (suggestedButton.isDisabled()) {
                            suggestedButton.setStyle("-fx-background-color: #d6d2d2;");
                        }
                        break;
                    }
                }
            }

            if (suggestedButton != null) {

                suggestedButton.setStyle("-fx-background-color: yellow;");

            }

            System.out.println("pedi una pista");

        }

    }

    private Button encontrarBotonPorID(String id) {
        for (int i = 0; i < gridPane.getChildren().size(); i++) {
            if (gridPane.getChildren().get(i) instanceof Button) {
                Button button = (Button) gridPane.getChildren().get(i);
                if (button.getId().equals(id)) {
                    return button;
                }
            }
        }
        return null;
    }
}
