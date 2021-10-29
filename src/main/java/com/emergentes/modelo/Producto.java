package com.emergentes.modelo;
public class Producto {
    private int id;
    private String producto;
    private float precio;
    private int cantidad;

    public Producto() {
        this.id=0;
        this.producto="";
        this.precio=0;
        this.cantidad=0;
    }
    

    public int getId() {
        return id;
    }

    public String getProducto() {
        return producto;
    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    @Override
    public String toString(){
        return "Producto{"+"id="+id+",producto="+producto+",precio="+precio+",cantidad="+cantidad +'}';
    }
    
}
