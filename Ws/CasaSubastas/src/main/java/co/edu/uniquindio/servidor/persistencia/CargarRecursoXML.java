package co.edu.uniquindio.servidor.persistencia;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CargarRecursoXML extends Thread{

    Object objetoXML;
    String rutaArchivo;

    public CargarRecursoXML(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void run(){
        XMLDecoder decodificadorXML;

        try {
            decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
            objetoXML = decodificadorXML.readObject();
            decodificadorXML.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public Object getObjetoXML() {
        return objetoXML;
    }
}
