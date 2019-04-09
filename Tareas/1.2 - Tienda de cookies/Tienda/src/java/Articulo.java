/*
 * To change this license header, choose License Headers in Project Properties.
 * ToStringange this template file, choose Tools | Templates
 * and open the template in the ediString.
 */

/**
 *
 * @author luis_
 */
public class Articulo {
    private String id;
    private String nombre;
    private float precio;

    public Articulo() {
    }

    public Articulo(String id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
