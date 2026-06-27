public class InventarioTest {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        inventario.registrarIngreso("P001", "Martillo", 10, 25.50);

        Producto producto = inventario.buscarPorId("P001");

        if (producto != null) {
            System.out.println("Prueba 1 correcta");
        } else {
            System.out.println("Prueba 1 fallo");
        }

    }

}