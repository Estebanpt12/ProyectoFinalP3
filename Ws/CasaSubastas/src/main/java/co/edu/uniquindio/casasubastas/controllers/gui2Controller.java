package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.ExistingUserException;
import co.edu.uniquindio.casasubastas.exceptions.InvalidUserException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class gui2Controller {
        /**
         *Botón de registro
         */
        @FXML // fx:id="btnRegistro"
        private Button btnRegistro; // Value injected by FXMLLoader

        /**
         *TextField de la edad
         */
        @FXML // fx:id="edadTextField"
        private TextField edadTextField; // Value injected by FXMLLoader

        /**
         *TextField del nombre
         */
        @FXML // fx:id="nombreTextField"
        private TextField nombreTextField; // Value injected by FXMLLoader
         /**
         *TextField del nombre
         */
        @FXML // fx:id="passwordTextField"
        private PasswordField passwordTextField; // Value injected by FXMLLoader

        /**
         *Label del título
         */
        @FXML // fx:id="titleLabel"
        private Label titleLabel; // Value injected by FXMLLoader
        /**
         * Text Field del nombre de usuario
         */
        @FXML // fx:id="userNameTextField"
        private TextField userNameTextField; // Value injected by FXMLLoader

        /**
         * Método para crear un anunciante al oprimir el botón de registro
         * @param  event Evento de oprimir el botón de registro
         * @throws InvalidUserException valida si el usuario es válido
         * @throws ExistingUserException valida si el usuario que se intenta registrar ya existe
         */
        @FXML
        void crearAnunciante(ActionEvent event) throws InvalidUserException, ExistingUserException{
                try {
                        Singleton.crearAnunciante();
                } catch (EmptyFieldsException ef) {
                        JOptionPane.showMessageDialog(null, "Hay campos vacíos");
                } catch (InvalidUserException ie){
                        throw ie;
                } catch (ExistingUserException ex){
                        throw ex;
                }
        }
}