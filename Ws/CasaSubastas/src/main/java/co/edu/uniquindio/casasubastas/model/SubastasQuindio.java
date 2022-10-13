package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.exceptions.*;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SubastasQuindio implements Serializable {

    public static void main(String[] args) {
        SubastasQuindio subastasQuindio = new SubastasQuindio();
        try {
            subastasQuindio.crearAnunciante("penaeste", "1234", "Esteban", 19);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        } catch (ExistingUserException e) {
            e.printStackTrace();
        }
        try {
           subastasQuindio.crearComprador("penaeste1", "1234", "Esteban1", 19);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        } catch (ExistingUserException e) {
            e.printStackTrace();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            subastasQuindio.crearProducto("Electro", "Micro", "prueba",
                                           "co/edu/uniquindio/casasubastas/views/login-view.fxml", localDateTime
                                            , localDateTime.plusMonths(3), 10000, "Esteban");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }  catch (ProductsLimitException e) {
            e.printStackTrace();
        }
        try {
            subastasQuindio.crearPuja("Micro", "penaeste1", 50000, localDateTime);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        } catch (ProductsLimitException e) {
            e.printStackTrace();
        } catch (InsufficientBidException e){
            e.printStackTrace();
        }
        System.out.println("Empresa creada");
    }

    /**
     * Lista usuarios
     */
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    /**
     * Lista productos
     */
    private ArrayList<Producto> productos = new ArrayList<>();

    /**
     * Constructor vacio de la clase
     */
    public SubastasQuindio() {}

    /**
     * Metodo para tomar los usuarios
     * @return Lista usuarios
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Metodo para asignar los usuarios
     * @param usuarios Lista usuarios
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Metodo para tomar los productos
     * @return Lista productos
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * Metodo para asignar los productos
     * @param productos Lista productos
     */
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Metodo para crear un anunciante
     * @param usuario usuario de la aplicacion del anunciante
     * @param contrasenia contrasenia de la aplicacion del anunciante
     * @param nombre nombre del anunciante
     * @param edad edad del anunciante
     * @throws InvalidUserException Valida si el usuario es mayor de edad
     * @throws ExistingUserException Valida si el usuario ya existe
     */
    public void crearAnunciante(String usuario, String contrasenia, String nombre, int edad) throws InvalidUserException, ExistingUserException{
        if(edad < 18){
            throw new InvalidUserException("El usuario no es mayor de edad");
        }
        for (Usuario usuario1: usuarios){
            if (usuario1.getUsuario().equals(usuario)){
                throw new ExistingUserException("El usuario ya existe");
            }
        }
        Usuario usuario1 = new Usuario();
        Anunciante anunciante = new Anunciante();
        usuario1.setUsuario(usuario);
        usuario1.setContrasenia(contrasenia);
        usuario1.setNombre(nombre);
        usuario1.setEdad(edad);
        usuario1.setIUsuario(anunciante);
        usuarios.add(usuario1);
    }

    /**
     * Metodo para crear un comprador
     * @param usuario usuario de la aplicacion del comprador
     * @param contrasenia contrasenia de la aplicacion del comprador
     * @param nombre nombre del comprador
     * @param edad edad del comprador
     * @throws InvalidUserException Valida si el usuario es mayor de edad
     * @throws ExistingUserException Valida si el usuario ya existe
     */
    public void crearComprador(String usuario, String contrasenia, String nombre, int edad) throws InvalidUserException, ExistingUserException{
        if(edad < 18){
            throw new InvalidUserException("El usuario no es mayor de edad");
        }
        for (Usuario usuario1: usuarios){
            if (usuario1.getUsuario().equals(usuario)){
                throw new ExistingUserException("El usuario ya existe");
            }
        }
        Usuario usuario1 = new Usuario();
        Comprador comprador = new Comprador();
        usuario1.setUsuario(usuario);
        usuario1.setContrasenia(contrasenia);
        usuario1.setNombre(nombre);
        usuario1.setEdad(edad);
        usuario1.setIUsuario(comprador);
        usuarios.add(usuario1);
    }

    /**
     * Metodo para crear una puja a un producto
     * @param nombreProducto Nombre del producto al que se le hace la puja
     * @param usuarioPujante Usuario que hace la puja
     * @param valorPujado valor pujado por el usuario
     * @param fecha fecha de la puja
     * @throws UserNotFoundException Se valida si el usuario no existe
     * @throws ProductNotFoundException Se valida si el producto no existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     * @throws InsufficientBidException Se valida si el valor pujado es valido
     */
    public void crearPuja(String nombreProducto, String usuarioPujante, double valorPujado,
                          LocalDateTime fecha) throws UserNotFoundException, ProductNotFoundException,
                            ProductsLimitException, InsufficientBidException {
        for(int i = 0; i<usuarios.size(); i++){
            if(validarPujante(usuarios.get(i), usuarioPujante, nombreProducto)){
                usuarios.get(i).aniadirProducto(nombreProducto);
                break;
            }else if(i == usuarios.size()-1){
                throw new UserNotFoundException("Usuario no encontrado");
            }
        }
        for(int i = 0; i < productos.size(); i++){
            if(productos.get(i).getNombre().equals(nombreProducto)){
                if(productos.get(i).getValorInicial() <= valorPujado){
                    productos.get(i).agregarPuja(usuarioPujante, valorPujado, fecha);
                    break;
                }else{
                    for(Usuario usuario : usuarios){
                        if(usuario.getUsuario().equals(usuarioPujante)) {
                            usuario.eliminarProducto(nombreProducto);
                        }
                    }
                    throw new InsufficientBidException("Valor pujado es menor al valor inicial");
                }
            }else if(i == productos.size()-1){
                for(Usuario usuario : usuarios){
                    if(usuario.getUsuario().equals(usuarioPujante)) {
                        usuario.eliminarProducto(nombreProducto);
                    }
                }
                throw new ProductNotFoundException("Producto no encontrado");
            }
        }
    }

    /**
     * Metodo para crear un producto
     * @param tipoProducto tipo de producto de la casa de subastas
     * @param nombre nombre del producto de la casa de subastas
     * @param descripcion descripcion del producto de la casa de subastas
     * @param foto foto del producto de la casa de subastas
     * @param fInicio fecha de publicacion del producto de la casa de subastas
     * @param fFin fecha donde se termina la publicacion del producto de la casa de subastas
     * @param vInicial valor inicial de la subasta del producto de la casa de subastas
     * @param nombrePublicante nombre del publicante del producto
     * @throws UserNotFoundException Se valida si el usuario existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    public void crearProducto(String tipoProducto, String nombre, String descripcion, String foto, LocalDateTime fInicio,
                              LocalDateTime fFin, double vInicial, String nombrePublicante)
            throws UserNotFoundException, ProductsLimitException{
        for(int i = 0; i < usuarios.size(); i++){
            if(validarAnunciante(usuarios.get(i), nombrePublicante)){
                usuarios.get(i).aniadirProducto(nombre);
                break;
            }else if(i == usuarios.size()-1){
                throw new UserNotFoundException("Usuario no encontrado");
            }
        }
        Producto producto = new Producto();
        producto.setTipoProducto(tipoProducto);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setRutaFoto(foto);
        producto.setFechaInicio(fInicio);
        producto.setFechaFin(fFin);
        producto.setValorInicial(vInicial);
        producto.setVendido(false);
        productos.add(producto);
    }

    /**
     * Validacion de la puja privada
     * @param usuario Usuario a validar
     * @param usuario1 Usuario de la aplicacion a validar
     * @param nombreProducto Nombre del producto a validar
     * @return Marca si el usuario existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    private boolean validarPujante(Usuario usuario, String usuario1, String nombreProducto) throws ProductsLimitException{
        if(usuario.getUsuario().equals(usuario1)){
            if(usuario.getiUsuario() instanceof Comprador){
                if(usuario.validarCantidadProductos(nombreProducto)){
                    return true;
                }else{
                    throw new ProductsLimitException("El usuario ha sobrepasado la cantidad limite de pujas");
                }
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * Validacion de anunciante privado
     * @param usuario Usuario a validar
     * @param nombrePublicante Nombre del usuario de la aplicacion a validar
     * @return Marca si el usuario existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    private boolean validarAnunciante(Usuario usuario, String nombrePublicante) throws ProductsLimitException{
        if(usuario.getNombre().equals(nombrePublicante)){
            if(usuario.getiUsuario() instanceof Anunciante){
                if(usuario.validarCantidadProductos(null)){
                    return true;
                }else{
                    throw new ProductsLimitException("El usuario ha sobrepasado la cantidad limite de anuncios");
                }
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * Metodo para editar un producto
     * @param tipoProducto tipo de producto de la casa de subastas
     * @param nombre nombre del producto de la casa de subastas
     * @param descripcion descripcion del producto de la casa de subastas
     * @param foto foto del producto de la casa de subastas
     * @param fInicio fecha de publicacion del producto de la casa de subastas
     * @param fFin fecha donde se termina la publicacion del producto de la casa de subastas
     * @param vInicial valor inicial de la subasta del producto de la casa de subastas
     * @throws ProductNotFoundException Se valida si el producto existe
     */
    public void editarProducto(String tipoProducto, String nombre, String descripcion, String foto, LocalDateTime fInicio,
                               LocalDateTime fFin, double vInicial) throws ProductNotFoundException {
        for(int i = 0; i< productos.size(); i++){
            if(productos.get(i).getNombre().equals(nombre)){
                productos.get(i).setTipoProducto(tipoProducto);
                productos.get(i).setNombre(nombre);
                productos.get(i).setDescripcion(descripcion);
                productos.get(i).setRutaFoto(foto);
                productos.get(i).setFechaInicio(fInicio);
                productos.get(i).setFechaFin(fFin);
                productos.get(i).setValorInicial(vInicial);
                productos.get(i).setVendido(false);
            }else if(i == productos.size()-1){
                throw new ProductNotFoundException("El producto no ha sido encontrado");
            }
        }
    }

    /**
     * Metodo para eliminar un producto
     * @param tipoProducto tipo de producto de la casa de subastas
     * @param nombre nombre del producto de la casa de subastas
     * @return producto eliminado
     * @throws ProductNotFoundException Se valida si el producto existe
     */
    public Producto eliminarProducto(String tipoProducto, String nombre) throws ProductNotFoundException {
        Producto producto = new Producto();
        for(int i = 0; i<productos.size(); i++){
            if(productos.get(i).getNombre().equals(nombre) && productos.get(i).getTipoProducto().equals(tipoProducto)){
                producto = productos.get(i);
                productos.remove(i);
            }else if(i == productos.size()-1){
                throw new ProductNotFoundException("Producto no encontrado");
            }
        }
        for(int i = 0; i<usuarios.size(); i++){
            for(int j = 0; j<usuarios.size(); j++){
                if(usuarios.get(i).getListaProductos().get(j).equals(producto.getNombre()) && usuarios.get(i).getiUsuario() instanceof  Anunciante){
                    usuarios.get(i).getListaProductos().remove(j);
                }
            }
        }
        return producto;
    }

    /**
     * Metodo para editar una puja
     * @param nombreProducto Nombre del producto al que se le hace la puja
     * @param usuarioPujante Usuario que hace la puja
     * @param valorPujado valor pujado por el usuario
     * @param fecha fecha de la puja
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws BidNotFoundException Se valida si la puja existe
     * @throws InsufficientBidException Se valida si la puja hecha es mayor al valor inicial del producto
     */
    public void editarPuja(String nombreProducto, String usuarioPujante, double valorPujado,
                           LocalDateTime fecha) throws ProductNotFoundException, BidNotFoundException, InsufficientBidException {
        for(int i = 0; i<productos.size(); i++){
            if(productos.get(i).getNombre().equals(nombreProducto)){
                for(int j = 0; j<productos.get(i).getListaPuja().size(); i++){
                    if(productos.get(i).getListaPuja().get(j).getUsuario().equals(usuarioPujante)
                        && productos.get(i).getListaPuja().get(j).getFecha() == fecha){
                        if(productos.get(i).getValorInicial() <= valorPujado){
                            productos.get(i).getListaPuja().get(j).setValor(valorPujado);
                        }else {
                            throw new InsufficientBidException("El valor pujado es menor al valor inicial del producto");
                        }
                    }else if(j == productos.get(i).getListaPuja().size()-1){
                        throw new BidNotFoundException("La puja no ha sido encontrada");
                    }
                }
                break;
            }else if(i == productos.size()-1){
                throw new ProductNotFoundException("El producto no ha sido encontrado");
            }
        }
    }

    /**
     * Metodo para borrar una puja
     * @param nombreProducto Nombre del producto al que se le hace la puja
     * @param usuarioPujante Usuario que hace la puja
     * @return puja eliminada
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws BidNotFoundException Se valida si la puja existe
     */
    public Puja eliminarPuja(String nombreProducto, String usuarioPujante) throws ProductNotFoundException, BidNotFoundException {
        Puja puja = new Puja();
        for(int i = 0; i<productos.size(); i++){
            if(productos.get(i).getNombre().equals(nombreProducto)){
                for(int j = 0; j<productos.get(i).getListaPuja().size(); j++){
                    if(productos.get(i).getListaPuja().get(j).getUsuario().equals(usuarioPujante)){
                        puja = productos.get(i).getListaPuja().get(j);
                        productos.get(i).getListaPuja().remove(j);
                    }else if(j == productos.get(i).getListaPuja().size()-1){
                        throw new BidNotFoundException("La puja no ha sido encontrada");
                    }
                }
            }else if(i == productos.size()-1){
                throw new ProductNotFoundException("Producto no encontrado");
            }
        }
        for (Usuario usuario : usuarios) {
            for (int j = 0; j < usuario.getListaProductos().size(); j++) {
                if (usuario.getListaProductos().get(j).equals(nombreProducto) && usuario.getiUsuario() instanceof Comprador) {
                    usuario.getListaProductos().remove(j);
                }
            }
        }
        return puja;
    }
}
