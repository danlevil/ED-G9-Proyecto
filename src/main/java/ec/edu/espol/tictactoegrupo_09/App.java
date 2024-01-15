package ec.edu.espol.tictactoegrupo_09;

import Modelo.Bloque;
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
        miTablero.modificarValor("00", urlOfire);
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
        miTablero2.imprimirElementos();

    }

}
