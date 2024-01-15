/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author danlevil
 */
public class GeneradorProbabilidad {
    
    public List<TableroC> generarProbablesTablero(TableroC tablero, char symbol){
        String currentSymbol= String.valueOf(symbol);
        List<TableroC> probables= new ArrayList<>();
        List<String> listaDeClaves = new ArrayList<>(tablero.getDisponibles().keySet());
        System.out.println(listaDeClaves);
        System.out.println(this);
        for(int i=0;i<listaDeClaves.size();i++){
            TableroC<String> copyBoard= new TableroC<String>();
            copyBoard.setTablero(new LinkedHashMap<>(tablero.getTablero()));
            copyBoard.modificarValorProbable(listaDeClaves.get(i), currentSymbol);
            probables.add(copyBoard);
            
        }
        return probables;
    }
    public List<TableroC> generarProbablesTablero(TableroC tablero, String currentSymbol){
        List<TableroC> probables= new ArrayList<>();
        List<String> listaDeClaves = new ArrayList<>(tablero.getDisponibles().keySet());
        System.out.println(listaDeClaves);
        System.out.println(this);
        for(int i=0;i<listaDeClaves.size();i++){
            TableroC<String> copyBoard= new TableroC<String>();
            copyBoard.setTablero(new LinkedHashMap<>(tablero.getTablero()));
            copyBoard.modificarValorProbable(listaDeClaves.get(i), currentSymbol);
            probables.add(copyBoard);
            
        }
        return probables;
    }
}
