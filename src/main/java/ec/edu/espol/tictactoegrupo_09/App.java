package ec.edu.espol.tictactoegrupo_09;

import Modelo.Bloque;
import Modelo.CalculadoraDeUtilidad;
import Modelo.GeneradorProbabilidad;
import Modelo.TableroC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Button[] buttons = new Button[9];
    private char currentPlayer = 'X';
    public static String urlOfire="images/oFire.png";
    public static String urlXfire="images/xFire.png";

    @Override
    public void start(Stage stage) throws IOException {
        
        scene = new Scene(loadFXML("Menu"));
        stage.setTitle("Tres en Raya");
        Image icono = new Image("images/tictactoe.png");
        stage.getIcons().add(icono);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        TableroC<String> miTablero= new TableroC<>();
        miTablero.imprimirElementos();
        System.out.println("primera modificacion");
        miTablero.modificarValor("11", String.valueOf('X'));
        
        System.out.println("Imprimir posibilidades");
        
        int n=1;
        GeneradorProbabilidad g1= new GeneradorProbabilidad();
        CalculadoraDeUtilidad c1= new CalculadoraDeUtilidad();
        System.out.println(g1.generarProbablesTablero(miTablero,'O').size());
        for(TableroC<String> t: g1.generarProbablesTablero(miTablero,'O')){
            System.out.println("iteracion n: "+n);
            c1.calcularUtilidad(t, 'O');
            t.imprimirElementos();
            System.out.println("utilidad : "+t.getUtilidad());
            n++;
        }
    }

}





/*
        miTablero.modificarValor("02", String.valueOf('X'));
        miTablero.imprimirElementos();
        System.out.println(miTablero.obtenerElemento("00"));
        
        TableroC<Bloque> miTablero2= new TableroC<>();
        Bloque<Integer> b1= new Bloque<>();
        b1.setCol(0);
        b1.setFila(2);
        b1.setContenido(12);
        miTablero2.modificarValor("02", b1);
        System.out.println(miTablero2.obtenerElemento("02"));
        System.out.println("");
        miTablero2.imprimirElementos();*/