/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danlevil
 */
public class TableroC<V>{
    
    private HashMap<String,V> tablero;
    private HashMap<String,V> disponibles;

    public TableroC() {
        this.tablero=new HashMap<>();
        inicializarPredefinido(this.tablero);
        this.disponibles=new HashMap<>(this.tablero);
    }
    
    private void inicializarPredefinido(Map<String,V> tablero){
        tablero.put((String) "00", (V) null);
        tablero.put((String) "01", (V) null);
        tablero.put((String) "02", (V) null);
        tablero.put((String) "10", (V) null);
        tablero.put((String) "11", (V) null);
        tablero.put((String) "12", (V) null);
        tablero.put((String) "20", (V) null);
        tablero.put((String) "21", (V) null);
        tablero.put((String) "22", (V) null);
    }
    public V obtenerElemento(String clave) {
        return tablero.get(clave);
    }
    public void imprimirElementos() {
        for (Map.Entry<String, V> entry : tablero.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }
    }
    public void modificarValor(String clave, V nuevoValor) {
        if (tablero.containsKey(clave)) {
            tablero.put(clave, nuevoValor);
            disponibles.remove(clave);
            System.out.println(disponibles.keySet());
        } else {
            throw new IllegalArgumentException("La clave no existe en el HashMap");
        }
    }
    public void modificarValorProbable(String clave, V nuevoValor) {
        if (tablero.containsKey(clave)) {
            tablero.put(clave, nuevoValor);
            
        } else {
            throw new IllegalArgumentException("La clave no existe en el HashMap");
        }
    }
    public  List<TableroC<V>> generadorProbabilidades(V current){
        List<String> listaDeClaves = new ArrayList<>(disponibles.keySet());
        List<TableroC<V>>posibilidades = new ArrayList<>();
        System.out.println(listaDeClaves);
        System.out.println(this);
        for(int i=0;i<listaDeClaves.size();i++){
            TableroC<V> copyBoard= new TableroC<V>();
            copyBoard.tablero=new HashMap<>(this.tablero);
            copyBoard.modificarValorProbable(listaDeClaves.get(i), current);
            posibilidades.add(copyBoard);
            
        }
        return posibilidades;

        
    }
}
