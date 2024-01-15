/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author danlevil
 */
public class CalculadoraDeUtilidad {
    
    String symbolComputador;
    String symbolJugador;
    TableroC tablero;
    
    
    
    public void calcularUtilidad(TableroC tablero, char computerSymbol){
        this.tablero=tablero;
        symbolComputador=String.valueOf(computerSymbol).toUpperCase();
        symbolJugador= (computerSymbol=='X')?"O":"X";
        
        int utilidad= calcularLineasComputador()-calcularLineasJugador();
        
        tablero.setUtilidad(utilidad);
        //System.out.println(utilidad);
    }
    
    
    
    private int calcularLineasComputador(){
        
      return contadorFilas(symbolComputador)+contadorColumnas(symbolComputador)
              +contadorDiagonales(symbolComputador);
    
    }
            
            
    private int calcularLineasJugador(){
        return contadorFilas(symbolJugador)+contadorColumnas(symbolJugador)
              +contadorDiagonales(symbolJugador);
    
    }
    
    private int contadorFilas(String simbolo){
        //int filas=0;
        boolean f1=
                tablero.obtenerElemento("00")==null||(tablero.obtenerElemento("00").equals(simbolo))&&
                (tablero.obtenerElemento("01")==null||tablero.obtenerElemento("01").equals(simbolo))&&
                (tablero.obtenerElemento("02")==null||tablero.obtenerElemento("02").equals(simbolo));
        
        boolean f2=
                (tablero.obtenerElemento("10")==null||tablero.obtenerElemento("10").equals(simbolo))&&
                (tablero.obtenerElemento("11")==null||tablero.obtenerElemento("11").equals(simbolo))&&
                (tablero.obtenerElemento("12")==null||tablero.obtenerElemento("12").equals(simbolo));
        
        boolean f3=
                (tablero.obtenerElemento("20")==null||tablero.obtenerElemento("20").equals(simbolo))&&
                (tablero.obtenerElemento("21")==null||tablero.obtenerElemento("21").equals(simbolo))&&
                (tablero.obtenerElemento("22")==null||tablero.obtenerElemento("22").equals(simbolo));
        
        return ((f1)?1:0 )+ ((f2)?1:0) + ((f3)?1:0);
    }
    private int contadorColumnas(String simbolo){
        //int filas=0;
        boolean c1=
                (tablero.obtenerElemento("00")==null||tablero.obtenerElemento("00").equals(simbolo))&&
                (tablero.obtenerElemento("10")==null||tablero.obtenerElemento("10").equals(simbolo))&&
                (tablero.obtenerElemento("20")==null||tablero.obtenerElemento("20").equals(simbolo));
        
        boolean c2=
                (tablero.obtenerElemento("01")==null||tablero.obtenerElemento("01").equals(simbolo))&&
                (tablero.obtenerElemento("11")==null||tablero.obtenerElemento("11").equals(simbolo))&&
                (tablero.obtenerElemento("21")==null||tablero.obtenerElemento("21").equals(simbolo));
        
        boolean c3=
                (tablero.obtenerElemento("02")==null||tablero.obtenerElemento("02").equals(simbolo))&&
                (tablero.obtenerElemento("12")==null||tablero.obtenerElemento("12").equals(simbolo))&&
                (tablero.obtenerElemento("22")==null||tablero.obtenerElemento("22").equals(simbolo));
        
        return ((c1)?1:0 )+ ((c2)?1:0) + ((c3)?1:0);
    }
    
    private int contadorDiagonales(String simbolo){
        //int filas=0;
        boolean diagonal=
                (tablero.obtenerElemento("00")==null||tablero.obtenerElemento("00").equals(simbolo))&&
                (tablero.obtenerElemento("11")==null||tablero.obtenerElemento("11").equals(simbolo))&&
                (tablero.obtenerElemento("22")==null||tablero.obtenerElemento("22").equals(simbolo));
        
        boolean antiDiagonal=
                (tablero.obtenerElemento("02")==null||tablero.obtenerElemento("02").equals(simbolo))&&
                (tablero.obtenerElemento("11")==null||tablero.obtenerElemento("11").equals(simbolo))&&
                (tablero.obtenerElemento("20")==null||tablero.obtenerElemento("20").equals(simbolo));
                
        return ((diagonal)?1:0 )+ ((antiDiagonal)?1:0) ;
    }
}
