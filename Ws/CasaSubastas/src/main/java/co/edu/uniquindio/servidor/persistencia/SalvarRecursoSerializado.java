package co.edu.uniquindio.servidor.persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SalvarRecursoSerializado extends Thread{

    String rutaArchivo;
    Object object;

    public SalvarRecursoSerializado(String rutaArchivo, Object object){
        this.rutaArchivo = rutaArchivo;
        this.object = object;
    }

    public void run(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            oos.writeObject(object);
        } catch (Exception e) {
            try {
                throw e;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
