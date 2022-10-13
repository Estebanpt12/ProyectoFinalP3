
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

public class gui4Controller {

    /**
     * Botón de Regrisrto
     */
    @FXML // fx:id="btnRegistro"
    private Button btnRegistro; // Value injected by FXMLLoader
    /**
     * TextField de la edad
     */
    @FXML // fx:id="edadTextField"
    private TextField edadTextField; // Value injected by FXMLLoader
    /**
     * TextField del nombre
     */
    @FXML // fx:id="nombreTextField"
    private TextField nombreTextField; // Value injected by FXMLLoader
    /**
     * TextField de la contraseña
     */
    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader
    /**
     * Label del título de la ventana
     */
    @FXML // fx:id="titleLabel"
    private Label titleLabel; // Value injected by FXMLLoader
    /**
     * TextField del nombre de usuario
     */
    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader

    /**
     * Método para crear comprador al oprimir el botón de registro
     * @param event evento de oprimir el botón
     * @throws InvalidUserException Válida si el usuario es válido
     * @throws ExistingUserException Válida si el usuario que se intenta registrar ya existe
     */
    @FXML
    void crearComprador(ActionEvent event) throws InvalidUserException,ExistingUserException{
        try{
            Singleton.crearComprador();
        }catch (EmptyFieldsException ef){
            JOptionPane.showMessageDialog(null,"Hay campos vacíos");
        }catch (InvalidUserException ie){
            throw ie;
        }catch (ExistingUserException ex){
            throw ex;
        }
    }
}