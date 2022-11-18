package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.ExistingUserException;
import co.edu.uniquindio.casasubastas.exceptions.InvalidUserException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controller del registro de usuarios
 */
public class CrearUsuarioController {

        private ModelFactoryController modelFactoryController;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        /**
         *Botón de registro
         */
        @FXML
        private Button btnRegistro;

        /**
         *TextField de la edad
         */
        @FXML
        private TextField edadTextField;

        /**
         *TextField del nombre
         */
        @FXML
        private TextField nombreTextField;
         /**
         *TextField del nombre
         */
         @FXML
         private PasswordField passwordTextField;

        /**
         *Label del título
         */
        @FXML
        private Label titleLabel;
        /**
         * Text Field del nombre de usuario
         */
        @FXML
        private TextField userNameTextField;

        /**
         * Combo del tipo usuario
         */
        @FXML
        private ComboBox<String> comboTipo;

        /**
         * Método para crear un usuario al oprimir el botón de registro
         */
        public void actionCrearUsuario(javafx.event.ActionEvent actionEvent) {
                try {
                        validarFields();
                        if(comboTipo.getSelectionModel().getSelectedIndex() == 1){
                                modelFactoryController.crearAnunciante(userNameTextField.getText(), passwordTextField.getText(),
                                        nombreTextField.getText(), Integer.parseInt(edadTextField.getText()));
                        }
                        if(comboTipo.getSelectionModel().getSelectedIndex() == 2) {
                                modelFactoryController.crearComprador(userNameTextField.getText(), passwordTextField.getText(),
                                        nombreTextField.getText(), Integer.parseInt(edadTextField.getText()));
                        }
                        JOptionPane.showMessageDialog(null, "Usuario creado");
                        Stage stage = (Stage) btnRegistro.getScene().getWindow();
                        stage.close();
                } catch (EmptyFieldsException | ExistingUserException | InvalidUserException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Error en el formato del valor incial");
                }
        }

        /**
         * Metodo para validar si los campos estan llenos y para validar el formato de la edad
         * @throws EmptyFieldsException Se valida si los campos estan llenos
         */
        private void validarFields() throws EmptyFieldsException, NumberFormatException {
                if(edadTextField.getText().equals("") || nombreTextField.getText().equals("") || passwordTextField.getText().equals("")
                        || userNameTextField.getText().equals("") || comboTipo.getSelectionModel().getSelectedIndex() == 0){
                        throw new EmptyFieldsException("Algun campo esta vacio");
                }
                Integer.parseInt(edadTextField.getText());
        }

        @FXML
        void initialize() {
                assert btnRegistro != null : "fx:id=\"btnRegistro\" was not injected: check your FXML file 'crear_usuario_view.fxml'.";
                assert edadTextField != null : "fx:id=\"edadTextField\" was not injected: check your FXML file 'crear_usuario_view.fxml'.";
                assert nombreTextField != null : "fx:id=\"nombreTextField\" was not injected: check your FXML file 'crear_usuario_view.fxml'.";
                assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'crear_usuario_view.fxml'.";
                assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'crear_usuario_view.fxml'.";
                assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'crear_usuario_view.fxml'.";
                initializeCombo();
                modelFactoryController = ModelFactoryController.getInstance();
        }

        /**
         * Metodo para inicializar las opciones del combo box
         */
        private void initializeCombo(){
                comboTipo.getItems().add("Tipo usuario");
                comboTipo.getItems().add("Anunciante");
                comboTipo.getItems().add("Comprador");
        }
}