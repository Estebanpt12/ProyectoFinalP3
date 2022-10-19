package co.edu.uniquindio.casasubastas.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.model.Puja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PujasViewController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Puja, LocalDateTime> ColumnFecha;

    @FXML
    private TableColumn<Puja, Double> ColumnValor;

    @FXML
    private TableView<Puja> TablaPujas;

    private ObservableList<Puja> pujas = FXCollections.observableArrayList();

    private void loadTable(){
        ColumnValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        ColumnFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        pujas.clear();
        pujas.addAll(modelFactoryController.getProductoPujasView().getListaPuja());
        TablaPujas.setItems(pujas);
        modelFactoryController.setProductoPujasView(null);
    }

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        assert ColumnFecha != null : "fx:id=\"ColumnFecha\" was not injected: check your FXML file 'pujas_view.fxml'.";
        assert ColumnValor != null : "fx:id=\"ColumnValor\" was not injected: check your FXML file 'pujas_view.fxml'.";
        assert TablaPujas != null : "fx:id=\"TablaPujas\" was not injected: check your FXML file 'pujas_view.fxml'.";
    }

}
