package com.example.sesion14.Clases;

public class Producto {
    String nombre;
    String codigo;
    Float precio_venta;

    public Producto(String nombre, String codigo, Float precio_venta) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio_venta = precio_venta;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Float precio_venta) {
        this.precio_venta = precio_venta;
    }
}
