package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.Producto;
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
public class EditarAnuncioController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * Botón para crear Anuncio
     * */
    @FXML
    private Button btnEditarAnuncio;
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
     *Método para editar un anuncio al oprimir el botón
     *@param event Evento de pulsar el botón
     */
    @FXML
    void editarAnuncio(ActionEvent event) {
        try {
            validarFields();
            modelFactoryController.editarProducto(comboTipoProducto.getSelectionModel().getSelectedItem(),
                    txtNombreProducto.getText(), txtArDescripcion.getText(), txtRutaImagen.getText(),
                    Double.parseDouble(valorInicialText.getText()));
            Stage stage = (Stage) btnEditarAnuncio.getScene().getWindow();
            stage.close();
        } catch (EmptyFieldsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error en el formato del valor incial");
        } catch (ProductNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "editarAnuncio");
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
        assert btnEditarAnuncio != null : "fx:id=\"btnCrearAnuncio\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert comboTipoProducto != null : "fx:id=\"comboTipoProducto\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert txtArDescripcion != null : "fx:id=\"txtArDescripcion\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert txtNombreProducto != null : "fx:id=\"txtNombreProducto\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert txtRutaImagen != null : "fx:id=\"txtRutaImagen\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        assert valorInicialText != null : "fx:id=\"valorInicialText\" was not injected: check your FXML file 'crear_anuncio_view.fxml'.";
        initializeCombo();
        modelFactoryController = ModelFactoryController.getInstance();
        initializeFields();
    }

    /**
     * Metodo para incializar fields segun el producto que se vaya a editar
     */
    private void initializeFields(){
        Producto producto = modelFactoryController.getProductoEditar();
        txtNombreProducto.setText(producto.getNombre());
        txtNombreProducto.setEditable(false);
        comboTipoProducto.getSelectionModel().select(producto.getTipoProducto());
        comboTipoProducto.setDisable(true);
        valorInicialText.setText(String.valueOf(producto.getValorInicial()));
        txtRutaImagen.setText(producto.getRutaFoto());
        txtArDescripcion.setText(producto.getDescripcion());
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
