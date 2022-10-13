package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.UserNotLoggedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

public class gui3Controller {

    /**
     * Botón para crear Anuncio
     * */
    @FXML
    private Button btnCrearAnuncio;
    /**
     * DatePicker para escoger la fecha en la que el anuncio termina
     * */
    @FXML
    private DatePicker dpFechaFin;
    /**
     * Tipo de anuncio correspondiente a bien raíz
     * */
    @FXML
    private MenuItem miBienRaiz;
    /**
     * Tipo de anuncio correspondiente a Deportes
     */
    @FXML
    private MenuItem miDeportes;
    /**
     *Tipo de anuncio correspondiente a Hogar
     */
    @FXML
    private MenuItem miHogar;
    /**
     *Tipo de anuncio correspondiente a Tecnología
     */
    @FXML
    private MenuItem miTecnologia;
    /**
     *Tipo de anuncio correspondiente a Vehiculos
     */
    @FXML
    private MenuItem miVehiculos;
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
    private TextArea txtArDescripción;
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
     *Método para crear un anuncio al oprimir el botón
     *@param event Evento de pulsar el botón
     *@throws UserNotLoggedException Excepción al intentar crear un anuncio sin haber hecho el login
     */
    @FXML
    void crearAnuncio(ActionEvent event) {
        try {
            Singleton.crearAnuncio();
        }catch(UserNotLoggedException ue) {
            throw ue;
        }catch(EmptyFieldsException ef){
            JOptionPane.showMessageDialog(null,"Hay campos vacíos");
        }
    }
    }

}