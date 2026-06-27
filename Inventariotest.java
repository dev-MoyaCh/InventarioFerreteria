public class InventarioTest {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        // Prueba 1: Registrar ingreso
        inventario.registrarIngreso("P001", "Martillo", 10, 25.50);

        Producto producto = inventario.buscarPorId("P001");

        if (producto != null) {
            System.out.println("Prueba 1 correcta");
        } else {
            System.out.println("Prueba 1 fallo");
        }

        // Prueba 2: Actualizar stock
        inventario.registrarIngreso("P001", "Martillo", 5, 25.50);

        if (inventario.buscarPorId("P001").getCantidad() == 15) {
            System.out.println("Prueba 2 correcta");
        } else {
            System.out.println("Prueba 2 fallo");
        }

        // Prueba 3: Registrar salida
        inventario.registrarSalida("P001", 3);

        if (inventario.buscarPorId("P001").getCantidad() == 12) {
            System.out.println("Prueba 3 correcta");
        } else {
            System.out.println("Prueba 3 fallo");
        }

    }

}