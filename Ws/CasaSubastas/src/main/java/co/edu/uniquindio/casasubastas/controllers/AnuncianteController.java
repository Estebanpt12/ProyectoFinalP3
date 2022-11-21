package co.edu.uniquindio.casasubastas.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.exceptions.BidNotFoundException;
import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.ProductNotFoundException;
import co.edu.uniquindio.casasubastas.model.Producto;
import co.edu.uniquindio.casasubastas.model.Puja;
import co.edu.uniquindio.casasubastas.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    /**
     *Boton para buscar
     */
    @FXML
    private Button BotonBuscar;
    /**
     *Boton para crear un anuncio
     */
    @FXML
    private Button BotonCrear;
    /**
     *Boton para editar
     */
    @FXML
    private Button BotonEditar;

    /**
     * Boton para eliminar
     * */

    @FXML
    private Button BotonEliminar;
    /**
     * Boton para vender
     * */

    @FXML
    private Button BotonVender;

    /**
     * Columna de la tabla correspondiente al comprador
     * */
    @FXML
    private TableColumn<Puja, String> ColumnComprador;

    /**
     * Columna de la tabla correspondiente a la descripcion del producto
     */
    @FXML
    private TableColumn<Producto, String> ColumnDescripcion;
    /**
     *Columna de la tabla correspondiente a la fecha de finalizacion del anuncio
     */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnFin;
    /**
     * Columna de la tabla correspondiente a la fecha de inicio del anuncio
     */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnInicio;
    /**
     * Columna de la tabla correspondiente al nombre
     */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnNombre;
    /**
     * Columna de la tabla correspondiente al tipo del producto
     */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnTipo;
    /**
     * Columna de la tabla correspondiente al valor pujado
     * */

    @FXML
    private TableColumn<Puja, Double> ColumnValor;
    /**
     * Columna de la tabla correspondiente al valor con el que un producto inicia la subasta
     */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnValorInicial;
    /**
     * Tabla que muestra los productos
     */

    @FXML
    private TableView<Producto> TablaProductos;
    /**
     * Tabla que muestra las pujas
     */

    @FXML
    private TableView<Puja> TablaPujas;
    /**
     * Label correspondiente al titulo
     */

    @FXML
    private Tab Chats;

    @FXML
    private TabPane AnuncianteView;
    
    @FXML
    private Label titleLabel1;
    /**
     * TextField para ingresar el nombre de un producto
     */
    @FXML
    private TextField txtNombreProducto;
    /**
     * Boton para recargar
     * */
    @FXML
    private Button botonRecargar;
    /**
     * String con el nombre del producto buscado
     */
    private String nombreProductoBuscado = "";
    /**
     * ObservableList de productos
     */
    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    /**
     * ObservableList de pujas
     */
    private ObservableList<Puja> pujas = FXCollections.observableArrayList();
    /**
     * Metodo para buscar al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     */
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
    /**
     * Metodo para validar si el producto a buscar esta vacio
     * @throws EmptyFieldsException Se valida si el cmapo esta vacio
     */
    private void validarFieldBuscar() throws EmptyFieldsException {
        if(txtNombreProducto.getText().equals("")){
            throw new EmptyFieldsException("El valor esta vacio");
        }
    }
    /**
     * Metodo para crear un anuncio al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     * */
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
    /**
     * Metodo para editar al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     * */
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
    /**
     * Método para recargar al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     */
    @FXML
    void actionRecargar(ActionEvent event) {
        productos.clear();
        productos.addAll(modelFactoryController.tomarListaProductoUsuario());
        TablaProductos.setItems(productos);
    }
    /**
     * Metodo para eliminar al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     * */
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
    /**
     * Metodo para realizar la accion de vender al pulsar el boton correspondiente
     * @param event Evento de pulsar el boton
     * */
    @FXML
    void actionVender(ActionEvent event) {
        try {
            validarTablaPuja();
            modelFactoryController.vender(nombreProductoBuscado, TablaPujas.getSelectionModel().getSelectedItem());
            modelFactoryController.crearChat(TablaPujas.getSelectionModel().getSelectedItem().getUsuario());
            JOptionPane.showMessageDialog(null, "El producto ha sido vendido");
        } catch (EmptyFieldsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /**
     *Metodo para validar si se ha seleccionado una puja de la tabla
     * @throws EmptyFieldsException Excepcion que valida si hay campos vacios
     */
    private void validarTablaPuja() throws EmptyFieldsException {
        if(TablaPujas.getSelectionModel().getSelectedItem() == null){
            throw new EmptyFieldsException("No hay puja seleccionada");
        }
    }
    /**
     * Metodo para cargar la tabla
     * */
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
