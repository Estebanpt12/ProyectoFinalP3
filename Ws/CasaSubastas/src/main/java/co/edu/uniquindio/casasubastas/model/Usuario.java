package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.model.services.IUsuario;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    /**
     * Usuario del log in
     */
    private String usuario;

    /**
     * Contrasenia del log in
     */
    private String contrasenia;

    /**
     * Nombre del usuario
     */
    private String nombre;

    /**
     * Edad del usuario
     */
    private int edad;

    /**
     * Interfaz para asignar el tipo de usuario
     */
    private IUsuario iUsuario;

    /**
     * Lista donde se guardan los nombre de los productos
     */
    ArrayList<String> listaProductos = new ArrayList<>();

    ArrayList<Mensaje> listaMensajes = new ArrayList<>();

    /**
     * Constructor vacio de la clase
     */
    public Usuario() {}

    /**
     * Metodo para tomar el usuario
     * @return Usuario del log in
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Metodo para asignar el usuario
     * @param usuario Usuario del log in
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo para tomar la contrasenia
     * @return Contrasenia del log in
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Metodo para asignar la contrasenia
     * @param contrasenia Contrasenia del log in
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Metodo para tomar el nombre
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para asignar el nombre
     * @param nombre Nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para tomar la edad
     * @return Edad del usuario
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Metodo para asignar la edad
     * @param edad Edad del usuario
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Metodo para tomar la interfaz del usuario
     * @return Interfaz para asignar el tipo de usuario
     */
    public IUsuario getiUsuario() {
        return iUsuario;
    }

    /**
     * Metodo para asignar la interfaz del usuario
     * @param iUsuario Interfaz para asignar el tipo de usuario
     */
    public void setIUsuario(IUsuario iUsuario) {
        this.iUsuario = iUsuario;
    }

    public void setIUsuario(Anunciante anunciante) {
        this.iUsuario = anunciante;
    }

    public void setIUsuario(Comprador comprador) {
        this.iUsuario = comprador;
    }

    /**
     * Metodo para tomar la lista de productos
     * @return Lista de productos publicados por el anunciante
     */
    public ArrayList<String> getListaProductos() {
        return listaProductos;
    }

    /**
     * Metodo para asignar la lista de productos
     * @param listaProductos Lista de productos publicados por el anunciante
     */
    public void setListaProductos(ArrayList<String> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * Metodo para validar la cantidad limite de productos
     * @return retorna si la cantidad de productos se ha sobrepasado o no
     */
    public boolean validarCantidadProductos(String nombreProducto){
        if(iUsuario instanceof Anunciante){
            return iUsuario.validarCantidadProductos(listaProductos.size());
        }else{
            int auxiliar = 0;
            for( String element : listaProductos){
                if(element.equals(nombreProducto)){
                    auxiliar+=1;
                }
            }
            return iUsuario.validarCantidadProductos(auxiliar);
        }
    }

    /**
     * Metodo para aniadir un producto
     * @param nombre nombre del producto a aniadir
     */
    public void aniadirProducto(String nombre){
        listaProductos.add(nombre);
    }

    /**
     * Metodo para eliminar un producto
     * @param nombre Nombre del producto a eliminar
     */
    public void eliminarProducto(String nombre) {
        listaProductos.remove(nombre);
    }

    public void setiUsuario(IUsuario iUsuario) {
        this.iUsuario = iUsuario;
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
}
