package co.edu.uniquindio.casasubastas.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase de los mensajes de la aplicacion
 */
public class Mensaje implements Serializable {

    /**
     * String con el usuario
     */
    private String usuario;
    /**
     * String con el mensaje
     */
    private String message;
    /**
     * Fecha
     */
    private LocalDateTime fecha;
    /**
     * Booleano para verificar si el mensaje es recibido
     */
    private boolean esRecibido;
    /**
     * Constructor vacío de la clase
     */
    public Mensaje(){}

    /**
     * Método para tomar el usuario
     * @return usuario del log in
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * Método para asignar el usuario
     * @param usuario del log in
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * Método para obtener el mensaje
     * @return Mensaje para mostrar
     */
    public String getMessage() {
        return message;
    }
    /**
     *Método para asignar el mensaje
     * @param message Mensaje a ser mostrado
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     *Método para obtener la fecha
     * @return fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    /**
     *Método para asignar la fecha
     * @param fecha Fecha
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    /**
     * Método para tomar el valor de esRecibido
     * @return esRecibido valor booleano
     */
    public boolean isEsRecibido() {
        return esRecibido;
    }
    /**
     * Método para asignar el valor de esRecibido
     * @param esRecibido valor booleano
     */
    public void setEsRecibido(boolean esRecibido) {
        this.esRecibido = esRecibido;
    }
}
