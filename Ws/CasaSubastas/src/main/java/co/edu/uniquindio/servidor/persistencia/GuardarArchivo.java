package co.edu.uniquindio.servidor.persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GuardarArchivo extends Thread{
    
    String ruta;
    String contenido; 
    Boolean flagAnexarContenido;

    public GuardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) {
        this.ruta = ruta;
        this.contenido = contenido;
        this.flagAnexarContenido = flagAnexarContenido;
    }

    @Override
    public void run(){
        FileWriter fw = null;
        try {
            fw = new FileWriter(ruta,flagAnexarContenido);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(contenido);
            bfw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
