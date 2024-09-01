package com.empresa.globitos;

public class Producto {
    private String nombre;
    private String tipo;
    private int cantidadDisponible;
    private float precio;

    // Constructor
    public Producto(String nombre, String tipo, int cantidadDisponible, float precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        setCantidadDisponible(cantidadDisponible); // Usar setter para validación
        this.precio = precio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public float getPrecio() {
        return precio;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidadDisponible(int cantidad) {
        if (cantidad >= 0) {
            this.cantidadDisponible = cantidad;
        } else {
            throw new IllegalArgumentException("Cantidad no puede ser negativa.");
        }
    }

    public void setPrecio(float precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("Precio no puede ser negativo.");
        }
    }

    // Modificar producto
    public void modificarProducto(String nuevoNombre, String nuevoTipo, int nuevaCantidad, float nuevoPrecio) {
        setNombre(nuevoNombre);
        setTipo(nuevoTipo);
        setCantidadDisponible(nuevaCantidad);
        setPrecio(nuevoPrecio);
    }

    // Reducir cantidad disponible
    public void reducirCantidadDisponible(int cantidad) {
        if (cantidad > 0 && cantidad <= this.cantidadDisponible) {
            this.cantidadDisponible -= cantidad;
        } else {
            System.out.println("Cantidad no válida para reducir.");
        }
    }

    @Override
    public String toString() {
        return nombre + " - " + tipo + " - Precio: Q" + precio + " - Cantidad disponible: " + cantidadDisponible;
    }
}
