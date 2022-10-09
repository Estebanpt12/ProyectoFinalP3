package co.edu.uniquindio.casasubastas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class gui4Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}