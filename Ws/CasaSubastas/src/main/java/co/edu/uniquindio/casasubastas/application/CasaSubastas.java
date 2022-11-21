package co.edu.uniquindio.casasubastas.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class CasaSubastas extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/login-view.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 283, 321);
        stage.setTitle("LogIn");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
}