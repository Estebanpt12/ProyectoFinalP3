package co.edu.uniquindio.casasubastas.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase de los mensajes de la aplicacion
 */
public class Mensaje implements Serializable {

    private String usuario;
    private String message;
    private LocalDateTime fecha;
    private boolean esRecibido;

    public Mensaje(){}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isEsRecibido() {
        return esRecibido;
    }

    public void setEsRecibido(boolean esRecibido) {
        this.esRecibido = esRecibido;
    }
}
