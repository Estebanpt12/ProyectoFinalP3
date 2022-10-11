package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.model.services.IUsuario;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;


public class Comprador implements IUsuario, Serializable {

    /**
     * Ruta del archivo donde se toma la cantidad maxima de productos
     */
    private final String rutaArchivoProperties = "src/main/java/co/edu/uniquindio/casasubastas/resources/application.properties";

    public String getRutaArchivoProperties() {
        return rutaArchivoProperties;
    }

    /**
     * Constructor vacio de la clase
     */
    public Comprador() {
    }

    /**
     * Metodo para validar la cantidad de pujas activas
     * @return Validacion de si no se excede la cantidad de producto pujados
     */
    @Override
    public boolean validarCantidadProductos(int valor) {
        Properties properties = new Properties();
        int auxiliar = 0;
        try {
            properties.load(new FileReader(rutaArchivoProperties));
            auxiliar = Integer.parseInt(properties.getProperty("valor_maximo_pujas"));
        } catch (IOException e) { e.printStackTrace(); }
        return valor < auxiliar;
    }
}
