package co.edu.uniquindio.casasubastas.controllers;

import co.edu.uniquindio.casasubastas.cliente.Cliente;
import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.Chat;
import co.edu.uniquindio.casasubastas.model.Producto;
import co.edu.uniquindio.casasubastas.model.Puja;
import co.edu.uniquindio.casasubastas.model.SubastasQuindio;
import co.edu.uniquindio.casasubastas.model.Usuario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ModelFactoryController {

    /**
     * Casa de subastas
     */
    SubastasQuindio subastasQuindio;
    /**
     *Usuario con sesion iniciada
     */
    Usuario usuarioLogeado;
    /**
     *Producto a editar
     */
    Producto productoEditar = null;


    /**
     * Ruta de la imagen que se va a mostrar en la image view
     */
    String rutaImageView;

    /**
     * Producto del que se obtienen las pujas para la Pujas view
     */
    Producto productoPujasView = null;

    /**
     * Producto para pujar con el comprador
     */
    Producto productoPujar = null;
    /**
     * Producto buscado para editar su puja
     */
    String productoBuscadoEditarPuja;
    /**
     * Puja que se va a editar
     */
    Puja pujaEditar = null;

    /**
     * Clase estatica oculta
     */
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    /**
     *Metodo para obtener la instancia de la clase
     */
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        usuarioLogeado = new Usuario();
        rutaImageView = "";
        cargarResourceXML();
        if(subastasQuindio == null){
            System.out.println("es null");
            subastasQuindio = new SubastasQuindio();
            guardarResourceXML();
            guardarResourceBinario();
        }
    }

    /**
     *Metodo para cargar los datos desde los archivos
     */
    private void cargarDatosDesdeArchivos() {
        subastasQuindio = new SubastasQuindio();
        Cliente cliente = new Cliente(1); //Cargar datos desde txt
        cliente.iniciarCliente();
        subastasQuindio = cliente.getSubastasQuindio();
    }

    /**
     * Metodo para cargar el recurso binario
     */
    public void cargarResourceBinario() {
        Cliente cliente = new Cliente(2);
        cliente.iniciarCliente();
        subastasQuindio = cliente.getSubastasQuindio();
    }
    /**
     *Metodo para guardar el resource binario
     */
    public void guardarResourceBinario() {
        Cliente cliente = new Cliente(4);
        cliente.setSubastasQuindio(subastasQuindio);
        cliente.iniciarCliente();
    }
    /**
     *Metodo para cargar el recurso XML
     */
    public void cargarResourceXML() {
        Cliente cliente = new Cliente(3);
        cliente.iniciarCliente();
        subastasQuindio = cliente.getSubastasQuindio();
    }
    /**
     *Metodo para guardar recurso XML
     */
    public void guardarResourceXML() {
        Cliente cliente = new Cliente(5);
        cliente.setSubastasQuindio(subastasQuindio);
        cliente.iniciarCliente();
    }
    /**
     *Metodo getter para la casa de subastas
     */
    public SubastasQuindio getSubastasQuindio() {
        return subastasQuindio;
    }
    /**
     *Metodo para asignar la casa de subastas
     */
    public void setSubastasQuindio(SubastasQuindio subastasQuindio) {
        this.subastasQuindio = subastasQuindio;
    }
    /**
     *Metodo para guardar el respaldo
     */
    public void guardarRespaldo(){
        Cliente cliente = new Cliente(6);
        cliente.setSubastasQuindio(subastasQuindio);
        cliente.iniciarCliente();
    }
    /**
     *Metodo para guardar la casa de subastas
     */
    public void guardarSubastasQuindio(){
        Cliente cliente = new Cliente(7);
        cliente.setSubastasQuindio(subastasQuindio);
        cliente.iniciarCliente();
    }

    /**
     *Metodo para crear un anunciante
     * @param usuario Usuario con sesion iniciada
     * @param contrasenia Contrasenia del usuario
     * @param edad Edad del usuario
     * @param nombre Nombre del usuario
     */
    public void crearAnunciante(String usuario, String contrasenia, String nombre, int edad) throws ExistingUserException, InvalidUserException {
        subastasQuindio.crearAnunciante(usuario, contrasenia, nombre, edad);
        crearRegistroLog("Anunciante creado: " + usuario, 1, "Creacion");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo para crear comprador
     * @param usuario Usuario con señal iniciada
     * @param contrasenia Contrasenia del usuario
     * @param nombre Nombre del usuario
     * @param edad Edad del usuario
     * @throws ExistingUserException Se valida si el usuario existe
     * @throws InvalidUserException Se valida si el usuario es valido
     */
    public void crearComprador(String usuario, String contrasenia, String nombre, int edad) throws ExistingUserException, InvalidUserException {
        subastasQuindio.crearComprador(usuario, contrasenia, nombre, edad);
        crearRegistroLog("Comprador creado: " + usuario, 1, "Creacion");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo para crear una puja
     * @param nombreProducto Nombre del producto
     * @param valorPujado Valor que se puja por el producto
     * @throws UserNotFoundException Se valida si el usuario existe
     * @throws InsufficientBidException Se valida si la puja hecha es mayor al valor inicial del producto
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    public void crearPuja(String nombreProducto, double valorPujado) throws UserNotFoundException, InsufficientBidException, ProductNotFoundException, ProductsLimitException {
        LocalDateTime localDateTime = LocalDateTime.now();
        subastasQuindio.crearPuja(nombreProducto, usuarioLogeado.getUsuario(), valorPujado, localDateTime);
        crearRegistroLog("Puja realizada por el usuario "+usuarioLogeado.getUsuario(), 1, "Pujar");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo para crear un producto
     * @param nombre Nombre del producto
     * @param descripcion Descripcion del producto
     * @param foto Foto del producto
     * @param tipoProducto Tipo de producto
     * @param vInicial Valor inicial del producto
     * @throws UserNotFoundException Se valida si el usuario existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    public void crearProducto(String tipoProducto, String nombre, String descripcion, String foto,
                              double vInicial) throws UserNotFoundException, ProductsLimitException {
        LocalDateTime localDateTime = LocalDateTime.now();
        subastasQuindio.crearProducto(tipoProducto, nombre, descripcion, foto, localDateTime,
                localDateTime.plusMinutes(30), vInicial, usuarioLogeado.getNombre());
        crearRegistroLog("Producto creado", 1, "Creacion");
        guardarRespaldo();
        guardarSubastasQuindio();
    }
    /**
     *Metodo para crear el registro en el log
     * @param mensajeLog Mensaje que se guardará en el log
     * @param nivel Nivel del registro que quedará en el log
     * @param accion Accion realizada que se guardará en el log
     */
    public void crearRegistroLog(String mensajeLog, int nivel, String accion){
        Cliente cliente = new Cliente(8);
        cliente.setLog(mensajeLog+","+nivel+","+accion);
        cliente.iniciarCliente();
    }
    /**
     *Metodo para iniciar sesion
     * @param usuario Usuario del log in
     * @param contrasenia Contrasenia del usuario
     * @throws UserNotFoundException Se valida si el usuario existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void iniciarSesion(String usuario, String contrasenia) throws UserNotFoundException, IOException {
        Cliente cliente = new Cliente(9);
        cliente.setCrendenciales(usuario+","+contrasenia);
        cliente.iniciarCliente();
        usuarioLogeado = cliente.getUsuario();
        if(usuarioLogeado == null){
            throw new UserNotFoundException("Error en la combinacion de usuario y contraseña");
        }
        crearRegistroLog("El usuario "+ usuario +" ha iniciado sesion", 1, "Login");
    }
    /**
     * Metodo para editar una puja
     * @param nombreProducto Nombre del producto
     * @param puja puja a editar
     * @throws BidNotFoundException Se valida si la puja existe
     * @throws InsufficientBidException Se valida si la puja hecha es mayor al valor inicial del producto
     * @throws ProductNotFoundException Se valida si el producto existe
     */
    public void editarPuja(String nombreProducto, Puja puja, double valorPujadoEditar) throws BidNotFoundException, InsufficientBidException, ProductNotFoundException {
        subastasQuindio.editarPuja(nombreProducto, puja, valorPujadoEditar);
        crearRegistroLog("La puja del usuario "+ usuarioLogeado.getUsuario() +" por el producto "+
                        nombreProducto+ " ha sido editada", 1, "Editar puja");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     * Metodo para eliminar una puja
     * @param nombreProducto Nombre del producto
     * @throws BidNotFoundException Se valida si la puja existe
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void eliminarPuja(String nombreProducto, Puja p) throws BidNotFoundException, ProductNotFoundException, IOException {
        Puja puja = subastasQuindio.eliminarPuja(nombreProducto, p);
        Cliente cliente = new Cliente(10);
        cliente.setPuja(puja);
        cliente.setNombreProducto(nombreProducto);
        cliente.iniciarCliente();
        crearRegistroLog("El usuario "+usuarioLogeado.getUsuario()+" ha eliminado la puja a el producto "
                +nombreProducto+" con valor de"+puja.getValor()+" hecha en la fecha "+ puja.getFecha(), 2,
                "Eliminar puja");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo para editar un producto
     * @param tipoProducto Tipo del producto
     * @param nombre Nombre del producto
     * @param descripcion Descripcion del producto
     * @param foto Foto del producto
     * @param vInicial Valor inicial del producto
     * @throws ProductNotFoundException Se valida si el producto existe
     */
    public void editarProducto(String tipoProducto, String nombre, String descripcion, String foto, double vInicial) throws ProductNotFoundException {
        subastasQuindio.editarProducto(tipoProducto, nombre, descripcion, foto, vInicial);
        crearRegistroLog("El producto "+nombre+" que pertenece al tipo "+tipoProducto+" ha sido editado", 1,
                "Editar producto");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo para eliminar un producto
     * @param tipoProducto Tipo del producto
     * @param nombre Nombre del producto
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void eliminarProducto(String tipoProducto, String nombre) throws ProductNotFoundException, IOException {
        Producto producto = subastasQuindio.eliminarProducto(tipoProducto, nombre);
        Cliente cliente = new Cliente(11);
        cliente.setProducto(producto);
        cliente.iniciarCliente();
        crearRegistroLog("El producto "+nombre+" que pertenece al tipo "+tipoProducto+" ha sido eliminado", 2,
                "Eliminar producto");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo para vender un producto
     * @param puja Puja ganadora
     * @param nombreProducto Nombre del producto
     */
    public void vender(String nombreProducto, Puja puja){
        subastasQuindio.venderProducto(nombreProducto);
        crearRegistroLog("El producto "+nombreProducto+" ha sido vendido", 2,
                "Vender Producto");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    /**
     *Metodo getter de productoEditar
     */
    public Producto getProductoEditar() {
        return productoEditar;
    }
    /**
     *Metodo setter de productoEditar
     */
    public void setProductoEditar(Producto productoEditar) {
        this.productoEditar = productoEditar;
    }
    /**
     * Metodo para crear un mensaje
     * @param mensaje Mensaje a crear
     * @param codigoUsuarioDestinatario Codigo del destinatario del mensaje
     * */
    public void crearMensaje(String mensaje, String codigoUsuarioDestinatario){
        subastasQuindio.crearMensaje(usuarioLogeado.getUsuario(),usuarioLogeado.getUsuario(), codigoUsuarioDestinatario, mensaje);
        subastasQuindio.crearMensaje(codigoUsuarioDestinatario,usuarioLogeado.getUsuario(),codigoUsuarioDestinatario,mensaje);
        crearRegistroLog("El mensaje enviado por "+usuarioLogeado.getUsuario()+" a "+ codigoUsuarioDestinatario+
                        " ha sido creado", 1, "crearMensaje");
        guardarSubastasQuindio();
        guardarRespaldo();
    }
    
    public void crearChat(String UsuarioDestinatario) {
        subastasQuindio.crearChat(usuarioLogeado.getUsuario(),usuarioLogeado.getUsuario(), UsuarioDestinatario);
        subastasQuindio.crearChat(UsuarioDestinatario, UsuarioDestinatario, usuarioLogeado.getUsuario());
        crearRegistroLog("El chat de "+usuarioLogeado.getUsuario()+" a "+ UsuarioDestinatario+" ha sido creado", 1, "crearChat");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    public ArrayList<Chat> obtenerChats(String usuario) {
        return subastasQuindio.obtenerChats(usuario);
    }


    /**
     * Metodo para retornar la lista de productos de un usuario
     * @return lista de productos anunciados
     */
    public ArrayList<Producto> tomarListaProductoUsuario(){
        return subastasQuindio.buscarProductosAnunciante(usuarioLogeado.getUsuario());
    }

    /**
     * Metodo para retornar la lista de pujas de un usuario
     * @param nombreProducto nombre del producto
     * @return lista de pujas
     * @throws BidNotFoundException Si la puja no existe
     * @throws ProductNotFoundException Si el producto no existe
     */
    public ArrayList<Puja> tomarListaPujas(String nombreProducto) throws BidNotFoundException, ProductNotFoundException {
        return subastasQuindio.buscarPujasComprador(usuarioLogeado.getUsuario(), nombreProducto);
    }
    /**
     * Metodo para retornar la lista de pujas de un producto
     * @param nombreProducto nombre del producto
     * @return lista de pujas
     * @throws BidNotFoundException Si la puja no existe
     * @throws ProductNotFoundException Si el producto no existe
     */
    public ArrayList<Puja> tomarListaPujasProducto(String nombreProducto) throws BidNotFoundException, ProductNotFoundException {
        return subastasQuindio.buscarPujasProducto(usuarioLogeado.getUsuario(), nombreProducto);
    }
    /**
     *Metodo para asignar el usuario logeado
     */
    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
    /**
     *Metodo para obtener el usuario logeado
     */

    public Usuario getUsuarioLogeado(){
        return  this.usuarioLogeado;
    }

    /**
     * Tomar la ruta de la image view
     * @return Ruta de la imagen que se va a mostrar en la image view
     */
    public String getRutaImageView() {
        return rutaImageView;
    }

    /**
     * Asignar la ruta de la image view
     * @param rutaImageView Ruta de la imagen que se va a mostrar en la image view
     */
    public void setRutaImageView(String rutaImageView) {
        this.rutaImageView = rutaImageView;
    }
    /**
     *Metodo para obtener el producto de la Pujas view
     */
    public Producto getProductoPujasView() {
        return productoPujasView;
    }
    /**
     *Metodo para asignar el producto  para la Pujas view
     */
    public void setProductoPujasView(Producto productoPujasView) {
        this.productoPujasView = productoPujasView;
    }
    /**
     * Metodo para obtener el Producto por el que se va a pujar
     */
    public Producto getProductoPujar() {
        return productoPujar;
    }
    /**
     * Metodo para asignar el producto para pujar
     */
    public void setProductoPujar(Producto productoPujar) {
        this.productoPujar = productoPujar;
    }
    /**
     * Metodo para obtener el producto del cual se busca editar una puja
     */
    public String getProductoBuscadoEditarPuja() {
        return productoBuscadoEditarPuja;
    }
    /**
     * Metodo para asignar el el producto del cual se busca editar una puja
     */
    public void setProductoBuscadoEditarPuja(String productoBuscadoEditarPuja) {
        this.productoBuscadoEditarPuja = productoBuscadoEditarPuja;
    }
    /**
     *Metodo para obtener la puja que se va a editar
     */
    public Puja getPujaEditar() {
        return pujaEditar;
    }
    /**
     *Metodo para asignar la puja que se va a editar
     */
    public void setPujaEditar(Puja pujaEditar) {
        this.pujaEditar = pujaEditar;
    }
}
