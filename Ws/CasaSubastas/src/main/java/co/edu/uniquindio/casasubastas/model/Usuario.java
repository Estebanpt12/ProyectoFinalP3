package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.services.IUsuario;

public class Usuario {

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
    public void setiUsuario(IUsuario iUsuario) {
        this.iUsuario = iUsuario;
    }

    /**
     * Metodo para validar la cantidad limite de productos
     * @return retorna si la cantidad de productos se ha sobrepasado o no
     */
    public boolean validarCantidadProductos(){
        return iUsuario.validarCantidadProductos();
    }

    /**
     * Metodo para validar el tipo de usuario
     * @return retorna true si el usuario es comprador y false si el usuario es anunciante
     */
    public boolean validarTipoUsuario(){
        return  iUsuario.validarTipoUsuario();
    }
}
