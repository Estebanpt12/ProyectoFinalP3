package co.edu.uniquindio.casasubastas.model;

import java.util.Date;

public class Puja {

    /**
     * Usuario que realiza la puja
     */
    private String usuario;

    /**
     * Valor monetario de la puja
     */
    private double valor;

    /**
     * Fecha cuando se realizó la puja
     */
    private Date fecha;

    /**
     * Construcor vacio de la clase
     */
    public Puja(){}

    /**
     * Metodo para tomar el usuario
     * @return Usuario que realiza la puja
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Metodo para asignar el usuario
     * @param usuario Usuario que realiza la puja
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo para tomar el valor
     * @return Valor monetario de la puja
     */
    public double getValor() {
        return valor;
    }

    /**
     * Metodo para asignar el valor
     * @param valor Valor monetario de la puja
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Metodo para tomar la fecha
     * @return Fecha cuando se realizó la puja
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo para asignar la fecha
     * @param fecha Fecha cuando se realizó la puja
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
