package co.edu.uniquindio.casasubastas.model;

import co.edu.uniquindio.casasubastas.model.services.IUsuario;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class Anunciante implements IUsuario, Serializable {

    public String getRutaArchivoProperties() {
        return rutaArchivoProperties;
    }

    /**
     * Ruta del archivo donde se toma la cantidad maxima de productos
     */
    private final String rutaArchivoProperties = "src/main/java/co/edu/uniquindio/casasubastas/resources/application.properties";

    /**
     * Constructor vacio de la clase
     */
    public Anunciante() {}

    /**
     * Metodo para validar la cantidad de prductos activos
     * @return Validacion de si no se excede la cantidad de producto publicados
     */
    @Override
    public boolean validarCantidadProductos(int valor) {
        Properties properties = new Properties();
        int auxiliar = 0;
        try {
            properties.load(new FileReader(rutaArchivoProperties));
            auxiliar = Integer.parseInt(properties.getProperty("valor_maximo_anuncios"));
        } catch (IOException e) { e.printStackTrace(); }
        return valor < auxiliar;
    }
}
