package co.edu.uniquindio.casasubastas.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        mostrarVentana("login-view.fxml");
    }

    /**
     * Metodo para abrir una vista
     * @param view nombre de la vista
     */
    public void mostrarVentana(String view){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CasaSubastas.class.getResource("/co/edu/uniquindio/casasubastas/views/" + view));
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