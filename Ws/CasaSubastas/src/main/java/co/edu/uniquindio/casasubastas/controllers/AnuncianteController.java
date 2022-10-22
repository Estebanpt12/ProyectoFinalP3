package co.edu.uniquindio.casasubastas.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.exceptions.BidNotFoundException;
import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.ProductNotFoundException;
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

    @FXML
    private Button botonRecargar;

    private String nombreProductoBuscado = "";

    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    private ObservableList<Puja> pujas = FXCollections.observableArrayList();

    @FXML
    void actionBuscar(ActionEvent event) {
        try {
            validarFieldBuscar();
            pujas.clear();
            pujas.addAll(modelFactoryController.tomarListaPujasProducto(txtNombreProducto.getText()));
            TablaPujas.setItems(pujas);
            nombreProductoBuscado = txtNombreProducto.getText();
        } catch (EmptyFieldsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (BidNotFoundException | ProductNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema", 3, "anunciante");
        }
    }

    private void validarFieldBuscar() throws EmptyFieldsException {
        if(txtNombreProducto.getText().equals("")){
            throw new EmptyFieldsException("El valor esta vacio");
        }
    }

    @FXML
    void actionCrear(ActionEvent event) {
        try{
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/crear_anuncio_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 600, 543);
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
        try{
            Producto producto = TablaProductos.getSelectionModel().getSelectedItem();
            modelFactoryController.setProductoEditar(TablaProductos.getSelectionModel().getSelectedItem());
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/editar_anuncio_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 600, 543);
            Stage stage1 = new Stage();
            stage1.setTitle("Editar anuncio");
            stage1.setScene(scene1);
            stage1.show();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema", 3, "anunciante");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla");
        }
    }

    @FXML
    void actionRecargar(ActionEvent event) {
        productos.clear();
        productos.addAll(modelFactoryController.tomarListaProductoUsuario());
        TablaProductos.setItems(productos);
    }

    @FXML
    void actionEliminar(ActionEvent event) {
        try {
            Producto producto = TablaProductos.getSelectionModel().getSelectedItem();
            modelFactoryController.eliminarProducto(producto.getTipoProducto(), producto.getNombre());
            JOptionPane.showMessageDialog(null, "Producto eliminado");
        } catch (ProductNotFoundException | IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Seleccione una puja de la tabla");
        }
    }

    @FXML
    void actionVender(ActionEvent event) {
        try {
            validarTablaPuja();
            modelFactoryController.vender(nombreProductoBuscado, TablaPujas.getSelectionModel().getSelectedItem());
            JOptionPane.showMessageDialog(null, "El producto ha sido vendido");
        } catch (EmptyFieldsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void validarTablaPuja() throws EmptyFieldsException {
        if(TablaPujas.getSelectionModel().getSelectedItem() == null){
            throw new EmptyFieldsException("No hay puja seleccionada");
        }
    }

    private void loadTable(){
        ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColumnTipo.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        ColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        ColumnInicio.setCellValueFactory(new PropertyValueFactory<>("FechaInicio"));
        ColumnFin.setCellValueFactory(new PropertyValueFactory<>("FechaFin"));
        ColumnValorInicial.setCellValueFactory(new PropertyValueFactory<>("ValorInicial"));
        ColumnValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        ColumnComprador.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
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
        assert botonRecargar != null : "fx:id=\"botonRecargar\" was not injected: check your FXML file 'anunciante_view.fxml'.";

    }

}
