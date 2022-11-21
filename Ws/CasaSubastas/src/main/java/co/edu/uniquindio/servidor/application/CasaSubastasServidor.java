package co.edu.uniquindio.servidor.application;

import co.edu.uniquindio.servidor.servidor.Servidor;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import co.edu.uniquindio.servidor.exceptions.BidNotFoundException;
import co.edu.uniquindio.servidor.exceptions.ProductNotFoundException;

public class CasaSubastasServidor extends Application {
    @Override
    public void start(Stage stage) throws IOException, BidNotFoundException, ProductNotFoundException {
        try{
            Servidor Servidor = new Servidor();
            Servidor.iniciarServidor();
        }catch( co.edu.uniquindio.casasubastas.exceptions.BidNotFoundException |
               co.edu.uniquindio.casasubastas.exceptions.ProductNotFoundException e){

        }
    }

    public static void main(String[] args) {
        launch();

    }
}