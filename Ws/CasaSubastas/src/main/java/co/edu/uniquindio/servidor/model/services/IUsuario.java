package co.edu.uniquindio.casasubastas.model.services;

public interface IUsuario {

    /**
     * Metodo para validar la cantidad limite de productos
     * @return retorna si la cantidad de productos se ha sobrepasado o no
     */
    boolean validarCantidadProductos(int valor);
}
