package co.edu.uniquindio.casasubastas.services;

public interface IUsuario {

    /**
     * Metodo para validar la cantidad limite de productos
     * @return retorna si la cantidad de productos se ha sobrepasado o no
     */
    boolean validarCantidadProductos();

    /**
     * Metodo para validar el tipo de usuario
     * @return retorna true si el usuario es comprador y false si el usuario es anunciante
     */
    boolean validarTipoUsuario();

    /**
     * Metodo para aniadir un producto
     * @param nombre nombre del producto a aniadir
     */
    void aniadirProducto(String nombre);

    /**
     * Metodo para eliminar un producto
     * @param nombre Nombre del producto a eliminar
     */
    void eliminarProducto(String nombre);

}
