import java.time.LocalDate;

public class VentaTest {

    public static void main(String[] args) {

        // ==========================================
        // PRUEBA 1: Aplicar descuento válido
        // ==========================================
        Venta venta1 = new Venta("V001", LocalDate.now());
        venta1.agregarDetalle("P001", "Martillo", 2, 25.50); // total = 51.0

        venta1.aplicarDescuento(10); // 10% de descuento

        if (Math.abs(venta1.getTotal() - 45.9) < 0.01) {
            System.out.println("Prueba 1 correcta");
        } else {
            System.out.println("Prueba 1 fallo. Total obtenido: " + venta1.getTotal());
        }

        // ==========================================
        // PRUEBA 2: Descuento negativo lanza excepción
        // ==========================================
        Venta venta2 = new Venta("V002", LocalDate.now());
        venta2.agregarDetalle("P002", "Destornillador", 1, 10.0);

        try {
            venta2.aplicarDescuento(-5);
            System.out.println("Prueba 2 fallo (no lanzo excepcion)");
        } catch (EntradaInvalidaException e) {
            System.out.println("Prueba 2 correcta");
        }

        // ==========================================
        // PRUEBA 3: Descuento mayor a 100% lanza excepción
        // ==========================================
        Venta venta3 = new Venta("V003", LocalDate.now());
        venta3.agregarDetalle("P003", "Cinta Metrica", 1, 12.30);

        try {
            venta3.aplicarDescuento(150);
            System.out.println("Prueba 3 fallo (no lanzo excepcion)");
        } catch (EntradaInvalidaException e) {
            System.out.println("Prueba 3 correcta");
        }
    }
}
