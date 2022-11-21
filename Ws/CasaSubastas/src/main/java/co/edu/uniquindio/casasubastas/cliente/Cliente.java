package co.edu.uniquindio.casasubastas.cliente;

import co.edu.uniquindio.casasubastas.model.Producto;
import co.edu.uniquindio.casasubastas.model.Puja;
import co.edu.uniquindio.casasubastas.model.SubastasQuindio;
import co.edu.uniquindio.casasubastas.model.Usuario;

import java.io.*;
import java.net.Socket;

public class Cliente {

    String host;
    int puerto;
    Socket socketComunicacion;
    DataOutputStream flujoSalida;
    DataInputStream flujoEntrada;
    ObjectOutputStream flujoSalidaObjeto;
    ObjectInputStream flujoEntradaObjeto;
    int tipoPeticion;
    SubastasQuindio subastasQuindio;
    String log;
    Usuario usuario;
    String crendenciales;
    Puja puja;
    String nombreProducto;
    Producto producto;

    public Cliente(int tipoPeticion) {
        this.puerto = 8081;
        this.host = "localhost";
        this.tipoPeticion = tipoPeticion;
        subastasQuindio = new SubastasQuindio();
        usuario = new Usuario();
        puja = new Puja();
        producto = new Producto();
    }

    public void iniciarCliente() {
        try{
            crearConexion();

            flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
            flujoSalidaObjeto = new ObjectOutputStream(socketComunicacion.getOutputStream());
            flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
            flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
            enviarDatosPrimitivos();
            recibirDatos();
            flujoSalida.close();
            flujoEntrada.close();
            flujoSalidaObjeto.close();
            flujoEntradaObjeto.close();
            socketComunicacion.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recibirDatos()throws IOException {
        if(tipoPeticion <= 3){
            try {
                subastasQuindio = (SubastasQuindio) flujoEntradaObjeto.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(tipoPeticion == 9){
            try {
                usuario = (Usuario) flujoEntradaObjeto.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void enviarDatosPrimitivos()throws IOException {
        if(tipoPeticion <= 3){
            flujoSalida.writeInt(tipoPeticion);
        }
        if(tipoPeticion > 3 && tipoPeticion <= 7){
            flujoSalida.writeInt(tipoPeticion);
            flujoSalidaObjeto.writeObject(subastasQuindio);
        }
        if(tipoPeticion == 8){
            flujoSalida.writeInt(tipoPeticion);
            flujoSalida.writeUTF(log);
        }
        if(tipoPeticion == 9){
            flujoSalida.writeInt(tipoPeticion);
            flujoSalida.writeUTF(crendenciales);
        }
        if(tipoPeticion == 10) {
            flujoSalida.writeInt(tipoPeticion);
            flujoSalidaObjeto.writeObject(puja);
            flujoSalida.writeUTF(nombreProducto);
        }
        if(tipoPeticion == 11){
            flujoSalida.writeInt(tipoPeticion);
            flujoSalidaObjeto.writeObject(producto);
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void crearConexion()throws IOException {
        socketComunicacion = new Socket(host, puerto);
    }

    public SubastasQuindio getSubastasQuindio() {
        return subastasQuindio;
    }

    public void setSubastasQuindio(SubastasQuindio subastasQuindio) {
        this.subastasQuindio = subastasQuindio;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCrendenciales() {
        return crendenciales;
    }

    public void setCrendenciales(String crendenciales) {
        this.crendenciales = crendenciales;
    }

    public Puja getPuja() {
        return puja;
    }

    public void setPuja(Puja puja) {
        this.puja = puja;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
