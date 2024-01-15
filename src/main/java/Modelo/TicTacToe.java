/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Estructuras.Tree;
import Estructuras.TreeNode;
import java.io.Serializable;

/**
 *
 * @author johan
 */
public class TicTacToe implements Serializable {

    private static final long serialVersionUID = 1L;
    private char[][] cells;
    private char symbolPlayer;

    public TicTacToe() {
        this.cells = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = ' ';
            }
        }
    }

    public enum Linea {
        ROW, COLUMN, DIAGONAL, ANTIDIAGONAL
    }

    public char[][] getCells() {
        return cells;
    }

    public void setCells(char[][] cells) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = cells[i][j];
            }
        }
    }

    public char getSymbolPlayer() {
        return symbolPlayer;
    }

    public void setSymbolPlayer(char symbolPlayer) {
        this.symbolPlayer = symbolPlayer;
    }

    public void setSymbol(int i, int j, char symbolPlayer) {
        if (cells[i][j] == ' ') {
            this.cells[i][j] = symbolPlayer;

        }
    }

    private boolean esFilaGanadora(int fila, char symbol) {
        for (int columna = 0; columna < 3; columna++) {
            if (cells[fila][columna] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean esColumnaGanadora(int columna, char symbol) {
        for (int fila = 0; fila < 3; fila++) {
            if (cells[fila][columna] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean esDiagonalGanadora(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][i] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean esAntiDiagonalGanadora(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][2 - i] != symbol) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  0 1 2\n"); // Encabezados de columna
        for (int i = 0; i < cells.length; i++) {
            sb.append(i); // Número de fila
            for (int j = 0; j < cells[i].length; j++) {
                sb.append(" ").append(cells[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //---------------------------------------------------------------//
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.cells[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean HayGanador(char symbol) {

        if (esDiagonalGanadora(symbol) || esAntiDiagonalGanadora(symbol)) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            if (esFilaGanadora(i, symbol) || esColumnaGanadora(i, symbol)) {
                return true;
            }
        }

        return false;
    }

    /* Método que cuenta las líneas que son potencialmente ganadoras en un tablero */
    public boolean potentialWin(int index, Linea type, char symbol) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            char cell;
            switch (type) {
                case ROW:
                    cell = cells[index][i];
                    break;
                case COLUMN:
                    cell = cells[i][index];
                    break;
                case DIAGONAL:
                    cell = cells[i][i];
                    break;
                case ANTIDIAGONAL:
                    cell = cells[i][2 - i];
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (cell == symbol || cell == ' ') {
                count++;
            }
        }
        return count == 3;
    }

    public int utility(char currentPlayer) {
        int Pjugador = countPotentialWins(currentPlayer);
        char oponente = (currentPlayer == 'X') ? 'O' : 'X';
        int Poponente = countPotentialWins(oponente);
        return Pjugador - Poponente;
    }

    private int countPotentialWins(char symbol) {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            if (potentialWin(i, Linea.ROW, symbol)) {
                count++;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (potentialWin(j, Linea.COLUMN, symbol)) {
                count++;
            }
        }

        if (potentialWin(0, Linea.DIAGONAL, symbol)) {
            count++;
        }
        if (potentialWin(0, Linea.ANTIDIAGONAL, symbol)) {
            count++;
        }
        return count;
    }

        public void minimax(TreeNode<TicTacToe> node, int depth, boolean isMaximizingPlayer, char maximizer) {
            TicTacToe board = node.getContent();
            char winner = board.checkWinner(); 
            if (depth == 0 || winner != '\0') {
                node.setUtility(board.score(winner, maximizer)); 
                return;
            }

            if (isMaximizingPlayer) {
                int maxEval = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.getCells()[i][j] == ' ') {
                            board.setSymbol(i, j, maximizer); // Usa el símbolo del maximizador
                            TicTacToe childBoard = new TicTacToe();
                            childBoard.setCells(board.getCells()); // Clona el estado del tablero
                            TreeNode<TicTacToe> childNode = new TreeNode<>(childBoard);
                            node.getChildren().add(new Tree<>(childNode));
                            minimax(childNode, depth - 1, false, maximizer);
                            board.setSymbol(i, j, ' '); // Deshace el movimiento
                            maxEval = Math.max(maxEval, childNode.getUtility());
                            System.out.println(maxEval);
                        }
                    }
                }
                node.setUtility(maxEval);
            } else {
                char minimizer = (maximizer == 'X') ? 'O' : 'X'; // Determina el símbolo del minimizador
                int minEval = Integer.MAX_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.getCells()[i][j] == ' ') {
                            board.setSymbol(i, j, minimizer); // Usa el símbolo del minimizador
                            TicTacToe childBoard = new TicTacToe();
                            childBoard.setCells(board.getCells()); // Clona el estado del tablero
                            TreeNode<TicTacToe> childNode = new TreeNode<>(childBoard);
                            node.getChildren().add(new Tree<>(childNode));
                            minimax(childNode, depth - 1, true, maximizer);
                            board.setSymbol(i, j, ' '); // Deshace el movimiento
                            minEval = Math.min(minEval, childNode.getUtility());
                        }
                    }
                }
                node.setUtility(minEval);
            }
        }

    public int score(char winner, char maximizer) {
        if (winner == maximizer) { // Si el ganador es el jugador maximizador
            return 10;
        } else if (winner != '\0') { // Si hay un ganador y no es el maximizador
            return -10;
        } else {
            return 0; // Devuelve 0 si no hay ganador
        }
    }

    public char checkWinner() {
        if (HayGanador('X')) {
            return 'X';
        } else if (HayGanador('O')) {
            return 'O';
        } else {
            return '\0'; // Devuelve un carácter nulo si no hay ganador
        }
    }

}
