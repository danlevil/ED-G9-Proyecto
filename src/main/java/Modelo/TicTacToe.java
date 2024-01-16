/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johan
 */
public class TicTacToe implements Serializable{
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

    
    public char[][] getCells() {
        return cells;
    }

    public void setCells(char[][] cells) {
        this.cells = cells;
    }

    public char getSymbolPlayer() {
        return symbolPlayer;
    }

    public void setSymbolPlayer(char symbolPlayer) {
        this.symbolPlayer = symbolPlayer;
    }

    public void setSymbol(char[][] tablero, int x, int y, char symbolPlayer) {
        if (x >= 0 && x < tablero.length && y >= 0 && y < tablero[x].length) {
            tablero[x][y] = symbolPlayer;
        } else {
            // Manejar el caso en el que x o y están fuera de los límites
            System.out.println(" ");
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

    //---------------------------------------------------------------//
//    public boolean isFull() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (this.cells[i][j] == ' ') {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    
     public boolean isFull(char[][] board) {
        return posicionesVacias(board).size() == 0;
    }
     

    // posiblemente se puede usar pero en el TebleroController ya se está usando una lista de nodos en este caso los nodos son los nodos del gridpanel
    static List<int[]> posicionesVacias(char[][] TableroDeJuego) {
        List<int[]> posicionesVacias = new ArrayList<>();
        for (int x = 0; x < TableroDeJuego.length; x++) {
            for (int y = 0; y < TableroDeJuego[x].length; y++) {
                if (TableroDeJuego[x][y] == ' ') {
                    posicionesVacias.add(new int[]{x, y});
                }
            }
        }
        return posicionesVacias;
    }
    
    
    
    public int[] abminimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
        List<int[]> emptyCells = posicionesVacias(cells);

        if (depth == 0 || HayGanador('X') || HayGanador('O') || emptyCells.isEmpty()) {
            int score = getScore();
            return new int[]{-1, -1, score};
        }

        int[] bestMove = new int[]{-1, -1, maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE};

        for (int[] cell : emptyCells) {
            int x = cell[0];
            int y = cell[1];

            if (maximizingPlayer) {
                cells[x][y] = 'X';
                int[] currentMove = abminimax(depth - 1, alpha, beta, false);
                cells[x][y] = ' ';  // Deshacer el movimiento

                if (currentMove[2] > bestMove[2]) {
                    bestMove[0] = x;
                    bestMove[1] = y;
                    bestMove[2] = currentMove[2];
                }

                alpha = Math.max(alpha, bestMove[2]);
                if (beta <= alpha) {
                    break;  // Poda alfa-beta
                }
            } else {
                cells[x][y] = 'O';
                int[] currentMove = abminimax(depth - 1, alpha, beta, true);
                cells[x][y] = ' ';  // Deshacer el movimiento

                if (currentMove[2] < bestMove[2]) {
                    bestMove[0] = x;
                    bestMove[1] = y;
                    bestMove[2] = currentMove[2];
                }

                beta = Math.min(beta, bestMove[2]);
                if (beta <= alpha) {
                    break;  // Poda alfa-beta
                }
            }
        }

        return bestMove;
    }

    // Método de ayuda para obtener la puntuación del estado actual del tablero
    private int getScore() {
        if (HayGanador('X')) {
            return 10;
        } else if (HayGanador('O')) {
            return -10;
        } else {
            return 0;
        }
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

}




