package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.services.IUsuario;

import java.util.ArrayList;

public class Anunciante implements IUsuario {

    /**
     * Lista de productos publicados por el anunciante
     */
    ArrayList<String> listaProductos = new ArrayList<>();

    /**
     * Constructor vacio de la clase
     */
    public Anunciante() {}

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
    @Override
    public void eliminarProducto(String nombre) {
        listaProductos.remove(nombre);
    }

    /**
     * Metodo para tomar la lista de productos
     * @return Lista de productos publicados por el anunciante
     */
    public ArrayList<String> getlistaProductos() {
        return listaProductos;
    }

    /**
     * Metodo para asignar la lista de productos
     * @param listaProductos Lista de productos publicados por el anunciante
     */
    public void setlistaProductos(ArrayList<String> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * Metodo para validar la cantidad de prductos activos
     * @return Validacion de si no se excede la cantidad de producto publicados
     */
    @Override
    public boolean validarCantidadProductos() {
        return listaProductos.size() < 5;
    }

    /**
     * Metodo para validar el tipo de usuario
     * @return False porque el usuario es anunciante
     */
    @Override
    public boolean validarTipoUsuario() {
        return false;
    }



}
