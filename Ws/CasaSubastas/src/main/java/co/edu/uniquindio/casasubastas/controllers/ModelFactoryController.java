package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.Producto;
import co.edu.uniquindio.casasubastas.model.Puja;
import co.edu.uniquindio.casasubastas.model.SubastasQuindio;
import co.edu.uniquindio.casasubastas.model.Usuario;
import co.edu.uniquindio.casasubastas.persistencia.Persistencia;

import java.io.IOException;
import java.time.LocalDateTime;

public class ModelFactoryController {

    SubastasQuindio subastasQuindio;
    Usuario usuarioLogeado;
    Producto productoEditar;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        usuarioLogeado = new Usuario();
        productoEditar = new Producto();
        cargarResourceXML();
        if(subastasQuindio == null){
            System.out.println("es null");
            subastasQuindio = new SubastasQuindio();
            guardarResourceXML();
            guardarResourceBinario();
        }
    }

    private void cargarDatosDesdeArchivos() {
        subastasQuindio = new SubastasQuindio();
        try {
            subastasQuindio.setProductos(Persistencia.cargarProductos());
            subastasQuindio.setUsuarios(Persistencia.cargarUsuarios());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarResourceBinario() {
        subastasQuindio = Persistencia.cargarRecursoCasaBinario();
    }

    public void guardarResourceBinario() {
        Persistencia.guardarRecursoCasaBinario(subastasQuindio);
    }

    public void cargarResourceXML() {
        subastasQuindio = Persistencia.cargarRecursoCasaXML();
    }

    public void guardarResourceXML() {
        Persistencia.guardarRecursoCasaXML(subastasQuindio);
    }

    public SubastasQuindio getSubastasQuindio() {
        return subastasQuindio;
    }

    public void setSubastasQuindio(SubastasQuindio subastasQuindio) {
        this.subastasQuindio = subastasQuindio;
    }

    public void guardarRespaldo(){
        try {
            Persistencia.guardarUsuariosRespaldo(subastasQuindio.getUsuarios());
            Persistencia.guardarProductosRespaldo(subastasQuindio.getProductos());
            Persistencia.guardarRecursoCasaBinarioRespaldo(subastasQuindio);
            Persistencia.guardarRecursoCasaXMLRespaldo(subastasQuindio);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void guardarSubastasQuindio(){
        try {
            Persistencia.guardarProductos(subastasQuindio.getProductos());
            Persistencia.guardarUsuarios(subastasQuindio.getUsuarios());
            Persistencia.guardarRecursoCasaXML(subastasQuindio);
            Persistencia.guardarRecursoCasaBinario(subastasQuindio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearAnunciante(String usuario, String contrasenia, String nombre, int edad) throws ExistingUserException, InvalidUserException {
        subastasQuindio.crearAnunciante(usuario, contrasenia, nombre, edad);
        crearRegistroLog("Anunciante creado: " + usuario, 1, "Creacion");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public void crearComprador(String usuario, String contrasenia, String nombre, int edad) throws ExistingUserException, InvalidUserException {
        subastasQuindio.crearComprador(usuario, contrasenia, nombre, edad);
        crearRegistroLog("Comprador creado: " + usuario, 1, "Creacion");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public void crearPuja(String nombreProducto, String usuarioPujante, double valorPujado) throws UserNotFoundException, InsufficientBidException, ProductNotFoundException, ProductsLimitException {
        LocalDateTime localDateTime = LocalDateTime.now();
        subastasQuindio.crearPuja(nombreProducto, usuarioPujante, valorPujado, localDateTime);
        crearRegistroLog("Puja realizada", 1, "Pujar");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public void crearProducto(String tipoProducto, String nombre, String descripcion, String foto,
                              double vInicial) throws UserNotFoundException, ProductsLimitException {
        LocalDateTime localDateTime = LocalDateTime.now();
        subastasQuindio.crearProducto(tipoProducto, nombre, descripcion, foto, localDateTime,
                localDateTime.plusMinutes(30), vInicial, usuarioLogeado.getNombre());
        crearRegistroLog("Producto creado", 1, "Creacion");
        guardarRespaldo();
        guardarSubastasQuindio();
    }

    public void crearRegistroLog(String mensajeLog, int nivel, String accion){
        Persistencia.guardaRegistroLog(mensajeLog, nivel, accion);
    }

    public void iniciarSesion(String usuario, String contrasenia) throws UserNotFoundException, IOException {
        usuarioLogeado = Persistencia.iniciarSesion(usuario, contrasenia);
        crearRegistroLog("El usuario "+ usuario +" ha iniciado sesion", 1, "Login");
    }

    public void editarPuja(String nombreProducto, String usuarioPujante, double valorPujado, LocalDateTime fecha) throws BidNotFoundException, InsufficientBidException, ProductNotFoundException {
        subastasQuindio.editarPuja(nombreProducto, usuarioPujante, valorPujado, fecha);
        crearRegistroLog("La puja del usuario "+ usuarioPujante +" por el producto "+ nombreProducto+ " ha sido editada"
                            , 1, "Editar puja");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public void eliminarPuja(String nombreProducto, String usuarioPujante) throws BidNotFoundException, ProductNotFoundException, IOException {
        Puja puja = subastasQuindio.eliminarPuja(nombreProducto, usuarioPujante);
        Persistencia.guardarPujaEliminada(puja, nombreProducto);
        crearRegistroLog("El usuario "+usuarioPujante+" ha eliminado la puja a el producto "+nombreProducto+" con valor de"
                +puja.getValor()+" hecha en la fecha "+ puja.getFecha(), 2, "Eliminar puja");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public void editarProducto(String tipoProducto, String nombre, String descripcion, String foto, double vInicial) throws ProductNotFoundException {
        subastasQuindio.editarProducto(tipoProducto, nombre, descripcion, foto, vInicial);
        crearRegistroLog("El producto "+nombre+" que pertenece al tipo "+tipoProducto+" ha sido editado", 1,
                "Editar producto");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public void eliminarProducto(String tipoProducto, String nombre) throws ProductNotFoundException, IOException {
        Producto producto = subastasQuindio.eliminarProducto(tipoProducto, nombre);
        Persistencia.guardarProductoEliminado(producto);
        crearRegistroLog("El producto "+nombre+" que pertenece al tipo "+tipoProducto+" ha sido eliminado", 2,
                "Eliminar producto");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public Producto getProductoEditar() {
        return productoEditar;
    }

    public void setProductoEditar(Producto productoEditar) {
        this.productoEditar = productoEditar;
    }

    public void crearMensaje(String mensaje, String codigoUsuarioDestinatario){
        subastasQuindio.crearMensajeEnviado(usuarioLogeado.getUsuario(), codigoUsuarioDestinatario, mensaje);
        subastasQuindio.crearMensajeRecibido(usuarioLogeado.getUsuario(),codigoUsuarioDestinatario,mensaje);
        crearRegistroLog("El mensaje enviado por "+usuarioLogeado.getUsuario()+" a "+ codigoUsuarioDestinatario+
                        " ha sido creado", 1, "crearMensaje");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
}
