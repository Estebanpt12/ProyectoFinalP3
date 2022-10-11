package co.edu.uniquindio.casasubastas.persistencia;

import co.edu.uniquindio.casasubastas.exceptions.*;
import co.edu.uniquindio.casasubastas.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Persistencia {

    public static void main(String[] args) {
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
                    new File("co/edu/uniquindio/casasubastas/views/login-view.fxml"), localDateTime
                    , localDateTime.plusYears(1), 10000, "Esteban");
            subastasQuindio.crearProducto("Pro", "Jpa", "prueba",
                    new File("co/edu/uniquindio/casasubastas/views/login-view.fxml"), localDateTime
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
        //guardarRecursoCasaXML(subastasQuindio);
        try {
            guardarProductoEliminado(subastasQuindio.getProductos().get(0));
            guardarProductoAnuncianteEliminado("123", "wasd");
            guardarPujaCompradorEliminado("123", "wasd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     * Método para guardar una lista de usuarios sin importar si es comprador o anunciante
     * @param listaUsuarios Lista de usuarios a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        String contenidoCompradores = "";
        String contenidoAnunciantes = "";
        String contenidoPujas = "";
        String contenidoProductos = "";
        for(Usuario usuario : listaUsuarios){
            if(usuario.getiUsuario() instanceof Anunciante){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoProductos += "<"+usuario.getUsuario()+">@@<"+producto+">\n";
                    }
                }
                contenidoAnunciantes += "<"+usuario.getUsuario()+">@@<"+usuario.getNombre()+">@@<"
                        +usuario.getContrasenia()+">@@<"+usuario.getEdad()+">\n";
            }
            if(usuario.getiUsuario() instanceof Comprador){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoPujas += "<"+usuario.getUsuario()+">@@<"+producto+">\n";
                    }
                }
                contenidoCompradores += "<"+usuario.getUsuario()+">@@<"+usuario.getNombre()+">@@<"
                        +usuario.getContrasenia()+">@@<"+usuario.getEdad()+">\n";
            }
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenidoAnunciantes, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenidoCompradores, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES, contenidoProductos, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES, contenidoPujas, false);
    }


    /**
     * Método para guardar una lista de usuarios en el archivo de respaldo
     * @param listaUsuarios Lista de usuarios a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void guardarUsuariosRespaldo(ArrayList<Usuario> listaUsuarios) throws IOException {
        String contenidoCompradores = "";
        String contenidoAnunciantes = "";
        String contenidoPujas = "";
        String contenidoProductos = "";
        for(Usuario usuario : listaUsuarios){
            if(usuario.getiUsuario() instanceof Anunciante){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoProductos += "<"+usuario.getUsuario()+">@@<"+producto+">\n";
                    }
                }
                contenidoAnunciantes += "<"+usuario.getUsuario()+">@@<"+usuario.getNombre()+">@@<"
                        +usuario.getContrasenia()+">@@<"+usuario.getEdad()+">\n";
            }
            if(usuario.getiUsuario() instanceof Comprador){
                if(usuario.getListaProductos() != null){
                    for(String producto : usuario.getListaProductos()){
                        contenidoPujas += "<"+usuario.getUsuario()+">@@<"+producto+">\n";
                    }
                }
                contenidoCompradores += "<"+usuario.getUsuario()+">@@<"+usuario.getNombre()+">@@<"
                        +usuario.getContrasenia()+">@@<"+usuario.getEdad()+">\n";
            }
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Anunciantes")+".txt",
                contenidoAnunciantes, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Compradores")+".txt",
                contenidoCompradores, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("ProductosAnunciantes")+".txt",
                contenidoProductos, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("PujasCompradores")+".txt",
                contenidoPujas, false);
    }

    /**
     * Método para guardar una lista de productos y sus pujas
     * @param listaProductos Lista de productos a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void guardarProductos(ArrayList<Producto> listaProductos) throws IOException{
        String contenidoProductos = "";
        String contenidoPujas = "";
        for(Producto producto : listaProductos){
            if(producto.getListaPuja() != null){
                for(Puja puja : producto.getListaPuja()){
                    contenidoPujas += "<"+producto.getNombre()+">@@<"+puja.getUsuario()+">@@<"
                            +puja.getFecha()+">@@<"+puja.getValor()+">\n";
                }
            }
            contenidoProductos += "<"+producto.getNombre()+">@@<"+producto.getTipoProducto()+">@@<"+
                    producto.getValorInicial()+">@@<"+producto.getDescripcion()+">@@<"+producto.getFechaInicio()+">@@<"+
                    producto.getFechaFin()+">@@<"+producto.isVendido()+">@@<"+producto.getFoto().getAbsolutePath()+">\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenidoProductos, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS, contenidoPujas, false);

    }

    /**
     * Método para guardar unproducto y sus pujas eliminados
     * @param producto Lista de productos a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarProductoEliminado(Producto producto) throws IOException{
        String contenidoProductos = "";
        String contenidoPujas = "";
        for(Puja puja : producto.getListaPuja()){
            contenidoPujas += "<"+producto.getNombre()+">@@<"+puja.getUsuario()+">@@<"
                    +puja.getFecha()+">@@<"+puja.getValor()+">@@<"+LocalDateTime.now()+">\n";
        }
        contenidoProductos += "<"+producto.getNombre()+">@@<"+producto.getTipoProducto()+">@@<"+
                producto.getValorInicial()+">@@<"+producto.getDescripcion()+">@@<"+producto.getFechaInicio()+">@@<"+
                producto.getFechaFin()+">@@<"+producto.isVendido()+">@@<"+producto.getFoto().getAbsolutePath()+">@@<"+
                LocalDateTime.now()+">\n";
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ELIMINADOS, contenidoProductos, true);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_ELIMINADOS, contenidoPujas, true);

    }

    /**
     * Metodo para guardar el producto de un anunciante eliminado
     * @param codigoAnunciante Usuario del anunciante
     * @param nombreProducto Nombre del producto
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarProductoAnuncianteEliminado(String codigoAnunciante, String nombreProducto) throws IOException {
        String contenidoProductos = "";
        contenidoProductos += "<"+codigoAnunciante+">@@<"+nombreProducto+">@@<"+LocalDateTime.now()+">\n";
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES_ELIMINADOS, contenidoProductos, true);
    }

    /**
     * Metodo para guardar la puja de un comprador eliminado
     * @param codigoAnunciante Usuario del comprador
     * @param nombreProducto Nombre del producto
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static void guardarPujaCompradorEliminado(String codigoAnunciante, String nombreProducto) throws IOException {
        String contenidoProductos = "";
        contenidoProductos += "<"+codigoAnunciante+">@@<"+nombreProducto+">@@<"+LocalDateTime.now()+">\n";
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES_ELIMINADOS, contenidoProductos, true);
    }

    /**
     * Método para guardar una lista de productos y sus pujas en el respaldo
     * @param listaProductos Lista de productos a guardar
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public void guardarProductosRespaldo(ArrayList<Producto> listaProductos) throws IOException{
        String contenidoProductos = "";
        String contenidoPujas = "";
        for(Producto producto : listaProductos){
            if(producto.getListaPuja() != null){
                for(Puja puja : producto.getListaPuja()){
                    contenidoPujas += "<"+producto.getNombre()+">@@<"+puja.getUsuario()+">@@<"
                            +puja.getFecha()+">@@<"+puja.getValor()+">\n";
                }
            }
            contenidoProductos += "<"+producto.getNombre()+">@@<"+producto.getTipoProducto()+">@@<"+
                    producto.getValorInicial()+">@@<"+producto.getDescripcion()+">@@<"+producto.getFechaInicio()+">@@<"+
                    producto.getFechaFin()+">@@<"+producto.isVendido()+">@@<"+producto.getFoto().getAbsolutePath()+">\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("Productos")+".txt",
                contenidoProductos, false);
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("PujasProductos")+".txt",
                contenidoPujas, false);
    }

    /**
     * Metodo para cargar los usuarios
     * @return Lista de usuarios cargados
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public static ArrayList<Usuario> cargarUsuarios() throws IOException {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        ArrayList<String> listaAnunciantes = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
        ArrayList<String> listaCompradores = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
        ArrayList<String> listaProductos = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS_ANUNCIANTES);
        ArrayList<String> listaPujas = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS_COMPRADORES);

        for(String anuncianteCargado : listaAnunciantes){
            Usuario usuario = new Usuario();
            usuario.setiUsuario(new Anunciante());
            anuncianteCargado = deleteFirstAndLastString(anuncianteCargado);
            Scanner scanner = new Scanner(anuncianteCargado);
            scanner.useDelimiter(">@@<");
            while (scanner.hasNext()){
                usuario.setUsuario(scanner.next());
                usuario.setNombre(scanner.next());
                usuario.setContrasenia(scanner.next());
                usuario.setEdad(Integer.parseInt(scanner.next()));
            }
            for(String productoCargado : listaProductos){
                productoCargado = deleteFirstAndLastString(productoCargado);
                Scanner scanner1 = new Scanner(productoCargado);
                scanner1.useDelimiter(">@@<");
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
            usuario.setiUsuario(new Comprador());
            compradorCargado = deleteFirstAndLastString(compradorCargado);
            Scanner scanner = new Scanner(compradorCargado);
            scanner.useDelimiter(">@@<");
            while (scanner.hasNext()){
                usuario.setUsuario(scanner.next());
                usuario.setNombre(scanner.next());
                usuario.setContrasenia(scanner.next());
                usuario.setEdad(Integer.parseInt(scanner.next()));
            }
            for(String pujaCargada : listaPujas){
                pujaCargada = deleteFirstAndLastString(pujaCargada);
                Scanner scanner1 = new Scanner(pujaCargada);
                scanner1.useDelimiter(">@@<");
                while (scanner1.hasNext()){
                    String aux = scanner1.next();
                    if(aux.equals(usuario.getUsuario())){
                        usuario.getListaProductos().add(scanner1.next());
                    }
                }
            }
            listaUsuario.add(usuario);
        }

        return  listaUsuario;
    }

    /**
     * Metodo para cargar los productos
     * @return Lista de productos cargados
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    public ArrayList<Producto> cargarProductos() throws IOException{
        ArrayList<Producto> listaProducto = new ArrayList<>();
        ArrayList<String> productos = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        ArrayList<String> pujas = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS);

        for(String productoCargado : productos){
            Producto producto = new Producto();
            productoCargado = deleteFirstAndLastString(productoCargado);
            Scanner scanner = new Scanner(productoCargado);
            scanner.useDelimiter(">@@<");
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
                File file = new File(scanner.next());
                producto.setFoto(file);
            }
            for(String pujaCargada : pujas){
                pujaCargada = deleteFirstAndLastString(pujaCargada);
                Scanner scanner1 = new Scanner(pujaCargada);
                scanner1.useDelimiter(">@@<");
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
     * Metodo para eliminar el primer y ultimo caracter de una cadena
     * @param cadena cadena a implementar
     * @return cadena con el primer y el ultimo caracter eliminado
     */
    private static String deleteFirstAndLastString(String cadena){
        cadena = cadena.substring(1);
        cadena = cadena.substring(0, cadena.length()-1);
        return cadena;
    }

    /**
     * Metodo para guardar un registro en el log de la aplicacion
     * @param mensajeLog Mensaje a guardar
     * @param nivel Nivel del registro
     * @param accion La accion que se realiza
     */
    public void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    /**
     * Metodo para iniciar sesion
     * @param usuario Usuario de la aplicacion
     * @param contrasenia Contrasenia de la aplicacion
     * @return Si el usuario existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     * @throws UserNotFoundException En caso de que el usuario no sea encontrado en la lista de usuarios
     */
    public static boolean iniciarSesion(String usuario, String contrasenia) throws  IOException, UserNotFoundException {

        if(validarUsuario(usuario,contrasenia)) {
            return true;
        }else {
            throw new UserNotFoundException("Usuario no existe");
        }

    }

    /**
     * Metodo privado para validar un usuario en la lista de usuarios
     * @param usuario Usuario de la aplicacion
     * @param contrasenia Contrasenia de la aplicacion
     * @return Si el usuario existe
     * @throws IOException Excepcion que se presenta si se presenta errores al manipular el archivo
     */
    private static boolean validarUsuario(String usuario, String contrasenia) throws IOException {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios();

        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
        {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para cargar la casa de subastas del archivo binario
     * @return casa de subastas
     */
    public static SubastasQuindio cargarRecursoCasaBinario() {
        SubastasQuindio subastasQuindio = null;
        try {
            subastasQuindio = (SubastasQuindio)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASA_BINARIO);
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
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_CASA_BINARIO, subastasQuindio);
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
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("model")+".dat", subastasQuindio);
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
            subastasQuindio = (SubastasQuindio)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_CASA_XML);
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
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_CASA_XML, subastasQuindio);
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
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVOS_RESPALDO+getFileSaveName("model")+".xml",
                    subastasQuindio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo provado para realizar el formato del nombre del archivo de respaldo a implementar
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
