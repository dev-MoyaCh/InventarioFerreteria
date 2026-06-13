import java.util.*;

public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
        
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcionMenu();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
        
        System.out.println(" Sistema cerrado correctamente.");
    }

    private static void mostrarMenu() {
        System.out.println("\n  SISTEMA DE INVENTARIO Y VENTAS (FERRETERÍA)");
        System.out.println("1. Registrar Ingreso");
        System.out.println("2. Registrar Salida");
        System.out.println("3. Ver Inventario");
        System.out.println("4. Consultar Producto");
        System.out.println("5. Registrar Venta");
        System.out.println("6. Ver Historial de Ventas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarIngreso();
                case 2 -> registrarSalida();
                case 3 -> mostrarInventario();
                case 4 -> consultarProducto();
                case 5 -> registrarVenta();
                case 6 -> mostrarHistorial();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println(" Opción no válida.");
            }
        
        } catch (Exception e) {
            System.out.println(" Error inesperado: " + e.getMessage());
        }
    }

    private static void registrarIngreso() {
        
    }

    private static void registrarSalida() {
        
    }

    private static void mostrarInventario() {
    
    }

    private static void consultarProducto() {
      
    }

    private static void registrarVenta() {
        
    }

    private static void mostrarHistorial() {
        
    }

    //  UTILIDADES DE ENTRADA
    private static int leerOpcionMenu() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int leerIntPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor > 0) return valor;
                System.out.println(" Debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println(" Ingrese un número entero  válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor >= 0) return valor;
                System.out.println(" No puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println(" Ingrese un número válido.");
            }
        }
    }

    
}
