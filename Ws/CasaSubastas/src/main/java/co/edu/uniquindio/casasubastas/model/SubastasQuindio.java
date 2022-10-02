package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.exceptions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class SubastasQuindio {

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
     * Metodo para creear un anunciante
     * @param usuario usuario de la aplicacion del anunciante
     * @param contrasenia contrasenia de la aplicacion del anunciante
     * @param nombre nombre del anunciante
     * @param edad edad del anunciante
     * @throws InvalidUserException Valida si el usuario es mayor de edad
     * @throws ExistingUserException Valida si el usuario ya existe
     */
    public void crearAnunciante(String usuario, String contrasenia, String nombre, int edad) throws InvalidUserException, ExistingUserException{
        if(edad >= 18){
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
        usuario1.setiUsuario(anunciante);
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
        if(edad >= 18){
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
        usuario1.setiUsuario(comprador);
    }

    /**
     * Metodo para crear una puja a un producto
     * @param nombreProducto Nombre del producto al que se le hace la puja
     * @param usuarioPujante Usuario que hace la puja
     * @param valorPujado valor pujado por el usuario
     * @param fecha fecha de la puja
     * @throws UserNotFoundException Se valida si el usuario no existe
     * @throws InvalidUserException Se valida la cantidad de pujas y el tipo de usuario
     * @throws ProductNotFoundException Se valida si el producto no existe
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    public void crearPuja(String nombreProducto, String usuarioPujante, double valorPujado,
                          Date fecha) throws UserNotFoundException, InvalidUserException, ProductNotFoundException, ProductsLimitException {
        for(int i = 0; i<usuarios.size(); i++){
            if(validarPujante(usuarios.get(i), usuarioPujante)){
                usuarios.get(i).getiUsuario().aniadirProducto(nombreProducto);
                break;
            }else if(i == usuarios.size()-1){
                throw new UserNotFoundException("Usuario no encontrado");
            }
        }
        for(int i = 0; i < productos.size(); i++){
            if(productos.get(i).getNombre().equals(nombreProducto)){
                productos.get(i).agregarPuja(usuarioPujante, valorPujado, fecha);
                break;
            }else if(i == productos.size()-1){
                for(Usuario usuario : usuarios){
                    if(usuario.getUsuario().equals(usuarioPujante)) {
                        usuario.getiUsuario().eliminarProducto(nombreProducto);
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
     * @throws InvalidUserException Se valida la cantidad limite de anuncios del usuario y el tipo de usuario
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    public void crearProducto(String tipoProducto, String nombre, String descripcion, File foto, Date fInicio,
                              Date fFin, double vInicial, String nombrePublicante)
            throws UserNotFoundException, InvalidUserException, ProductsLimitException{
        for(int i = 0; i < usuarios.size(); i++){
            if(validarAnunciante(usuarios.get(i), nombrePublicante)){
                usuarios.get(i).getiUsuario().aniadirProducto(nombre);
                break;
            }else if(i == usuarios.size()-1){
                throw new UserNotFoundException("Usuario no encontrado");
            }
        }
        Producto producto = new Producto();
        producto.setTipoProducto(tipoProducto);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setFoto(foto);
        producto.setFechaFin(fInicio);
        producto.setFechaFin(fFin);
        producto.setValorInicial(vInicial);
        producto.setVendido(false);
        productos.add(producto);
    }

    /**
     * Validacion de la puja privada
     * @param usuario Usuario a validar
     * @param usuario1 Usuario de la aplicacion a validar
     * @return Marca si el usuario existe
     * @throws InvalidUserException Se valida la cantidad de pujas y el tipo de usuario
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    private boolean validarPujante(Usuario usuario, String usuario1) throws InvalidUserException, ProductsLimitException{
        if(usuario.getUsuario().equals(usuario1)){
            if(usuario.validarTipoUsuario()){
                if(usuario.validarCantidadProductos()){
                    return true;
                }else{
                    throw new ProductsLimitException("El usuario ha sobrepasado la cantidad limite de pujas");
                }
            }else {
                throw new InvalidUserException("El usuario no es un comprador");
            }
        }
        return false;
    }

    /**
     * Validacion de anunciante privado
     * @param usuario Usuario a validar
     * @param nombrePublicante Nombre del usuario de la aplicacion a validar
     * @return Marca si el usuario existe
     * @throws InvalidUserException Se valida el tipo de usuario
     * @throws ProductsLimitException Se valida la cantidad limite de anuncios del usuario
     */
    private boolean validarAnunciante(Usuario usuario, String nombrePublicante) throws InvalidUserException, ProductsLimitException{
        if(usuario.getNombre().equals(nombrePublicante)){
            if(!usuario.validarTipoUsuario()){
                if(usuario.validarCantidadProductos()){
                    return true;
                }else{
                    throw new ProductsLimitException("El usuario ha sobrepasado la cantidad limite de anuncios");
                }
            }else {
                throw new InvalidUserException("El usuario no es un anunciante");
            }
        }
        return false;
    }
}
