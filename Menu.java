package com.empresa.globitos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Clientes> listaClientes = new ArrayList<>();

        int opcionMenuPrincipal;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar nuevo cliente");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            opcionMenuPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcionMenuPrincipal) {
                case 1:
                    Clientes cliente = agregarNuevoCliente(scanner);
                    listaClientes.add(cliente);
                    System.out.println("Cliente agregado exitosamente.");
                    gestionarPedido(scanner, cliente);
                    break;

                case 2:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcionMenuPrincipal != 2);

        scanner.close();
    }

    private static Clientes agregarNuevoCliente(Scanner scanner) {
        System.out.println("Ingrese el nombre del cliente:");
        String nombreCliente = scanner.nextLine();

        System.out.println("Ingrese la dirección del cliente:");
        String direccionCliente = scanner.nextLine();

        System.out.println("Ingrese el teléfono del cliente:");
        String telefonoCliente = scanner.nextLine();

        System.out.println("Ingrese el email del cliente:");
        String emailCliente = scanner.nextLine();

        return new Clientes(nombreCliente, direccionCliente, telefonoCliente, emailCliente);
    }

    private static void gestionarPedido(Scanner scanner, Clientes cliente) {
        ControlInventario inventario = inicializarInventario();

        System.out.println("Ingrese el número del pedido:");
        String numeroPedido = scanner.nextLine();

        System.out.println("Ingrese la fecha del pedido (YYYY-MM-DD):");
        String fechaPedido = scanner.nextLine();

        Pedido pedido = new Pedido(numeroPedido, fechaPedido, cliente);

        int opcion;
        do {
            System.out.println("\n--- Menú del Pedido ---");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Agregar modificación al pedido");
            System.out.println("4. Ver detalles del pedido");
            System.out.println("5. Generar factura");
            System.out.println("6. Finalizar pedido y regresar al menú principal");
            System.out.println("7. Finalizar pedido y salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verProductosDisponibles(inventario);
                    break;
                case 2:
                    agregarProductoAlPedido(pedido, inventario, scanner);
                    break;
                case 3:
                    agregarModificacionAlPedido(pedido, scanner);
                    break;
                case 4:
                    verDetallesPedido(pedido);
                    break;
                case 5:
                    generarFactura(pedido);
                    break;
                case 6:
                    System.out.println("Pedido finalizado. Regresando al menú principal...");
                    return;
                case 7:
                    System.out.println("Pedido finalizado. Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }

    private static ControlInventario inicializarInventario() {
        ControlInventario inventario = new ControlInventario();
        inventario.agregarProducto(new Producto("Sombrilla", "Promocional", 1000, 10.5f));
        inventario.agregarProducto(new Producto("Taza", "Cerámica", 1200, 5.0f));
        inventario.agregarProducto(new Producto("Camisa", "Algodón", 500, 13.0f));
        inventario.agregarProducto(new ProductoFabrica("Llaveros", "Metálico", 1500, 3.5f, 7));
        inventario.agregarProducto(new ProductoFabrica("Bolígrafos", "Plástico", 1500, 1.0f, 3));
        return inventario;
    }

    private static void verProductosDisponibles(ControlInventario inventario) {
        System.out.println("\n--- Productos Disponibles ---");
        for (Producto producto : inventario.getProductos()) {
            System.out.println(producto.toString());
        }
    }

    private static void agregarProductoAlPedido(Pedido pedido, ControlInventario inventario, Scanner scanner) {
        System.out.println("Ingrese el nombre del producto que desea agregar:");
        String nombreProducto = scanner.nextLine();
        Producto producto = inventario.buscarProducto(nombreProducto);
        if (producto != null) {
            System.out.println("Ingrese la cantidad de " + producto.getNombre() + " que desea agregar:");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            if (producto instanceof ProductoFabrica) {
                pedido.agregarProducto(producto, cantidad);
                System.out.println("Producto fabricable agregado al pedido: " + cantidad + " x " + producto.getNombre());
            } else {
                if (cantidad > 0 && cantidad <= producto.getCantidadDisponible()) {
                    pedido.agregarProducto(producto, cantidad);
                    producto.reducirCantidadDisponible(cantidad);
                    System.out.println("Producto agregado al pedido: " + cantidad + " x " + producto.getNombre());
                } else {
                    System.out.println("Cantidad no válida. No hay suficiente producto en el inventario.");
                }
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void agregarModificacionAlPedido(Pedido pedido, Scanner scanner) {
        System.out.println("Ingrese la descripción de la modificación:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el costo de la modificación:");
        float costo = scanner.nextFloat();
        scanner.nextLine();

        Modificacion modificacion = new Modificacion(descripcion, costo);
        pedido.agregarModificacion(modificacion);

        System.out.println("Modificación agregada al pedido: " + descripcion);
    }

    private static void verDetallesPedido(Pedido pedido) {
        System.out.println("\n--- Detalles del Pedido ---");
        System.out.println("Número de Pedido: " + pedido.getNumeroPedido());
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.println("Cliente: " + pedido.getCliente().getNombre());

        System.out.println("\nProductos en el Pedido:");
        for (PedidoProducto pedidoProducto : pedido.getProductos()) {
            Producto producto = pedidoProducto.getProducto();
            int cantidad = pedidoProducto.getCantidad();
            System.out.println("- " + cantidad + " x " + producto.toString());
        }

        System.out.println("\nModificaciones:");
        for (Modificacion modificacion : pedido.getModificaciones()) {
            System.out.println("- " + modificacion.getDescripcion() + " (Costo: " + modificacion.getCosto() + ")");
        }
    }

    private static void generarFactura(Pedido pedido) {
        float montoTotal = 0;

        for (PedidoProducto pedidoProducto : pedido.getProductos()) {
            Producto producto = pedidoProducto.getProducto();
            int cantidad = pedidoProducto.getCantidad();
            montoTotal += producto.getPrecio() * cantidad;
        }

        for (Modificacion modificacion : pedido.getModificaciones()) {
            montoTotal += modificacion.getCosto();
        }

        Factura factura = new Factura("F" + pedido.getNumeroPedido(), pedido.getFecha(), montoTotal, pedido);
        factura.mostrarFactura();
    }
}
