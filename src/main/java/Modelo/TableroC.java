/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danlevil
 */
public class TableroC<V>{
    
    private HashMap<String,V> tablero= new HashMap();

    public TableroC() {
        this.tablero=new HashMap<>();
        inicializarPredefinido(this.tablero);
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
            // Solo permitir la modificación si la clave existe
            tablero.put(clave, nuevoValor);
        } else {
            throw new IllegalArgumentException("La clave no existe en el HashMap");
        }
    }
}
