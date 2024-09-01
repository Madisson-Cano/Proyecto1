package com.empresa.globitos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlInventario {
    private List<Producto> productos;

    // Constructor
    public ControlInventario() {
        this.productos = new ArrayList<>();
    }

    // Obtener una copia inmutable de la lista de productos
    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    // Agregar un producto al inventario
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productos.add(producto);
        } else {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
    }

    // Buscar un producto por nombre
    public Producto buscarProducto(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null; // Producto no encontrado
    }
}
