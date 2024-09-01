package com.empresa.globitos;

public class ProductoFabrica extends Producto {
    private int tiempoProduccion;

    // Constructor
    public ProductoFabrica(String nombre, String tipo, int cantidadDisponible, float precio, int tiempoProduccion) {
        super(nombre, tipo, cantidadDisponible, precio);
        setTiempoProduccion(tiempoProduccion); // Usar setter para validación
    }

    // Getter
    public int getTiempoProduccion() {
        return tiempoProduccion;
    }

    // Setter
    public void setTiempoProduccion(int tiempoProduccion) {
        if (tiempoProduccion > 0) {
            this.tiempoProduccion = tiempoProduccion;
        } else {
            throw new IllegalArgumentException("El tiempo de producción debe ser positivo.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " - Tiempo de Producción: " + tiempoProduccion + " días";
    }

    public boolean esFabricable() {
        return true; // Puede ser sobrecargado en subclases si es necesario
    }
}