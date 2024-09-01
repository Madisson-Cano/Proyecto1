package com.empresa.globitos;

public class PedidoProducto {
    private Producto producto; // Convención de nomenclatura: variable en minúsculas
    private int cantidad;

    // Constructor
    public PedidoProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getter para el producto
    public Producto getProducto() {
        return producto;
    }

    // Getter para la cantidad
    public int getCantidad() {
        return cantidad;
    }

    // Representación en cadena del pedido de producto
    @Override
    public String toString() {
        return cantidad + " x " + producto.getNombre() + " (Precio Unitario: Q" + producto.getPrecio() + ")";
    }
}
