package co.edu.uniquindio.servidor.persistencia;

import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Persistencia {

    /*public static void main(String[] args) throws IOException {
        SubastasQuindio subastasQuindio = new SubastasQuindio();
        try {
            subastasQuindio.crearAnunciante("penaeste", "1234", "Esteban", 19);
            subastasQuindio.crearAnunciante("penaeste2", "1233", "wsad", 20);
            subastasQuindio.crearAnunciante("penaeste3", "1232", "per", 18);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        } catch (ExistingUserException e) {
            e.printStackTrace();
        }
        try {
            subastasQuindio.crearComprador("penaeste1", "1234", "Esteban1", 19);
            subastasQuindio.crearComprador("penaeste4", "45", "Esteban12", 41);
            subastasQuindio.crearComprador("penaeste5", "675", "Esteban13", 39);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        } catch (ExistingUserException e) {
            e.printStackTrace();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            subastasQuindio.crearProducto("Electro", "Micro", "prueba",
                    "co/edu/uniquindio/casasubastas/views/login-view.fxml", localDateTime
                    , localDateTime.plusYears(1), 10000, "Esteban");
            subastasQuindio.crearProducto("Pro", "Jpa", "prueba",
                    "co/edu/uniquindio/casasubastas/views/login-view.fxml", localDateTime
                    , localDateTime.plusMonths(6), 20000, "per");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }  catch (ProductsLimitException e) {
            e.printStackTrace();
        }
        try {
            subastasQuindio.crearPuja("Micro", "penaeste1", 50000, localDateTime);
            subastasQuindio.crearPuja("Micro", "penaeste1", 250000, localDateTime);
            subastasQuindio.crearPuja("Jpa", "penaeste5", 50000, localDateTime);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        } catch (ProductsLimitException e) {
            e.printStackTrace();
        } catch (InsufficientBidException e){
            e.printStackTrace();
        }
        Persistencia persistencia = new Persistencia();
        try {
            persistencia.guardarUsuarios(subastasQuindio.getUsuarios());
            persistencia.guardarProductos(subastasQuindio.getProductos());
            guardarRecursoCasaBinario(subastasQuindio);
            guardarRecursoCasaBinario(subastasQuindio);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Usuario> l = new ArrayList<>();
        ArrayList<Producto> p = new ArrayList<>();
        try {
            l = persistencia.cargarUsuarios();
            p = persistencia.cargarProductos();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            guardarProductoEliminado(subastasQuindio.getProductos().get(0));
            guardarProductoAnuncianteEliminado("123", "wasd");
            guardarPujaCompradorEliminado("123", "wasd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubastasQuindio subastasQuindio1 = cargarRecursoCasaBinario();
    }*/

    /**
     * Ruta del archivo de los usuarios compradores
     */
    /**
     * Ruta del archivo de los usuarios compradores
     */
    public static final String RUTA_ARCHIVO_COMPRADORES = "C:\\td\\persistencia\\archivos\\Compradores.txt";

    /**
     * Ruta del archivo de los usuarios anunciantes
     */
    public static final String RUTA_ARCHIVO_ANUNCIANTES = "C:\\td\\persistencia\\archivos\\Anunciantes.txt";

    /**
     * Ruta del archivo de los productos
     */
    public static final String RUTA_ARCHIVO_PRODUCTOS = "C:\\td\\persistencia\\archivos\\Productos.txt";

    /**
     * Ruta del archivo de las pujas de los productos
     */
    public static final String RUTA_ARCHIVO_PUJAS = "C:\\td\\persistencia\\archivos\\PujasProductos.txt";

    /**
     * Ruta del archivo de los productos de los usuarios anunciantes
     */
    public static final String RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES  = "C:\\td\\persistencia\\archivos\\ProductosAnunciantes.txt";

    /**
     * Ruta del archivo de los productos pujados de los usuarios compradores
     */
    public static final String RUTA_ARCHIVO_PUJAS_COMPRADORES  = "C:\\td\\persistencia\\archivos\\PujasCompradores.txt";

    /**
     * Ruta del archivo de los productos eliminados
     */
    public static final String RUTA_ARCHIVO_PRODUCTOS_ELIMINADOS = "C:\\td\\persistencia\\archivos\\eliminados\\Productos.txt";

    /**
     * Ruta del archivo de las pujas de los productos eliminados
     */
    public static final String RUTA_ARCHIVO_PUJAS_ELIMINADOS = "C:\\td\\persistencia\\archivos\\eliminados\\PujasProductos.txt";

    /**
     * Ruta del archivo de los productos de los usuarios anunciantes eliminados
     */
    public static final String RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES_ELIMINADOS  = "C:\\td\\persistencia\\archivos\\eliminados\\ProductosAnunciantes.txt";

    /**
     * Ruta del archivo de los productos pujados de los usuarios compradores eliminados
     */
    public static final String RUTA_ARCHIVO_PUJAS_COMPRADORES_ELIMINADOS  = "C:\\td\\persistencia\\archivos\\eliminados\\PujasCompradores.txt";

    /**
     * Ruta del archivo log del proyecto
     */
    public static final String RUTA_ARCHIVO_LOG = "C:\\td\\persistencia\\log\\CasaSubastas_Log.txt";

    /**
     * Ruta del archivo serializado binario del modelo
     */
    public static final String RUTA_ARCHIVO_MODELO_CASA_BINARIO = "C:\\td\\persistencia\\model.dat";

    /**
     * Ruta del archivo serializado XML del modelo
     */
    public static final String RUTA_ARCHIVO_MODELO_CASA_XML = "C:\\td\\persistencia\\model.xml";

    /**
     * Ruta para los archivos de respaldo
     */
    public static final String RUTA_ARCHIVOS_RESPALDO = "C:\\td\\persistencia\\respaldo\\";

    /**
     * Ruta del archivo donde se guardan los mensajes
     */
    public static final String RUTA_ARCHIVO_MENSAJES = "C:\\td\\persistencia\\archivos\\Mensajes.txt";

    /**
     * Ruta del archivo donde se guardan los chats
     */
    public static final String RUTA_ARCHIVO_CHATS = "C:\\td\\persistencia\\archivos\\Chats.txt";

    /**
     * Método para guardar una lista de usuarios sin importar si es comprador o anunciante
     * @param listaUsuarios Lista de usuarios a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException, InterruptedException {
        String contenidoCompradores = "";
        String contenidoAnunciantes = "";
        String contenidoPujas = "";
        String contenidoProductos = "";
        String contenidoChats = "";
        String contenidoMensajes = "";
        for(Usuario usuario : listaUsuarios){
            if(usuario.getiUsuario() instanceof Anunciante){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoProductos += usuario.getUsuario()+"@@"+producto+"\n";
                    }
                }
                contenidoAnunciantes += usuario.getUsuario()+"@@"+usuario.getNombre()+"@@"
                        +usuario.getContrasenia()+"@@"+usuario.getEdad()+"\n";
            }
            if(usuario.getiUsuario() instanceof Comprador){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoPujas += usuario.getUsuario()+"@@"+producto+"\n";
                    }
                }
                contenidoCompradores += usuario.getUsuario()+"@@"+usuario.getNombre()+"@@"
                        +usuario.getContrasenia()+"@@"+usuario.getEdad()+"\n";
            }
            if(usuario.getListaChats() != null){
                for(Chat chat : usuario.getListaChats()){
                    contenidoChats += chat.getUsuario()+"@@"+chat.getUsuarioRemitente()+"@@"+chat.getUsuarioDestinatario()+"\n";
                    if(chat.getListaMensajes() != null){
                        for(Mensaje mensaje : chat.getListaMensajes()){
                            contenidoMensajes += mensaje.getUsuario()+"@@"+mensaje.getUsuarioRemitente()+"@@"+mensaje.getUsuarioDestinatario()+"@@"+mensaje.getMessage()+"@@"+mensaje.getFecha()+"\n";
                        }
                    }
                    
                }
            }
        }

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenidoAnunciantes, false);
        GuardarArchivo hiloGuardarArchivo2 = new GuardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenidoCompradores, false);
        GuardarArchivo hiloGuardarArchivo3 = new GuardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES, contenidoProductos, false);
        GuardarArchivo hiloGuardarArchivo4 = new GuardarArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES, contenidoPujas, false);
        GuardarArchivo hiloGuardarArchivo5 = new GuardarArchivo(RUTA_ARCHIVO_MENSAJES, contenidoMensajes, false);
        GuardarArchivo hiloGuardarArchivo6 = new GuardarArchivo(RUTA_ARCHIVO_CHATS, contenidoChats, false);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();
        hiloGuardarArchivo2.start();
        hiloGuardarArchivo2.join();
        hiloGuardarArchivo3.start();
        hiloGuardarArchivo3.join();
        hiloGuardarArchivo4.start();
        hiloGuardarArchivo4.join();
        hiloGuardarArchivo5.start();
        hiloGuardarArchivo5.join();
        hiloGuardarArchivo6.start();
        hiloGuardarArchivo6.join();



        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenidoAnunciantes, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenidoCompradores, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES, contenidoProductos, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES, contenidoPujas, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_MENSAJES, contenidoMensajes, false);
        //ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CHATS, contenidoChats, false);
    }


    /**
     * Método para guardar una lista de usuarios en el archivo de respaldo
     * @param arrayList Lista de usuarios a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarUsuariosRespaldo(ArrayList<Usuario> arrayList) throws IOException, InterruptedException {
        String contenidoCompradores = "";
        String contenidoAnunciantes = "";
        String contenidoPujas = "";
        String contenidoProductos = "";
        String contenidoChats = "";
        String contenidoMensajes = "";
        for(Usuario usuario : arrayList){
            if(usuario.getiUsuario() instanceof Anunciante){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoProductos += usuario.getUsuario()+"@@"+producto+"\n";
                    }
                }
                contenidoAnunciantes += usuario.getUsuario()+"@@"+usuario.getNombre()+"@@"
                        +usuario.getContrasenia()+"@@"+usuario.getEdad()+"\n";
            }
            if(usuario.getiUsuario() instanceof Comprador){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoPujas += usuario.getUsuario()+"@@"+producto+"\n";
                    }
                }
                contenidoCompradores += usuario.getUsuario()+"@@"+usuario.getNombre()+"@@"
                        +usuario.getContrasenia()+"@@"+usuario.getEdad()+"\n";
            }
            if(usuario.getListaChats() != null){
                for(Chat chat : usuario.getListaChats()){
                    contenidoChats += chat.getUsuario()+"@@"+chat.getUsuarioRemitente()+"@@"+chat.getUsuarioDestinatario()+"\n";
                    if(chat.getListaMensajes() != null){
                        for(Mensaje mensaje : chat.getListaMensajes()){
                            contenidoMensajes += mensaje.getUsuario()+"@@"+mensaje.getUsuarioRemitente()+"@@"+mensaje.getUsuarioDestinatario()+"@@"+mensaje.getMessage()+"@@"+mensaje.getFecha();
                        }
                    }
                    
                }
            }
        }

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Anunciantes")+".txt",
        contenidoAnunciantes, false);
        GuardarArchivo hiloGuardarArchivo2 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Compradores")+".txt",
        contenidoCompradores, false);
        GuardarArchivo hiloGuardarArchivo3 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("ProductosAnunciantes")+".txt",
        contenidoProductos, false);
        GuardarArchivo hiloGuardarArchivo4 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("PujasCompradores")+".txt",
        contenidoPujas, false);
        GuardarArchivo hiloGuardarArchivo5 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Mensajes")+".txt",
        contenidoMensajes, false);
        GuardarArchivo hiloGuardarArchivo6 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Chats")+".txt",contenidoChats, false);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();
        hiloGuardarArchivo2.start();
        hiloGuardarArchivo2.join();
        hiloGuardarArchivo3.start();
        hiloGuardarArchivo3.join();
        hiloGuardarArchivo4.start();
        hiloGuardarArchivo4.join();
        hiloGuardarArchivo5.start();
        hiloGuardarArchivo5.join();
        hiloGuardarArchivo6.start();
        hiloGuardarArchivo6.join();


        //ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Chats")+".txt",contenidoChats, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Anunciantes")+".txt",
        //         contenidoAnunciantes, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Compradores")+".txt",
        //         contenidoCompradores, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("ProductosAnunciantes")+".txt",
        //         contenidoProductos, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("PujasCompradores")+".txt",
        //         contenidoPujas, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Mensajes")+".txt",
        //         contenidoMensajes, false);
    }

    /**
     * Método para guardar una lista de productos y sus pujas
     * @param listaProductos Lista de productos a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarProductos(ArrayList<Producto> listaProductos) throws IOException, InterruptedException {
        String contenidoProductos = "";
        String contenidoPujas = "";
        for(Producto producto : listaProductos){
            if(producto.getListaPuja() != null){
                for(Puja puja : producto.getListaPuja()){
                    contenidoPujas += producto.getNombre()+"@@"+puja.getUsuario()+"@@"
                            +puja.getFecha()+"@@"+puja.getValor()+"\n";
                }
            }
            contenidoProductos += producto.getNombre()+"@@"+producto.getTipoProducto()+"@@"+
                    producto.getValorInicial()+"@@"+producto.getDescripcion()+"@@"+producto.getFechaInicio()+"@@"+
                    producto.getFechaFin()+"@@"+producto.isVendido()+"@@"+producto.getRutaFoto()+"\n";
        }

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenidoProductos, false);
        GuardarArchivo hiloGuardarArchivo2 = new GuardarArchivo(RUTA_ARCHIVO_PUJAS, contenidoPujas, false);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();
        hiloGuardarArchivo2.start();
        hiloGuardarArchivo2.join();

        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenidoProductos, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS, contenidoPujas, false);

    }

    /**
     * Método para guardar un producto y sus pujas eliminados
     * @param producto Lista de productos a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarProductoEliminado(Producto producto) throws IOException, InterruptedException {
        String contenidoProductos = "";
        String contenidoPujas = "";
        for(Puja puja : producto.getListaPuja()){
            contenidoPujas += producto.getNombre()+"@@"+puja.getUsuario()+"@@"
                    +puja.getFecha()+"@@"+puja.getValor()+"@@"+LocalDateTime.now()+"\n";
        }
        contenidoProductos += producto.getNombre()+"@@"+producto.getTipoProducto()+"@@"+
                producto.getValorInicial()+"@@"+producto.getDescripcion()+"@@"+producto.getFechaInicio()+"@@"+
                producto.getFechaFin()+"@@"+producto.isVendido()+"@@"+producto.getRutaFoto()+"@@"+
                LocalDateTime.now()+"\n";

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ELIMINADOS, contenidoProductos, true);
        GuardarArchivo hiloGuardarArchivo2 = new GuardarArchivo(RUTA_ARCHIVO_PUJAS_ELIMINADOS, contenidoPujas, true);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();
        hiloGuardarArchivo2.start();
        hiloGuardarArchivo2.join();

        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ELIMINADOS, contenidoProductos, true);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_ELIMINADOS, contenidoPujas, true);

    }

    /**
     * Metodo para guardar el producto de un anunciante eliminado
     * @param codigoAnunciante Usuario del anunciante
     * @param nombreProducto Nombre del producto
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarProductoAnuncianteEliminado(String codigoAnunciante, String nombreProducto) throws IOException, InterruptedException {
        String contenidoProductos = "";
        contenidoProductos += codigoAnunciante+"@@"+nombreProducto+"@@"+LocalDateTime.now()+"\n";

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES_ELIMINADOS, contenidoProductos, true);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();

        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES_ELIMINADOS, contenidoProductos, true);
    }

    /**
     * Metodo para guardar la puja de un comprador eliminado
     * @param codigoAnunciante Usuario del comprador
     * @param nombreProducto Nombre del producto
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarPujaCompradorEliminado(String codigoAnunciante, String nombreProducto) throws IOException, InterruptedException {
        String contenidoProductos = "";
        contenidoProductos += codigoAnunciante+"@@"+nombreProducto+"@@"+LocalDateTime.now()+"\n";

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES_ELIMINADOS, contenidoProductos, true);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();

        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES_ELIMINADOS, contenidoProductos, true);
    }

    /**
     * Metodo para guardar una puja eliminada
     * @param puja Puja a eliminar
     * @param producto Nombre del producto asociado a la puja
     * @throws IOException Se valida si existe un error en la escritura del archivo
     */
    public static void guardarPujaEliminada(Puja puja, String producto) throws IOException, InterruptedException {
        String contenidoPujas = "";
        contenidoPujas += producto+"@@"+puja.getUsuario()+"@@"+puja.getFecha()+"@@"
                +puja.getValor()+"@@"+LocalDateTime.now()+"\n";

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVO_PUJAS_ELIMINADOS, contenidoPujas, true);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();

        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_ELIMINADOS, contenidoPujas, true);
    }

    /**
     * Método para guardar una lista de productos y sus pujas en el respaldo
     * @param listaProductos Lista de productos a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarProductosRespaldo(ArrayList<Producto> listaProductos) throws IOException, InterruptedException {
        String contenidoProductos = "";
        String contenidoPujas = "";
        for(Producto producto : listaProductos){
            if(producto.getListaPuja() != null){
                for(Puja puja : producto.getListaPuja()){
                    contenidoPujas += producto.getNombre()+"@@"+puja.getUsuario()+"@@"
                            +puja.getFecha()+"@@"+puja.getValor()+"\n";
                }
            }
            contenidoProductos += producto.getNombre()+"@@"+producto.getTipoProducto()+"@@"+
                    producto.getValorInicial()+"@@"+producto.getDescripcion()+"@@"+producto.getFechaInicio()+"@@"+
                    producto.getFechaFin()+"@@"+producto.isVendido()+"@@"+producto.getRutaFoto()+"\n";
        }

        GuardarArchivo hiloGuardarArchivo1 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Productos")+".txt",
        contenidoProductos, false);
        GuardarArchivo hiloGuardarArchivo2 = new GuardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("PujasProductos")+".txt",
        contenidoPujas, false);

        hiloGuardarArchivo1.start();
        hiloGuardarArchivo1.join();
        hiloGuardarArchivo2.start();
        hiloGuardarArchivo2.join();

        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Productos")+".txt",
        //         contenidoProductos, false);
        // ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("PujasProductos")+".txt",
        //         contenidoPujas, false);
    }

    /**
     * Metodo para cargar los usuarios
     * @return Lista de usuarios cargados
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static ArrayList<Usuario> cargarUsuarios() throws IOException, InterruptedException {

        LeerArchivo hiloLeerArchivo1 = new LeerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
        LeerArchivo hiloLeerArchivo2 = new LeerArchivo(RUTA_ARCHIVO_COMPRADORES);
        LeerArchivo hiloLeerArchivo3 = new LeerArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES);
        LeerArchivo hiloLeerArchivo4 = new LeerArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES);
        LeerArchivo hiloLeerArchivo5 = new LeerArchivo(RUTA_ARCHIVO_MENSAJES);
        LeerArchivo hiloLeerArchivo6 = new LeerArchivo(RUTA_ARCHIVO_CHATS);

        hiloLeerArchivo1.start();
        hiloLeerArchivo1.join();
        hiloLeerArchivo2.start();
        hiloLeerArchivo2.join();
        hiloLeerArchivo3.start();
        hiloLeerArchivo3.join();
        hiloLeerArchivo4.start();
        hiloLeerArchivo4.join();
        hiloLeerArchivo5.start();
        hiloLeerArchivo5.join();
        hiloLeerArchivo6.start();
        hiloLeerArchivo6.join();

        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        ArrayList<String> listaChats = hiloLeerArchivo6.getContenido();
        ArrayList<String> listaAnunciantes = hiloLeerArchivo1.getContenido();
        ArrayList<String> listaCompradores = hiloLeerArchivo2.getContenido();
        ArrayList<String> listaProductos = hiloLeerArchivo3.getContenido();
        ArrayList<String> listaPujas = hiloLeerArchivo4.getContenido();
        ArrayList<String> listaMensajes = hiloLeerArchivo5.getContenido();

        // ArrayList<Usuario> listaUsuario = new ArrayList<>();
        // ArrayList<String> listaAnunciantes = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
        // ArrayList<String> listaCompradores = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
        // ArrayList<String> listaProductos = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES);
        // ArrayList<String> listaPujas = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES);
        // ArrayList<String> listaMensajes = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_MENSAJES);

        for(String anuncianteCargado : listaAnunciantes){
            Usuario usuario = new Usuario();
            usuario.setIUsuario(new Anunciante());
            Scanner scanner = new Scanner(anuncianteCargado);
            scanner.useDelimiter("@@");
            while (scanner.hasNext()){
                usuario.setUsuario(scanner.next());
                usuario.setNombre(scanner.next());
                usuario.setContrasenia(scanner.next());
                usuario.setEdad(Integer.parseInt(scanner.next()));
            }
            for(String productoCargado : listaProductos){
                Scanner scanner1 = new Scanner(productoCargado);
                scanner1.useDelimiter("@@");
                while (scanner1.hasNext()){
                    String aux = scanner1.next();
                    if(aux.equals(usuario.getUsuario())){
                        usuario.getListaProductos().add(scanner1.next());
                    }
                }

            }
            listaUsuario.add(usuario);
        }
        for(String compradorCargado : listaCompradores){
            Usuario usuario = new Usuario();
            usuario.setIUsuario(new Comprador());
            Scanner scanner = new Scanner(compradorCargado);
            scanner.useDelimiter("@@");
            while (scanner.hasNext()){
                usuario.setUsuario(scanner.next());
                usuario.setNombre(scanner.next());
                usuario.setContrasenia(scanner.next());
                usuario.setEdad(Integer.parseInt(scanner.next()));
            }
            for(String pujaCargada : listaPujas){
                Scanner scanner1 = new Scanner(pujaCargada);
                scanner1.useDelimiter("@@");
                while (scanner1.hasNext()){
                    String aux = scanner1.next();
                    if(aux.equals(usuario.getUsuario())){
                        usuario.getListaProductos().add(scanner1.next());
                    }
                }
            }
            listaUsuario.add(usuario);
        }

        for(String chatCargado : listaChats){
            Chat chat = new Chat();
            Scanner scanner = new Scanner(chatCargado);
            scanner.useDelimiter("@@");
            while (scanner.hasNext()){
                chat.setUsuario(scanner.next());
                chat.setUsuarioRemitente(scanner.next());
                chat.setUsuarioDestinatario(scanner.next());
            }
            for(Usuario usuario : listaUsuario){
                if(usuario.getUsuario().equals(chat.getUsuario())){
                    usuario.getListaChats().add(chat);
                    break;
                }
            }
        }

        for(String mensajeCargado : listaMensajes){
            Mensaje mensaje = new Mensaje();
            Scanner scanner = new Scanner(mensajeCargado);
            scanner.useDelimiter("@@");
            while (scanner.hasNext()){
                mensaje.setUsuario(scanner.next());
                mensaje.setUsuarioRemitente(scanner.next());
                mensaje.setUsuarioDestinatario(scanner.next());
                mensaje.setMessage(scanner.next());
                LocalDateTime localDateTime = LocalDateTime.parse(scanner.next());
                mensaje.setFecha(localDateTime);
            }
            for(Usuario usuario : listaUsuario){
                for(Chat chat : usuario.getListaChats()){
                    if(chat.getUsuario().equals(mensaje.getUsuario())){
                        chat.getListaMensajes().add(mensaje);
                        break;
                    }
                }
            }
        }


        return  listaUsuario;
    }

    /**
     * Metodo para cargar los productos
     * @return Lista de productos cargados
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static ArrayList<Producto> cargarProductos() throws IOException, InterruptedException {

        LeerArchivo hiloLeerArchivo1 = new LeerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        LeerArchivo hiloLeerArchivo2 = new LeerArchivo(RUTA_ARCHIVO_PUJAS);

        hiloLeerArchivo1.start();
        hiloLeerArchivo1.join();
        hiloLeerArchivo2.start();
        hiloLeerArchivo2.join();

        ArrayList<Producto> listaProducto = new ArrayList<>();
        ArrayList<String> productos = hiloLeerArchivo1.getContenido();
        ArrayList<String> pujas = hiloLeerArchivo2.getContenido();

        // ArrayList<Producto> listaProducto = new ArrayList<>();
        // ArrayList<String> productos = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        // ArrayList<String> pujas = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS);

        for(String productoCargado : productos){
            Producto producto = new Producto();
            Scanner scanner = new Scanner(productoCargado);
            scanner.useDelimiter("@@");
            while (scanner.hasNext()){
                producto.setNombre(scanner.next());
                producto.setTipoProducto(scanner.next());
                producto.setValorInicial(Double.parseDouble(scanner.next()));
                producto.setDescripcion(scanner.next());
                LocalDateTime localDateTime = LocalDateTime.parse(scanner.next());
                producto.setFechaInicio(localDateTime);
                localDateTime = LocalDateTime.parse(scanner.next());
                producto.setFechaFin(localDateTime);
                producto.setVendido(Boolean.parseBoolean(scanner.next()));
                producto.setRutaFoto(scanner.next());
            }
            for(String pujaCargada : pujas){
                Scanner scanner1 = new Scanner(pujaCargada);
                scanner1.useDelimiter("@@");
                while (scanner1.hasNext()){
                    String aux = scanner1.next();
                    if(aux.equals(producto.getNombre())){
                        Puja puja = new Puja();
                        puja.setUsuario(scanner1.next());
                        LocalDateTime localDateTime = LocalDateTime.parse(scanner1.next());
                        puja.setFecha(localDateTime);
                        puja.setValor(Double.parseDouble(scanner1.next()));
                        producto.agregarPuja(puja);
                    }
                }
            }
            listaProducto.add(producto);
        }

        return listaProducto;
    }

    /**
     * Metodo para guardar un registro en el log de la aplicacion
     * @param mensajeLog Mensaje a guardar
     * @param nivel Nivel del registro
     * @param accion La accion que se realiza
     */
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) throws InterruptedException {

        GuardarRegistroLog hiloGuardarRegistroLog = new GuardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);

        hiloGuardarRegistroLog.start();
        hiloGuardarRegistroLog.join();

        // ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    /**
     * Metodo para iniciar sesion
     * @param usuario Usuario de la aplicacion
     * @param contrasenia Contrasenia de la aplicacion
     * @return Usuario logueado
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     * @throws UserNotFoundException En caso de que el usuario no sea encontrado en la lista de usuarios
     */
    public static Usuario iniciarSesion(String usuario, String contrasenia) throws IOException, UserNotFoundException, InterruptedException {
        if(validarUsuario(usuario, contrasenia) != null) {
            return validarUsuario(usuario, contrasenia);
        }else {
            throw new UserNotFoundException("Combinacion erronea");
        }
    }

    /**
     * Metodo privado para validar un usuario en la lista de usuarios
     * @param usuario Usuario de la aplicacion
     * @param contrasenia Contrasenia de la aplicacion
     * @return Usuario logueado
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    private static Usuario validarUsuario(String usuario, String contrasenia) throws IOException, InterruptedException {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios();

        for (Usuario usuarioAux : usuarios) {
            if (usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return usuarioAux;
            }
        }
        return null;
    }

    /**
     * Metodo para cargar la casa de subastas del archivo binario
     * @return casa de subastas
     */
    public static SubastasQuindio cargarRecursoCasaBinario() {
        SubastasQuindio subastasQuindio = null;
        try {
            
            CargarRecursoSerializado hiloCargarRecursoSerializado = new CargarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASA_BINARIO);

            hiloCargarRecursoSerializado.start();
            hiloCargarRecursoSerializado.join();

            subastasQuindio = (SubastasQuindio)hiloCargarRecursoSerializado.getAux();

            // subastasQuindio = (SubastasQuindio)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASA_BINARIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subastasQuindio;
    }

    /**
     * Metodo para guardar la casa de subastas en binario
     * @param subastasQuindio casa de subastas a guardar
     */
    public static void guardarRecursoCasaBinario(SubastasQuindio subastasQuindio) {
        try {

            SalvarRecursoSerializado hiloSalvarRecursoSerializado = new SalvarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASA_BINARIO, subastasQuindio);

            hiloSalvarRecursoSerializado.start();
            hiloSalvarRecursoSerializado.join();

            // ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASA_BINARIO, subastasQuindio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para guardar la casa de subastas en binario en el respaldo
     * @param subastasQuindio casa de subastas a guardar
     */
    public static void guardarRecursoCasaBinarioRespaldo(SubastasQuindio subastasQuindio) {
        try {

            SalvarRecursoSerializado hiloSalvarRecursoSerializado = new SalvarRecursoSerializado(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("model")+".dat", subastasQuindio);

            hiloSalvarRecursoSerializado.start();
            hiloSalvarRecursoSerializado.join();

            // ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("model")+".dat", subastasQuindio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar la casa de subastas del archivo XML
     * @return casa de subastas
     */
    public static SubastasQuindio cargarRecursoCasaXML() {
        SubastasQuindio subastasQuindio = null;
        try {

            CargarRecursoXML hiloCargarRecursoSerializado = new CargarRecursoXML(RUTA_ARCHIVO_MODELO_CASA_XML);

            hiloCargarRecursoSerializado.start();
            hiloCargarRecursoSerializado.join();

            subastasQuindio = (SubastasQuindio)hiloCargarRecursoSerializado.getObjetoXML();

            // subastasQuindio = (SubastasQuindio)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_CASA_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subastasQuindio;
    }

    /**
     * Metodo para guardar la casa de subastas en XML
     * @param subastasQuindio casa de subastas a guardar
     */
    public static void guardarRecursoCasaXML(SubastasQuindio subastasQuindio) {
        try {

            SalvarRecursoXML hiloSalvarRecursoXML = new SalvarRecursoXML(RUTA_ARCHIVO_MODELO_CASA_XML, subastasQuindio);

            hiloSalvarRecursoXML.start();
            hiloSalvarRecursoXML.join();

            // ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_CASA_XML, subastasQuindio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para guardar la casa de subastas en XML en el respaldo
     * @param subastasQuindio casa de subastas a guardar
     */
    public static void guardarRecursoCasaXMLRespaldo(SubastasQuindio subastasQuindio) {
        try {

            SalvarRecursoXML hiloSalvarRecursoXML = new SalvarRecursoXML(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("model")+".xml",
                    subastasQuindio);

            hiloSalvarRecursoXML.start();
            hiloSalvarRecursoXML.join();

            // ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("model")+".xml",
            //         subastasQuindio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo privado para realizar el formato del nombre del archivo de respaldo a implementar
     * @param fileName Nombre del archivo de respaldo
     * @return Nombre del archivo de respaldo con el formato
     */
    private static String getFileSaveName(String fileName){
        LocalDateTime localDateTime = LocalDateTime.now();
        String aux = String.valueOf(localDateTime.getYear());
        aux = aux.substring(1);
        aux = aux.substring(1);
        fileName += "_"+localDateTime.getDayOfMonth()+localDateTime.getMonthValue()+aux+"_"+localDateTime.getHour()+"_"
                +localDateTime.getMinute()+"_"+localDateTime.getSecond();
        return fileName;
    }

}
