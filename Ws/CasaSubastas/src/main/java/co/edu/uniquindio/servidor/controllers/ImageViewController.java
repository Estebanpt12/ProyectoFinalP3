package co.edu.uniquindio.casasubastas.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    /**
     * ImageView para visualizar la imagen
     */
    @FXML
    private ImageView Image;

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        assert Image != null : "fx:id=\"Image\" was not injected: check your FXML file 'imagen_view.fxml'.";
        loadImage();
    }
    /**
     *Metodo para cargar la imagen
     */
    private void loadImage(){
        File file = new File(modelFactoryController.getRutaImageView());
        Image image = new Image(file.toURI().toString());
        Image.setImage(image);
        modelFactoryController.setRutaImageView("");
    }

}
