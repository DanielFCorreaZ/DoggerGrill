package com.danielcorrea.doggergrill;

/**
 * Created by DELL on 20/09/2016.
 */
public class Productos {
    private int idima;
    private String nombre, descripcion,precio;
    public Productos(){

    }

    public Productos(int idima, String precio, String nombre, String descripcion){
        this.idima=idima;
        this.precio=precio;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }

    public int getIdima() {
        return idima;
    }

    public void setIdima(int idima) {
        this.idima = idima;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
