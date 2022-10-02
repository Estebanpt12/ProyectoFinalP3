package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.services.IUsuario;

import java.util.ArrayList;

public class Comprador implements IUsuario {

    /**
     * Lista donde se guardan los nombre de los productos
     */
    ArrayList<String> listaProductos = new ArrayList<>();

    /**
     * Constructor vacio de la clase
     */
    public Comprador() {
    }

    /**
     * Metodo para aniadir un producto
     * @param nombre nombre del producto a aniadir
     */
    public void aniadirProducto(String nombre){
        listaProductos.add(nombre);
    }

    /**
     * Metodo para tomar la lista de productos
     * @return Lista donde se guardan los nombre de los productos
     */
    public ArrayList<String> getListaProducto() {
        return listaProductos;
    }

    /**
     * Metodo para asignar la lista de productos
     * @param listaProducto Lista donde se guardan los nombre de los productos
     */
    public void setListaProducto(ArrayList<String> listaProducto) {
        this.listaProductos = listaProducto;
    }

    /**
     * Metodo para validar la cantidad de pujas activas
     * @return Validacion de si no se excede la cantidad de producto pujados
     */
    @Override
    public boolean validarCantidadProductos() {
        return listaProductos.size() < 3;
    }

    /**
     * Metodo para validar el tipo de usuario
     * @return Verdadero porque el usuario es comprador
     */
    @Override
    public boolean validarTipoUsuario() {
        return true;
    }

    /**
     * Metodo para eliminar un producto
     * @param nombre Nombre del producto a eliminar
     */
    @Override
    public void eliminarProducto(String nombre) {
        listaProductos.remove(nombre);
    }
}
