package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.InvalidLogInException;
import co.edu.uniquindio.casasubastas.exceptions.UserNotFoundException;
import co.edu.uniquindio.casasubastas.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controller del login view
 */
public class LoginController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     *Botón de login
     */
    @FXML
    private Button loginBtn;


    @FXML
    private Button crearUsuarioBtn;

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
     * Método para hacer login al pulsar el botón
     * @param event Evento de pulsar el botón de login
     * @throws InvalidLogInException valida si el Login es válido
     */
    @FXML
    void actionLogin(ActionEvent event) {
        try {
            validarFields();
            modelFactoryController.iniciarSesion(usernameTextField.getText(), passwordTextField.getText());
            if(modelFactoryController.getUsuarioLogeado().getiUsuario() instanceof Comprador){
                URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/comprador_view.fxml").toURI().toURL();
                Parent root1 = FXMLLoader.load(url);
                Scene scene1 = new Scene(root1, 786, 400);
                Stage stage1 = new Stage();
                stage1.setTitle("Comprador");
                stage1.setScene(scene1);
                stage1.show();
            }else{
                URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/anunciante_view.fxml").toURI().toURL();
                Parent root1 = FXMLLoader.load(url);
                Scene scene1 = new Scene(root1, 804, 570);
                Stage stage1 = new Stage();
                stage1.setTitle("Anunciante");
                stage1.setScene(scene1);
                stage1.show();
            }
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();
        } catch (EmptyFieldsException | UserNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "inicioSesion");
        }
    }

    @FXML
    void actionCrear(ActionEvent event) {
        try {
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/crear_usuario_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 600, 400);
            Stage stage1 = new Stage();
            stage1.setTitle("Crear usuario");
            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para validar los campos de la interfaz de usuario
     * @throws EmptyFieldsException se valida si los campos estan vacios y se lanz la excepcion
     */
    private void validarFields() throws EmptyFieldsException {
        if(usernameTextField.getText().equals("") || passwordTextField.getText().equals("")){
            throw new EmptyFieldsException("Algun campo esta vacio");
        }
    }

    @FXML
    void initialize() {
        assert crearUsuarioBtn != null : "fx:id=\"crearUsuarioBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'login-view.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'login-view.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'login-view.fxml'.";
        modelFactoryController = ModelFactoryController.getInstance();
    }
}