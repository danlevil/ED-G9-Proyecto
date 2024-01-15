/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author danlevil
 */
public class Bloque<E> {
    private int fila;
    private int col;
    private E contenido;

    public Bloque(int fila, int col, E contenido) {
        this.fila = fila;
        this.col = col;
        this.contenido = contenido;
    }
    
    public Bloque(){
        this.fila=-1;
        this.col=-1;
        this.contenido=null;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }
    
    @Override
    public String toString(){
        return "{fila: "+this.fila+";Col: "+this.col+"; content: "+getContenido().toString()+'}';
    }
    
}
