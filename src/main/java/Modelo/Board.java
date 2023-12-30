/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author johan
 */
public class Board {

    private char[][] cells;
    private char symbolPlayer;

    public Board() {
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

}
