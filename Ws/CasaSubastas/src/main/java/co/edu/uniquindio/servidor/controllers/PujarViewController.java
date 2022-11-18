package co.edu.uniquindio.casasubastas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class PujarViewController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * Boton para pujar
     */

    @FXML
    private Button BotonPuja;
    /**
     *TextField de la puja
     */

    @FXML
    private TextField TextPuja;
    /**
     * Metodo para realizar una puja al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     * */
    @FXML
    void ActionPuja(ActionEvent event) {
        try {
            validarField();
            modelFactoryController.crearPuja(modelFactoryController.getProductoPujar().getNombre(),
                    Double.parseDouble(TextPuja.getText()));
            modelFactoryController.setProductoPujar(null);
            JOptionPane.showMessageDialog(null, "Puja creada");
            Stage stage = (Stage) BotonPuja.getScene().getWindow();
            stage.close();
        } catch (EmptyFieldsException | InsufficientBidException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error en el formato del valor");
        } catch (UserNotFoundException | ProductNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }catch (ProductsLimitException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            Stage stage = (Stage) BotonPuja.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Metodo para validar el campo que se va a pujar
     * @throws EmptyFieldsException Se valida si la puja esta vacia
     * @throws NumberFormatException Se valida el formato del numero
     */
    private void validarField() throws EmptyFieldsException, NumberFormatException {
        if(TextPuja.getText().equals("")){
            throw new EmptyFieldsException("El campo esta vacio");
        }
        Double.parseDouble(TextPuja.getText());
    }

    @FXML
    void initialize() {
        assert BotonPuja != null : "fx:id=\"BotonPuja\" was not injected: check your FXML file 'pujar_view.fxml'.";
        assert TextPuja != null : "fx:id=\"TextPuja\" was not injected: check your FXML file 'pujar_view.fxml'.";
        modelFactoryController = ModelFactoryController.getInstance();
    }

}

