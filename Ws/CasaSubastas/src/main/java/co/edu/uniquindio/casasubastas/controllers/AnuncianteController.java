package co.edu.uniquindio.casasubastas.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.model.Producto;
import co.edu.uniquindio.casasubastas.model.Puja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

public class AnuncianteController {

    ModelFactoryController modelFactoryController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BotonBuscar;

    @FXML
    private Button BotonCrear;

    @FXML
    private Button BotonEditar;

    @FXML
    private Button BotonEliminar;

    @FXML
    private Button BotonVender;

    @FXML
    private TableColumn<Puja, String> ColumnComprador;

    @FXML
    private TableColumn<Producto, String> ColumnDescripcion;

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnFin;

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnInicio;

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnNombre;

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnTipo;

    @FXML
    private TableColumn<Puja, Double> ColumnValor;

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnValorInicial;

    @FXML
    private TableView<Producto> TablaProductos;

    @FXML
    private TableView<Puja> TablaPujas;

    @FXML
    private Label titleLabel1;

    @FXML
    private TextField txtNombreProducto;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    void actionBuscar(ActionEvent event) {

    }

    //TODO Agregar boton actualizar
    @FXML
    void actionCrear(ActionEvent event) {
        try{
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/crear_anuncio_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 600, 643);
            Stage stage1 = new Stage();
            stage1.setTitle("Crear anuncio");
            stage1.setScene(scene1);
            stage1.show();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema", 3, "anunciante");
        }
    }

    @FXML
    void actionEditar(ActionEvent event) {

    }

    @FXML
    void actionEliminar(ActionEvent event) {

    }

    @FXML
    void actionVender(ActionEvent event) {

    }

    private void loadTable(){
        ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColumnTipo.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        ColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        ColumnInicio.setCellValueFactory(new PropertyValueFactory<>("FechaInicio"));
        ColumnFin.setCellValueFactory(new PropertyValueFactory<>("FechaFin"));
        ColumnValorInicial.setCellValueFactory(new PropertyValueFactory<>("ValorInicial"));
        productos.clear();
        productos.addAll(modelFactoryController.tomarListaProductoUsuario());
        TablaProductos.setItems(productos);
    }

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        loadTable();
        assert BotonBuscar != null : "fx:id=\"BotonBuscar\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert BotonCrear != null : "fx:id=\"BotonCrear\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert BotonEditar != null : "fx:id=\"BotonEditar\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert BotonEliminar != null : "fx:id=\"BotonEliminar\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert BotonVender != null : "fx:id=\"BotonVender\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnComprador != null : "fx:id=\"ColumnComprador\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnDescripcion != null : "fx:id=\"ColumnDescripcion\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnFin != null : "fx:id=\"ColumnFin\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnInicio != null : "fx:id=\"ColumnInicio\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnNombre != null : "fx:id=\"ColumnNombre\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnTipo != null : "fx:id=\"ColumnTipo\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnValor != null : "fx:id=\"ColumnValor\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert ColumnValorInicial != null : "fx:id=\"ColumnValorInicial\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert TablaProductos != null : "fx:id=\"TablaProductos\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert TablaPujas != null : "fx:id=\"TablaPujas\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert titleLabel1 != null : "fx:id=\"titleLabel1\" was not injected: check your FXML file 'anunciante_view.fxml'.";
        assert txtNombreProducto != null : "fx:id=\"txtNombreProducto\" was not injected: check your FXML file 'anunciante_view.fxml'.";

    }

}
