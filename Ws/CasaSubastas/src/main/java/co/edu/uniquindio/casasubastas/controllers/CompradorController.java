package co.edu.uniquindio.casasubastas.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.casasubastas.exceptions.BidNotFoundException;
import co.edu.uniquindio.casasubastas.exceptions.EmptyFieldsException;
import co.edu.uniquindio.casasubastas.exceptions.InsufficientBidException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

public class CompradorController {

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
     *Boton para editar
     */

    @FXML
    private Button BotonEditar;
    /**
     *Boton para eliminar
     */

    @FXML
    private Button BotonEliminar;
    /**
     * Boton para pujar
     * */

    @FXML
    private Button BotonPujar;
    /**
     * Boton para ver la imagen
     */

    @FXML
    private Button BotonVerImagen;
    /**
     * Boton para ver las pujas
     */

    @FXML
    private Button BotonVerPujas;
    /**
     * Columna de la tabla correspondiente a la descripcion del producto
     */

    @FXML
    private TableColumn<Producto, String> ColumnDescripcion;
    /**
     * Columna de la tabla correspondiente a la fecha
     */

    @FXML
    private TableColumn<Puja, LocalDateTime> ColumnFecha;
    /**
     * Columna de la tabla correspondiente a la fecha de finalizacion del anuncio
     */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnFin;
    /**
     * Columna de la tabla correspondiente a la fecha de inicio del anuncio
     * */

    @FXML
    private TableColumn<Producto, LocalDateTime> ColumnInicio;
    /**
     * Columna de la tabla correspondiente al nombre
     */

    @FXML
    private TableColumn<Producto, String> ColumnNombre;
    /**
     * Columna de la tabla correspondiente al tipo del producto
     */

    @FXML
    private TableColumn<Producto, String> ColumnTipo;
    /**
     * Columna de la tabla correspondiente al valor del producto
     */

    @FXML
    private TableColumn<Puja, Double> ColumnValor;

    /**
     * Columna de la tabla correspondiente al valor inicial del producto
     */

    @FXML
    private TableColumn<Producto, Double> ColumnValorInicial;
    /**
     * Tabla de productos
     */

    @FXML
    private TableView<Producto> Tabla;
    /**
     * Tab de productos
     */

    @FXML
    private Tab TableProductos;
    /**
     *Tabla de pujas
     */

    @FXML
    private TableView<Puja> TablePujas;
    /**
     *TextField para ingresar el nombre del producto
     */

    @FXML
    private TextField TextNombreProducto;

