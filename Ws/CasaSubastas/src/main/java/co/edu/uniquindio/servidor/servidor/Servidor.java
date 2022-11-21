package co.edu.uniquindio.servidor.servidor;

import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.Producto;
import co.edu.uniquindio.casasubastas.model.Puja;
import co.edu.uniquindio.casasubastas.model.SubastasQuindio;
import co.edu.uniquindio.casasubastas.model.Usuario;
import co.edu.uniquindio.servidor.persistencia.Persistencia;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {

    String host = "localhost";
	int puerto = 8081;
	ServerSocket server;
	
	Socket socketComunicacion;
	
	DataOutputStream flujoSalida;
	DataInputStream flujoEntrada;
	BufferedReader entrada;
	
	String mensajeCliente;

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
	
	public Servidor() {
		
	}
	
	public void iniciarServidor() throws BidNotFoundException, ProductNotFoundException {
		
		
		try {
			// Se crea un socket servidor atendiendo a un determinado puerto.
			server = new ServerSocket(puerto);

			while(true)
			{
				System.out.println ("Esperando cliente");
				socketComunicacion = server.accept();

                flujoSalidaObjeto = new ObjectOutputStream(socketComunicacion.getOutputStream());
                flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
                flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
                flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
                recibirDatos();
                enviarDatos();
                flujoSalida.close();
                flujoEntrada.close();
                flujoSalidaObjeto.close();
                flujoEntradaObjeto.close();
                socketComunicacion.close();

				// Se cierra el socket encargado de aceptar clientes. Ya no
				// queremos m�s.
				//		            server.close();
			}

		} catch (IOException | InterruptedException | ClassNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
		}
	}
    private void recibirDatos() throws IOException, InterruptedException, ClassNotFoundException, UserNotFoundException, BidNotFoundException, ProductNotFoundException {
        int tipoPeticion = flujoEntrada.readInt();
        if(tipoPeticion == 1){
            cargarDatosDesdeArchivos();
            flujoSalidaObjeto.writeObject(getSubastasQuindio());
        }
        if(tipoPeticion == 2){
            cargarResourceBinario();
            flujoSalidaObjeto.writeObject(getSubastasQuindio());
        }
        if(tipoPeticion == 3){
            cargarResourceXML();
            flujoSalidaObjeto.writeObject(getSubastasQuindio());
        }
        if(tipoPeticion == 4){
            subastasQuindio = (SubastasQuindio) flujoEntradaObjeto.readObject();
            setSubastasQuindio(subastasQuindio);
            guardarResourceBinario();
        }
        if(tipoPeticion == 5){
            subastasQuindio = (SubastasQuindio) flujoEntradaObjeto.readObject();
            guardarResourceXML();
        }
        if(tipoPeticion == 6){
            subastasQuindio = (SubastasQuindio) flujoEntradaObjeto.readObject();
            guardarRespaldo();
        }
        if(tipoPeticion == 7){
            subastasQuindio = (SubastasQuindio) flujoEntradaObjeto.readObject();
            guardarSubastasQuindio();
        }
        if(tipoPeticion == 8){
            String log = flujoEntrada.readUTF();
            String mensajeLog = null;
            int nivel = 0;
            String accion = null;
            Scanner scanner = new Scanner(log);
                scanner.useDelimiter(",");
                while (scanner.hasNext()){
                    mensajeLog = scanner.next();
                    nivel = Integer.parseInt(scanner.next());
                    accion = scanner.next();
                }
            crearRegistroLog(mensajeLog, nivel, accion);
        }
        if(tipoPeticion == 9){
            String crendenciales = flujoEntrada.readUTF();
            String usuario = null;
            String contrasenia = null;
            Scanner scanner = new Scanner(crendenciales);
                scanner.useDelimiter(",");
                while (scanner.hasNext()){
                    usuario = scanner.next();
                    contrasenia = scanner.next();
                }
            this.usuario = iniciarSesion(usuario, contrasenia);
            flujoSalidaObjeto.writeObject(this.usuario);
        }
        if(tipoPeticion == 10) {
            puja = (Puja)flujoEntradaObjeto.readObject();
            nombreProducto = flujoEntrada.readUTF();
            eliminarPuja(nombreProducto,puja);
        }
        if(tipoPeticion == 11){
            producto = (Producto)flujoEntradaObjeto.readObject();
            eliminarProducto(producto.getTipoProducto(),producto.getNombre());
        }
    }

    public void enviarDatos()throws IOException {
        
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

    /**
     *Metodo para cargar los datos desde los archivos
     * @throws InterruptedException
     */
    public void cargarDatosDesdeArchivos() throws InterruptedException {
        subastasQuindio = new SubastasQuindio();
        try {
            subastasQuindio.setProductos(Persistencia.cargarProductos());
            subastasQuindio.setUsuarios(Persistencia.cargarUsuarios());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar el recurso binario
     */
    public void cargarResourceBinario() {
        subastasQuindio = Persistencia.cargarRecursoCasaBinario();
    }
    /**
     *Metodo para guardar el resource binario
     */
    public void guardarResourceBinario() {
        Persistencia.guardarRecursoCasaBinario(subastasQuindio);
    }
    /**
     *Metodo para cargar el recurso XML
     */
    public void cargarResourceXML() {
        subastasQuindio = Persistencia.cargarRecursoCasaXML();
    }
    /**
     *Metodo para guardar recurso XML
     */
    public void guardarResourceXML() {
        Persistencia.guardarRecursoCasaXML(subastasQuindio);
    }

    /**
     *Metodo para guardar el respaldo
     * @throws InterruptedException
     */
    public void guardarRespaldo() throws InterruptedException{
        try {
            Persistencia.guardarUsuariosRespaldo(subastasQuindio.getUsuarios());
            Persistencia.guardarProductosRespaldo(subastasQuindio.getProductos());
            Persistencia.guardarRecursoCasaBinarioRespaldo(subastasQuindio);
            Persistencia.guardarRecursoCasaXMLRespaldo(subastasQuindio);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     *Metodo para guardar la casa de subastas
     * @throws InterruptedException
     */
    public void guardarSubastasQuindio() throws InterruptedException{
        try {
            Persistencia.guardarProductos(subastasQuindio.getProductos());
            Persistencia.guardarUsuarios(subastasQuindio.getUsuarios());
            Persistencia.guardarRecursoCasaXML(subastasQuindio);
            Persistencia.guardarRecursoCasaBinario(subastasQuindio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Metodo para crear el registro en el log
     * @param mensajeLog Mensaje que se guardará en el log
     * @param nivel Nivel del registro que quedará en el log
     * @param accion Accion realizada que se guardará en el log
     * @throws InterruptedException
     */
    public void crearRegistroLog(String mensajeLog, int nivel, String accion) throws InterruptedException{
        Persistencia.guardaRegistroLog(mensajeLog, nivel, accion);
    }
    /**
     *Metodo para iniciar sesion
     * @param usuario Usuario del log in
     * @param contrasenia Contrasenia del usuario
     * @throws UserNotFoundException Se valida si el usuario existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     * @throws InterruptedException
     */
    public Usuario iniciarSesion(String usuario, String contrasenia) throws UserNotFoundException, IOException, InterruptedException {
        try {
            this.usuario = Persistencia.iniciarSesion(usuario, contrasenia);
        } catch (UserNotFoundException e) {
            return null;
        }
        crearRegistroLog("El usuario "+ usuario +" ha iniciado sesion", 1, "Login");
        return this.usuario;
    }

    /**
     * Metodo para eliminar una puja
     * @param nombreProducto Nombre del producto
     * @throws BidNotFoundException Se valida si la puja existe
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     * @throws InterruptedException
     */
    public void eliminarPuja(String nombreProducto, Puja p) throws BidNotFoundException, ProductNotFoundException, IOException, InterruptedException {
        Puja puja = subastasQuindio.eliminarPuja(nombreProducto, p);
        Persistencia.guardarPujaEliminada(puja, nombreProducto);
        crearRegistroLog("El usuario "+this.usuario.getUsuario()+" ha eliminado la puja a el producto "
                        +nombreProducto+" con valor de"+puja.getValor()+" hecha en la fecha "+ puja.getFecha(), 2,
                "Eliminar puja");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    /**
     *Metodo para eliminar un producto
     * @param tipoProducto Tipo del producto
     * @param nombre Nombre del producto
     * @throws ProductNotFoundException Se valida si el producto existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     * @throws InterruptedException
     */
    public void eliminarProducto(String tipoProducto, String nombre) throws ProductNotFoundException, IOException, InterruptedException {
        Producto producto = subastasQuindio.eliminarProducto(tipoProducto, nombre);
        Persistencia.guardarProductoEliminado(producto);
        crearRegistroLog("El producto "+nombre+" que pertenece al tipo "+tipoProducto+" ha sido eliminado", 2,
                "Eliminar producto");
        guardarSubastasQuindio();
        guardarRespaldo();
    }

    /**
     * Metodo para retornar la lista de productos de un usuario
     * @return lista de productos anunciados
     */
    public ArrayList<Producto> tomarListaProductoUsuario(){
        return subastasQuindio.buscarProductosAnunciante(this.usuario.getUsuario());
    }


}
