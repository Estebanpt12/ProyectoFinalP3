package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.InvalidLogInException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    /**
     *Botón de login
     */
    @FXML
    private Button loginBtn;
    /**
     *Campo para ingresar la contraseña
     */
    @FXML
    private PasswordField passwordTextField;
    /**
     *Label del título
     */
    @FXML
    private Label titleLabel;
    /**
     *TextField del nombre de usuario
     */
    @FXML
    private TextField usernameTextField;
    /**
     *Método para hacer login al pulsar el botón
     * @param event Evento de pulsar el botón de login
     * @throws InvalidLogInException valida si el Login es válido
     */
    @FXML
    void loginUsuario(ActionEvent event) throws InvalidLogInException {
        try {
            Singleton.logearUsuario();
        }catch (InvalidLoginException il){
            throw InvalidLoginException;
        }catch (EmptyFieldsException ef){
            JOptionPane.showMessageDialog(null,"Hay campos vacíos");
        }
    }
}