    /**
     *Boton para recargar
     */
    @FXML
    private Button BotonRecargar;
    /**
     *ObservableList de productos
     */
    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    /**
     *ObservableList de pujas
     */
    private ObservableList<Puja> pujas = FXCollections.observableArrayList();
    /**
     * Accion de recargar al pulsar el boton
     * @param event Evento de pulsar el boton
     */
    @FXML
    void actionRecargar(ActionEvent event) {
        if(modelFactoryController.productoBuscadoEditarPuja.equals("")){
            JOptionPane.showMessageDialog(null, "Primero busque un producto");
        }else {
            try {
                pujas.clear();
                pujas.addAll(modelFactoryController.tomarListaPujas(modelFactoryController.productoBuscadoEditarPuja));
                TablePujas.setItems(pujas);
            } catch (BidNotFoundException | ProductNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    /**
     * Accion de buscar al presionaer el boton
     * @param event Evento de pulsar el boton
     */
    @FXML
    void actionBuscar(ActionEvent event) {
        try {
            validarFieldBuscar();
            modelFactoryController.productoBuscadoEditarPuja = TextNombreProducto.getText();
            pujas.clear();
            pujas.addAll(modelFactoryController.tomarListaPujas(TextNombreProducto.getText()));
            TablePujas.setItems(pujas);
        } catch (EmptyFieldsException | BidNotFoundException | ProductNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Metodo para validar si el producto a buscar esta vacio
     * @throws EmptyFieldsException Se valida si el cmapo esta vacio
     */
    private void validarFieldBuscar() throws EmptyFieldsException {
        if(TextNombreProducto.getText().equals("")){
            throw new EmptyFieldsException("El campo esta vacio");
        }
    }
    /**
     * Metodo para editar una puja al presionar el boton
     * @param event Evento de presionar el boton
     */

    @FXML
    void actionEditarPuja(ActionEvent event) {
        try{
            validarTablaPujas();
            modelFactoryController.setPujaEditar(TablePujas.getSelectionModel().getSelectedItem());
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/editar_puja_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 260, 115);
            Stage stage1 = new Stage();
            stage1.setTitle("Editar puja");
            stage1.setScene(scene1);
            stage1.show();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Metodo para validar que hay una fila seleccionada en la tabla de pujas
     */
    private void validarTablaPujas(){
        if(TablePujas.getSelectionModel().getSelectedItem().getUsuario() == null){
            throw new NullPointerException("Selecciones una fila de la tabla");
        }
    }
    /**
     * Metodo para eliminar una puja al presionar el boton correspondiente
     * @param event Evento de presionar el boton
     * */
    @FXML
    void actionEliminarPuja(ActionEvent event) {
        try{
            modelFactoryController.eliminarPuja(modelFactoryController.productoBuscadoEditarPuja, TablePujas.getSelectionModel().getSelectedItem());
            pujas.remove(TablePujas.getSelectionModel().getSelectedItem());
            TablePujas.setItems(pujas);
            JOptionPane.showMessageDialog(null, "La puja ha sido borrada");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Seleccione una puja de la tabla");
        } catch (BidNotFoundException | ProductNotFoundException | IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }
    }
    /**
     * Metodo para realizar una puja al presionar el boton
     * @param event Evento de pulsar el boton
     */
    @FXML
    void actionPujar(ActionEvent event) {
        try{
            modelFactoryController.setProductoPujar(Tabla.getSelectionModel().getSelectedItem());
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/pujar_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 260, 115);
            Stage stage1 = new Stage();
            stage1.setTitle("Pujar");
            stage1.setScene(scene1);
            stage1.show();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla");
        }
    }
    /**
     * Metodo para ver la imagen del producto al presionar el boton correspondiente
     * @param event Evento de presionar el boton
     */
    @FXML
    void actionVerImagen(ActionEvent event) {
        try{
            modelFactoryController.setRutaImageView(Tabla.getSelectionModel().getSelectedItem().getRutaFoto());
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/imagen_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 572, 400);
            Stage stage1 = new Stage();
            stage1.setTitle("Foto");
            stage1.setScene(scene1);
            stage1.show();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla");
        }
    }
    /**
     * Metodo para ver las pujas al presionar el boton
     * @param event Evento de presionar el boton
     */
    @FXML
    void actionVerPujas(ActionEvent event) {
        try{
            modelFactoryController.setProductoPujasView(Tabla.getSelectionModel().getSelectedItem());
            URL url = new File("src/main/java/co/edu/uniquindio/casasubastas/views/pujas_view.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Scene scene1 = new Scene(root1, 585, 242);
            Stage stage1 = new Stage();
            stage1.setTitle("Pujas de producto");
            stage1.setScene(scene1);
            stage1.show();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Hubo un error en el sistema");
            modelFactoryController.crearRegistroLog("Error en los archivos del sistema" ,3, "comprador");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla");
        }
    }

    /**
     * Metodo para cargar las columnas de la tabla
     */
    private void loadTables(){
        ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColumnTipo.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        ColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        ColumnInicio.setCellValueFactory(new PropertyValueFactory<>("FechaInicio"));
        ColumnFin.setCellValueFactory(new PropertyValueFactory<>("FechaFin"));
        ColumnValorInicial.setCellValueFactory(new PropertyValueFactory<>("ValorInicial"));
        ColumnValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        ColumnFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        productos.clear();
        productos.addAll(loadProducts());
        Tabla.setItems(productos);
    }
    /**
     * Metodo para cargar los productos
     */
    private ArrayList<Producto> loadProducts(){
        ArrayList<Producto> listaAuxiliar = new ArrayList<>();
        for (int i = 0; i< modelFactoryController.getSubastasQuindio().getProductos().size(); i++){
            if(!modelFactoryController.getSubastasQuindio().getProductos().get(i).isVendido() &&
                    modelFactoryController.getSubastasQuindio().getProductos().get(i).getFechaFin().isAfter(LocalDateTime.now())){
                listaAuxiliar.add(modelFactoryController.getSubastasQuindio().getProductos().get(i));
            }
        }
        return listaAuxiliar;
    }

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        assert BotonBuscar != null : "fx:id=\"BotonBuscar\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert BotonEditar != null : "fx:id=\"BotonEditar\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert BotonEliminar != null : "fx:id=\"BotonEliminar\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert BotonPujar != null : "fx:id=\"BotonPujar\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert BotonVerImagen != null : "fx:id=\"BotonVerImagen\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert BotonVerPujas != null : "fx:id=\"BotonVerPujas\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnDescripcion != null : "fx:id=\"ColumnDescripcion\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnFecha != null : "fx:id=\"ColumnFecha\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnFin != null : "fx:id=\"ColumnFin\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnInicio != null : "fx:id=\"ColumnInicio\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnNombre != null : "fx:id=\"ColumnNombre\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnTipo != null : "fx:id=\"ColumnTipo\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnValor != null : "fx:id=\"ColumnValor\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert ColumnValorInicial != null : "fx:id=\"ColumnValorInicial\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert BotonRecargar != null : "fx:id=\"BotonRecargar\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert Tabla != null : "fx:id=\"Tabla\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert TableProductos != null : "fx:id=\"TableProductos\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert TablePujas != null : "fx:id=\"TablePujas\" was not injected: check your FXML file 'comprador_view.fxml'.";
        assert TextNombreProducto != null : "fx:id=\"TextNombreProducto\" was not injected: check your FXML file 'comprador_view.fxml'.";
        loadTables();
    }

}
