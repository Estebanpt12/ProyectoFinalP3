package co.edu.uniquindio.casasubastas.model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Producto implements Serializable {

    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Tipo de producto
     */
    private String tipoProducto;

    /**
     * Descripcion del producto
     */
    private String descripcion;

    /**
     * Foto del producto
     */
    private File foto;

    /**
     * Fecha de publicacion del producto
     */
    private LocalDateTime fechaInicio;

    /**
     * Fecha limite del producto
     */
    private LocalDateTime fechaFin;

    /**
     * Valor inicial que asigna el publicante al producto
     */
    private double valorInicial;

    /**
     * Lista de pujas del producto
     */
    private ArrayList<Puja> listaPuja = new ArrayList<>();;

    /**
     * Marca para saber si el producto esta vendido
     */
    private boolean vendido;

    /**
     * Constructor vacio de la clase
     */
    public Producto(){ }

    /**
     * Metodo para tomar el nombre
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para asignar el nombre
     * @param nombre Nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para tomar el tipo producto
     * @return Tipo producto
     */
    public String getTipoProducto() {
        return tipoProducto;
    }

    /**
     * Metodo para asignar el tipo producto
     * @param tipoProducto Tipo producto
     */
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * Metodo para tomar la descripcion
     * @return Descripcion del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo para asignar la descripcion
     * @param descripcion Descripcion del producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Metodo para tomar la foto
     * @return Foto del producto
     */
    public File getFoto() {
        return foto;
    }

    /**
     * Metodo para asignar la foto
     * @param foto Foto del producto
     */
    public void setFoto(File foto) {
        this.foto = foto;
    }

    /**
     * Metodo para tomar la fecha inicial
     * @return Fecha de publicacion del producto
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Metodo para asignar la fecha inicial
     * @param fechaInicio Fecha de publicacion del producto
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Metodo para tomar la fecha final
     * @return Fecha limite del producto
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    /**
     * Metodo para asignar la fecha final
     * @param fechaFin Fecha limite del producto
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Metodo para tomar el valor
     * @return Valor inicial que asigna el publicante al producto
     */
    public double getValorInicial() {
        return valorInicial;
    }

    /**
     * Metodo para asignar el valor
     * @param valorInicial Valor inicial que asigna el publicante al producto
     */
    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    /**
     * Metodo para tomar la lista de pujas
     * @return Lista de pujas del producto
     */
    public ArrayList<Puja> getListaPuja() {
        return listaPuja;
    }

    /**
     * Metodo para asignar la lista de pujas
     * @param listaPuja Lista de pujas del producto
     */
    public void setListaPuja(ArrayList<Puja> listaPuja) {
        this.listaPuja = listaPuja;
    }

    /**
     * Metodo para validar si el producto esta vendido
     * @return Marca para saber si el producto esta vendido
     */
    public boolean isVendido() {
        return vendido;
    }

    /**
     * Metodo para asignar el estado del producto
     * @param vendido Marca para saber si el producto esta vendido
     */
    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    /**
     * Metodo para agregar una puja a la lista
     * @param usuario Usuario que realiza la puja
     * @param valorPuja Valor que se puja
     * @param fecha Fecha donde se realiza la puja
     */
    public void agregarPuja(String usuario, double valorPuja, LocalDateTime fecha){
        Puja puja = new Puja();
        puja.setFecha(fecha);
        puja.setUsuario(usuario);
        puja.setValor(valorPuja);
        listaPuja.add(puja);
    }

    /**
     * Metodo para agregar una puja
     * @param puja Puja a agregar
     */
    public void agregarPuja(Puja puja) {
        listaPuja.add(puja);
    }
}
