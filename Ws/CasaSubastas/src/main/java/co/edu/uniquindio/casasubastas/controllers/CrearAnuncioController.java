package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.ProductsLimitException;
import co.edu.uniquindio.casasubastas.exceptions.UserNotFoundException;
import co.edu.uniquindio.casasubastas.exceptions.UserNotLoggedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de la view para crear anuncios
 */
public class CrearAnuncioController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * Botón para crear Anuncio
     * */
    @FXML
    private Button btnCrearAnuncio;
    /**
     *Menú desplegable con los tipos de anuncios
     */
    @FXML
    private MenuButton mnbTipoProducto;
    /**
     *Label del titulo
     */
    @FXML
    private Label titleLabel;
    /**
     * Área de texto para la descripción del producto
     * */
    @FXML
    private TextArea txtArDescripcion;
    /**
     * TextField para el nombre del producto
     */
    @FXML
    private TextField txtNombreProducto;
    /**
     *TextField para la ruta de la imagen
     */
    @FXML
    private TextField txtRutaImagen;

    /**
     * combo de tipo producto
     */
    @FXML
    private ComboBox<String> comboTipoProducto;


    @FXML
    private TextField valorInicialText;

    /**
     *Método para crear un anuncio al oprimir el botón
     *@param event Evento de pulsar el botón
     *@throws UserNotLoggedException Excepción al intentar crear un anuncio sin haber hecho el login
     */
    @FXML
    void crearAnuncio(ActionEvent event) {
        try {
            validarFields();
            modelFactoryController.crearProducto(comboTipoProducto.getSelectionModel().getSelectedItem(),
                    txtNombreProducto.getText(), txtArDescripcion.getText(), txtRutaImagen.getText(),
                    Double.parseDouble(valorInicialText.getText()));
            Stage stage = (Stage) btnCrearAnuncio.getScene().getWindow();
            stage.close();
        } catch (EmptyFieldsException | ProductsLimitException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UserNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "crearAnuncio");
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error en el formato del valor incial");
        }
    }

    /**
     * Metodo para validar si los fields de la interfaz de usuario estan vacios y para validar el formato del valor inicial
     * @throws EmptyFieldsException excepcion si algun campo esta vacio
     */
    private void validarFields() throws EmptyFieldsException, NumberFormatException{
        if(txtNombreProducto.getText().equals("") || comboTipoProducto.getSelectionModel().getSelectedIndex() == 0 ||
            valorInicialText.getText().equals("") || txtRutaImagen.getText().equals("") || txtArDescripcion.getText().equals("")){
            throw new EmptyFieldsException("Algun campo esta vacio");
        }
        Double.parseDouble(valorInicialText.getText());
    }

    @FXML
    void initialize() {
        assert btnCrearAnuncio != null : "fx:id=\"btnCrearAnuncio\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert comboTipoProducto != null : "fx:id=\"comboTipoProducto\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert txtArDescripcion != null : "fx:id=\"txtArDescripcion\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert txtNombreProducto != null : "fx:id=\"txtNombreProducto\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert txtRutaImagen != null : "fx:id=\"txtRutaImagen\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert valorInicialText != null : "fx:id=\"valorInicialText\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        initializeCombo();
        modelFactoryController = ModelFactoryController.getInstance();
    }

    /**
     * Metodo para inicializar las opciones del tipo producto
     */
    private void initializeCombo(){
        comboTipoProducto.getItems().add("Tipo producto");
        comboTipoProducto.getItems().add("Tecnologia");
        comboTipoProducto.getItems().add("Hogar");
        comboTipoProducto.getItems().add("Deportes");
        comboTipoProducto.getItems().add("Vehiculos");
        comboTipoProducto.getItems().add("Bien raiz");
        comboTipoProducto.getSelectionModel().selectFirst();
    }

}