package co.edu.uniquindio.casasubastas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable{

    /**
     * String con el usuario que tiene el chat
     */
    private String usuario;

    /**
     * String con el usuario remitente
     */
    private String usuarioRemitente;

    /**
     * String con el usuario destinatario
     */
    private String usuarioDestinatario;

    /**
     *Lista donde se guardan los mensajes
     */
    private ArrayList<Mensaje> listaMensajes = new ArrayList<>();

    public Chat() {
    }

    public Chat(String usuario, String usuarioRemitente, String usuarioDestinatario) {
        this.usuario = usuario;
        this.usuarioRemitente = usuarioRemitente;
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public String getUsuarioRemitente() {
        return usuarioRemitente;
    }

    public void setUsuarioRemitente(String usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }

    public String getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(String usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
