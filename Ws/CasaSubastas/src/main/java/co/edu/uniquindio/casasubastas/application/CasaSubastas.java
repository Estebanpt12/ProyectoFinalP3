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
    private Stage primaryStage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Casa Subasta");
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplication.class.getResource("../views/login-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            //
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}