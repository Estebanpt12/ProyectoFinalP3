package co.edu.uniquindio.servidor.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LeerArchivo extends Thread{

    String ruta;
    ArrayList<String> contenido;

    public LeerArchivo(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void run(){
        try {
            ArrayList<String> contenido = new ArrayList<String>();
            FileReader fr=new FileReader(ruta);
            BufferedReader bfr=new BufferedReader(fr);
            String linea="";
            while((linea = bfr.readLine())!=null)
            {
                contenido.add(linea);
            }
            bfr.close();
            fr.close();
            this.contenido=contenido;
        } catch (Exception e) {
        }
    }
    
    public ArrayList<String> getContenido() {
        return contenido;
    }
}
