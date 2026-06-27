public class InventarioTest {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        // ==========================
        // PRUEBA 1: Registrar ingreso
        // ==========================
        inventario.registrarIngreso("P001", "Martillo", 10, 25.50);

        Producto producto = inventario.buscarPorId("P001");

        if (producto != null) {
            System.out.println("Prueba 1 correcta");
        } else {
            System.out.println("Prueba 1 fallo");
        }

        // ==================================
        // PRUEBA 2: Actualizar stock
        // ==================================
        inventario.registrarIngreso("P001", "Martillo", 5, 25.50);

        if (inventario.buscarPorId("P001").getCantidad() == 15) {
            System.out.println("Prueba 2 correcta");
        } else {
            System.out.println("Prueba 2 fallo");
        }

        // ==================================
        // PRUEBA 3: Registrar salida
        // ==================================
        inventario.registrarSalida("P001", 3);

        if (inventario.buscarPorId("P001").getCantidad() == 12) {
            System.out.println("Prueba 3 correcta");
        } else {
            System.out.println("Prueba 3 fallo");
        }

        // ==================================
        // PRUEBA 4: Verificar inventario
        // ==================================
        if (inventario.obtenerInventario().size() == 1) {
            System.out.println("Prueba 4 correcta");
        } else {
            System.out.println("Prueba 4 fallo");
        }

        // ==================================
        // PRUEBA 5: Verificar historial
        // ==================================
        if (inventario.obtenerHistorialVentas().isEmpty()) {
            System.out.println("Prueba 5 correcta");
        } else {
            System.out.println("Prueba 5 fallo");
        }

    }

}