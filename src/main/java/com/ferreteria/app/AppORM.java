package com.ferreteria.app;

import com.ferreteria.dao.ProductoDAO;
import com.ferreteria.modelo.Producto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Programa de demostración: muestra el ORM guardando y leyendo productos
 * de una base de datos real (H2), sin escribir una sola línea de SQL.
 */
public class AppORM {

    private static final ProductoDAO dao = new ProductoDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== DEMO ORM: Inventario Ferretería con Hibernate + H2 ===\n");

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutar(opcion);
        } while (opcion != 0);

        dao.cerrar();
        System.out.println("Conexión cerrada. Hasta luego.");
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Guardar producto nuevo");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Eliminar producto");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutar(int opcion) {
        try {
            switch (opcion) {
                case 1 -> guardarProducto();
                case 2 -> buscarProducto();
                case 3 -> listarProductos();
                case 4 -> eliminarProducto();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void guardarProducto() {
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine().trim());

        Producto p = new Producto(id, nombre, cantidad, precio);
        dao.guardar(p);
        System.out.println("Producto guardado en la base de datos.");
    }

    private static void buscarProducto() {
        System.out.print("ID a buscar: ");
        String id = scanner.nextLine().trim();
        Optional<Producto> resultado = dao.buscarPorId(id);
        if (resultado.isPresent()) {
            System.out.println(resultado.get());
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }
    }

    private static void listarProductos() {
        List<Producto> productos = dao.listarTodos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos guardados todavía.");
            return;
        }
        productos.forEach(System.out::println);
    }

    private static void eliminarProducto() {
        System.out.print("ID a eliminar: ");
        String id = scanner.nextLine().trim();
        dao.eliminar(id);
        System.out.println("Producto eliminado (si existía).");
    }
}
