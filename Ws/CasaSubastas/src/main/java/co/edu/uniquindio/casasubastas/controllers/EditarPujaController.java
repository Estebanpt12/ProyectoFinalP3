package co.edu.uniquindio.casasubastas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.exceptions.BidNotFoundException;
import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.InsufficientBidException;
import co.edu.uniquindio.casasubastas.exceptions.ProductNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class EditarPujaController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BotonEditar;

    @FXML
    private TextField TextPuja;

    @FXML
    void ActionEditar(ActionEvent event) {
        try {
            validarField();
            modelFactoryController.editarPuja(modelFactoryController.productoBuscadoEditarPuja, modelFactoryController.getPujaEditar(),
                    Double.parseDouble(TextPuja.getText()));
            Stage stage = (Stage) BotonEditar.getScene().getWindow();
            stage.close();
            JOptionPane.showMessageDialog(null, "La puja ha sido editada");
        } catch (EmptyFieldsException | InsufficientBidException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El formato del valor es incorrecto");
        } catch (BidNotFoundException | ProductNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }
    }

    /**
     * Metodo para validar si el producto a buscar esta vacio
     * @throws EmptyFieldsException Se valida si el cmapo esta vacio
     */
    private void validarField() throws EmptyFieldsException, NumberFormatException {
        if(TextPuja.getText().equals("")){
            throw new EmptyFieldsException("El campo esta vacio");
        }
        Double.parseDouble(TextPuja.getText());
    }

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        assert BotonEditar != null : "fx:id=\"BotonEditar\" was not injected: check your FXML file 'editar_puja_view.fxml'.";
        assert TextPuja != null : "fx:id=\"TextPuja\" was not injected: check your FXML file 'editar_puja_view.fxml'.";
    }

}