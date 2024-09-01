package com.empresa.globitos;

public class Factura {
    // Atributos privados para encapsulación
    private String numeroFactura;
    private String fechaEmision;
    private float montoTotal;
    private Pedido pedido;

    // Constructor para inicializar los atributos de la factura
    public Factura(String numeroFactura, String fechaEmision, float montoTotal, Pedido pedido) {
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.pedido = pedido;
    }

    // Métodos Getters para acceder a los atributos
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    // Método para mostrar la factura en la consola
    public void mostrarFactura() {
        System.out.println("Factura N°: " + numeroFactura);
        System.out.println("Fecha de Emisión: " + fechaEmision);
        System.out.println("Productos:");

        for (PedidoProducto pedidoProducto : pedido.getProductos()) {
            Producto producto = pedidoProducto.getProducto();
            int cantidad = pedidoProducto.getCantidad();

            System.out.println("- " + cantidad + " x " + producto.getNombre() + " - Q" + producto.getPrecio());
        }

        System.out.println("Monto Total: Q" + montoTotal);
    }
}